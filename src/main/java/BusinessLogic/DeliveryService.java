package BusinessLogic;

import Data.FilesAndFoldersUtil;
import Data.Serializator;
import GUI.LogInView;
import Model.Admin;
import Model.Client;
import Model.Employee;
import Model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {
    private HashMap<Order,ArrayList<MenuItem>> map = new HashMap<Order, ArrayList<MenuItem>>();
    private  ArrayList<MenuItem>  allProducts;
    private String fileSource = "D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\baseproducts\\";
    private String fileOrders = "D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\orders\\";
    private static final long serialVersionUID = 9165573976487385543L;



    public DeliveryService(){ }
    public DeliveryService(HashMap<Order, ArrayList<MenuItem>> map) {
        this.map = map;

    }

    public HashMap<Order, ArrayList<MenuItem>> getMap() {
        return map;
    }

    public HashMap<Order, ArrayList<MenuItem>> getUpdatedMap() {
        try {
            DeliveryService ds = (DeliveryService) Serializator.readObject("D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\map");
            return ds.map;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int calculateSinglePrice(ArrayList<MenuItem> items){
        int total = 0;
        for(MenuItem m : items)
                total = total + m.getPrice();
        return total;
    }
    @Override
    public int deleteProduct(String name) {
            assert name != null;
            File file = new File(fileSource + name);
            if(file.exists()){
                //FilesAndFoldersUtil.deleteFile(file.getName());
                file.delete();
                return 0;
            }
            else return -1;
    }

    @Override
    public int editProduct(String name, String field, int value) {
        assert field != null;
        assert name != null;
        File file = new File(fileSource + name);
        if(!file.exists()){
            return -1;
        }
        else {
            try {
                BaseProduct b = (BaseProduct) Serializator.readObject(fileSource + name);
                if (field.equals("Rating")) b.setRating(value);
                if (field.equals("Calories")) b.setCalories(value);
                if (field.equals("Protein")) b.setProtein(value);
                if (field.equals("Fat")) b.setFat(value);
                if (field.equals("Sodium")) b.setSodium(value);
                if (field.equals("Price")) b.setPrice(value);
                Serializator.writeObject(b, fileSource + name);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        return 0;
        }
    }

    @Override
    public int insertProduct(MenuItem m) throws IOException {
        BaseProduct p = (BaseProduct) m;
        File file = new File(fileSource + p.getName());
        if(file.exists()){
            return -1;
        }
        else {
                Serializator.writeObject(m, fileSource + p.getName());
            return 0;
        }
    }

    @Override
    public void addElemToMap(Order o, ArrayList<MenuItem> items) {
        assert o != null;
        assert items != null;
        map.put(o, items);
        notifyObservers();
    }

    @Override
    public void generateType1Report(FileWriter fileWriter, int s, int f) throws IOException {
        assert fileWriter != null;
        for(Map.Entry<Order,ArrayList<MenuItem>> it : this.getUpdatedMap().entrySet()) //parcurg tot map-ul
        {
            if(it.getKey().getOrderDate().getHour() >= s && it.getKey().getOrderDate().getHour() < f){
                fileWriter.write("Comanda cu ID-ul : " + it.getKey().getOrderID() +
                        "\n Din data de: " + it.getKey().getOrderDate().getDayOfMonth() + ", luna: " + it.getKey().getOrderDate().getMonth() +
                        "\n S-au comandat produse in valoare de: " + this.calculateSinglePrice(it.getValue()));
            }
        }
    }
    @Override
    public void generateType2Report(FileWriter fileWriter, int amount) throws IOException {
        ArrayList<MenuItem> joinedList = new ArrayList<>();
        for(Map.Entry<Order,ArrayList<MenuItem>> it : this.getUpdatedMap().entrySet())
            joinedList.addAll(it.getValue());
        Map<String, Long> count = joinedList.stream()
                .collect(Collectors.groupingBy(
                        MenuItem::getName,
                        Collectors.counting()));
        for(Map.Entry<String,Long> it : count.entrySet())
            if(it.getValue() >= amount)
                fileWriter.write(it.getKey() + "\n");
    }

    @Override
    public void generateType3Report(FileWriter fileWriter, int ordersNumber, int miniSum) throws IOException {
        ArrayList<Order> joinedList = new ArrayList<>();
        for(Map.Entry<Order,ArrayList<MenuItem>> it : this.getUpdatedMap().entrySet()) {
            if(this.calculateSinglePrice(it.getValue()) > miniSum) // i retin doar pe cei ce o comandat minimul
                joinedList.add(it.getKey());
        }
        Map<String, Long> count = joinedList.stream()
                .collect(Collectors.groupingBy(
                        Order::getClientID,
                        Collectors.counting()));
        for(Map.Entry<String,Long> it : count.entrySet())
            if(it.getValue() >= ordersNumber ) {
                fileWriter.write(it.getKey() + "\n");
            }
    }

    @Override
    public void generateType4Report(FileWriter fileWriter, String month, int day) throws IOException {
        assert month != null;
        assert day < 32;
        ArrayList<MenuItem> joinedList = new ArrayList<>();
        for(Map.Entry<Order,ArrayList<MenuItem>> it : this.getUpdatedMap().entrySet())
            if(Integer.parseInt(String.valueOf(it.getKey().getOrderDate().getDayOfMonth())) == day &&
                    (String.valueOf(it.getKey().getOrderDate().getMonth())).equals(month))
                joinedList.addAll(it.getValue());
        Map<String, Long> count = joinedList.stream()
                .collect(Collectors.groupingBy(
                        MenuItem::getName,
                        Collectors.counting()));
        for(Map.Entry<String,Long> it : count.entrySet())
                fileWriter.write(it.getKey() + " a fost comandat de " + it.getValue() + " ori\n");
    }

    public ArrayList<Order> getOrderList(){
        ArrayList<Order> orders = new ArrayList<>();
        for(Map.Entry<Order, ArrayList<MenuItem>> it:map.entrySet()){
            orders.add(it.getKey());
        }
        return orders;
    }
    public void addToOrder(String name, ArrayList<MenuItem> dishes)
    {
        File file = new File(fileSource + name);
        if (file.exists()) {
            MenuItem b = null;
            try {
                if(Serializator.readObject(fileSource + name) instanceof  BaseProduct)
                    b = (BaseProduct) Serializator.readObject(fileSource + name);
                else if(Serializator.readObject(fileSource + name) instanceof CompositeProduct)
                    b = (CompositeProduct) Serializator.readObject(fileSource + name);
                dishes.add(b);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void deleteFromOrder(String name, ArrayList<MenuItem> dishes){
        for(MenuItem m : dishes){
            //BaseProduct b = (BaseProduct) m;
            String s1 = m.getName().trim();
            String s2 = name.trim();
            if(s1.equals(s2)) {
                dishes.remove(m);
                break;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        ArrayList<User> users1 = new ArrayList<>();
        User u1 = new Admin("Alin","papa","Alin Florin", "Borhanci", User.Role.ADMIN);
        User u2 = new Client("Sorin", "123", "Sorin Dorin", "OBS", User.Role.CLIENT);
        User u3 = new Employee("Grig", "eu","Popescu Grig", "Victoriei", User.Role.EMPLOYEE);
        users1.add(u1);
        users1.add(u2);
        users1.add(u3);

        DeliveryService ds = new DeliveryService();
        LogInView lw = new LogInView(users1);
        Controller controller = new Controller(ds,lw, users1);
        lw.setVisible(true);
    }
}
