package GUI;

import BusinessLogic.*;
import BusinessLogic.MenuItem;
import Data.Reflection;
import Data.Serializator;
import Model.Employee;
import Model.User;
import org.apache.commons.logging.Log;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ClientOrderView extends JFrame {
    private FileWriter myFile;
    private JLabel welcome = new JLabel("Introduceti numele produselor");
    private JTextField productName = new JTextField(7);
    private JButton addi = new JButton("Adauga");
    private JButton delete = new JButton("Sterge");
    private JButton fin = new JButton("Finalizeaza comanda");

    private ArrayList<MenuItem> dishes = new ArrayList<>();
    //private HashMap<Order,ArrayList<MenuItem>> map = new HashMap<>();
    private DeliveryService ds = new DeliveryService(new HashMap<>());

    private String fileSource = "D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\baseproducts\\";
    private String fileOrders = "D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\orders\\";

    ClientOrderView(String username) {
        LogInView lw = new LogInView(new ArrayList<User>());
        lw.setVisible(true);
        //ds.addObserver(lw.getEw());
        JPanel content = new JPanel();
        this.setContentPane(content);
        this.pack();
        this.setTitle("Gutes Essen");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(650, 500);
        content.setLayout(null);
        //deci asa pun actuala lista de comenzi in HashMap
        if(new File("D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\map").exists())
             try {
                ds = (DeliveryService) Serializator.readObject("D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\map");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        Font f1 = new Font(Font.SANS_SERIF, Font.ITALIC, 30);
        Color color_background = new Color(250, 232, 224);
        content.setBackground(color_background);

        welcome.setBounds(70, 30, 500, 30);
        welcome.setFont(f1);
        content.add(welcome);

        productName.setBounds(30, 70, 530, 30);
        content.add(productName);

        addi.setBounds(30, 110, 100, 20);
        delete.setBounds(150, 110, 100, 20);
        fin.setBounds(270, 110, 200, 20);
        content.add(addi);
        content.add(delete);
        content.add(fin);

        addi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (productName.getText().equals(""))
                    showError("Nu ati introdus nimic in caseta");
                else if (!new File(fileSource + productName.getText()).exists())
                    showError("Produsul nu exista in meniu ! ");
                else{
                    ds.addToOrder(productName.getText(), dishes);
                    content.add(displayTable(dishes));
                }
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(productName.getText().equals(""))
                    showError("Introduceti un nume valid");
                else if (!new File(fileSource + productName.getText()).exists())
                    showError("Produsul ce se doreste a fi sters nu exista in lista");
                else {
                    ds.deleteFromOrder(productName.getText(), dishes);
                    content.add(displayTable(dishes));
                }
            }
        });
        fin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int orderNo = Objects.requireNonNull(new File("D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\bills").list()).length;
                Order o = new Order(orderNo,username);
                ds.addElemToMap(o,dishes); //pun o comanda in map
                try { //serializez frumos tot map-ul nou sa-l am pe mai departe, cu tot cu noul element
                    Serializator.writeObject(ds,"D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\map");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    ds = (DeliveryService) Serializator.readObject("D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\map");
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                makeReceipt(o, ds.getMap());
                 if(lw.getEw().isVisible()) { //apare doar cand si employee-ul este logat
                    ds.addObserver(lw.getEw()); //notificare aolo
                    lw.getEw().setVisible(true);
                    lw.getEw().update(ds,ds);
                    ds.deleteObserver(lw.getEw());
                }
            }
        });
    }
    public void makeReceipt(Order o, HashMap<Order,ArrayList<MenuItem>> map){
        try {
            String s = o.getOrderID() + "_" +o.getOrderDate().toString().replace(":",".");
            //nu mai serializez comenzile se[arat gata
            File f = new File("D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\bills\\" + s + ".txt");
            myFile = new FileWriter(f);
            myFile.write("ClientID : " + o.getClientID() +
                    "\nOrder Date: " + o.getOrderDate().getMonth() + ", " + o.getOrderDate().getDayOfMonth() +
                    "\nOrder Time: " + o.getOrderDate().getHour() + ", " + o.getOrderDate().getMinute() + ", " + o.getOrderDate().getSecond() +
                    "\nOrder Total: " + ds.calculateSinglePrice(ds.getMap().get(o)) + " RON" +
                    "\nOrdered Items: \n");
            for(MenuItem m : dishes)
                    myFile.write(m.getName() + "\n");
            myFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public JTable createTable(ArrayList<BusinessLogic.MenuItem> arrayList){
        String[] column = new String[]{"Type","Title","Price"};
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.setColumnIdentifiers(column);
        JTable table = new JTable(defaultTableModel);
        for(MenuItem m : arrayList){
            Object[] o = new Object[3];
            if(m instanceof BaseProduct)
                o[0] = "Fel de mancare";
            else
                o[0] = "Meniu";
            o[1] = m.getName();
            o[2] = String.valueOf(m.getPrice());
            defaultTableModel.addRow((Object[]) o);
        }
        return table;
    }
    public JScrollPane displayTable(ArrayList<MenuItem> products){
        JScrollPane scrollPane= new JScrollPane();
        scrollPane.setBounds(20, 170, 530,200);

        JTable productTable;
        productTable = createTable(products);
        productTable.setVisible(true);
        productTable.setEnabled(true);

        scrollPane.setViewportView(productTable);

        return scrollPane;
    }
    public void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }
}
