package Data;

import BusinessLogic.BaseProduct;
import BusinessLogic.CompositeProduct;
import BusinessLogic.MenuItem;
import Model.User;
import com.opencsv.CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FileCsvUtil{
    private final String fileSource;
    private final String fileDestination = "D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\baseproducts\\";
    private static final long serialVersionUID = 42L;

    public FileCsvUtil(String fileSource) {
        this.fileSource = fileSource;
    }

    public ArrayList<MenuItem> readFromCSV() {
        ArrayList<MenuItem> list = new ArrayList<>();
        //Instantiating the CSVReader class
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(fileSource));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Reading the contents of the csv file
        StringBuffer buffer = new StringBuffer();
        String[] line = new String[0];
        while (true) {
            try {
                if (!((line = reader.readNext()) != null))
                    break;
                modifyLine(line, list);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Collections.sort(list);

        return list;
    }
    public void modifyLine(String [] line, ArrayList<MenuItem> list){
        if (!line[0].equals("Title")) {
            if (line[1].equals("0"))
                line[1] = "0.0";
            line[0] = line[0].replace('"','-');
            line[0] = line[0].replace('/','^');
            line[0] = line[0].replace(':','.');
            MenuItem product = new BaseProduct(line[0], Double.parseDouble(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]),
                    Integer.parseInt(line[4]), Integer.parseInt(line[5]), Integer.parseInt(line[6]));

            //serializare
            try {
                File file = new File(fileDestination + line[0]);
                if(!file.exists()) {
                    Serializator.writeObject(product, fileDestination + line[0]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            list.add(product);
        }
    }
    public ArrayList<MenuItem> deserialization() {
        ArrayList<MenuItem> list = new ArrayList<>();
        List<String> files = FilesAndFoldersUtil.getFilesInFolder(fileDestination);
        try {
            for (String file : files) {
                if(Serializator.readObject(fileDestination + file) instanceof BaseProduct) {
                    MenuItem p = (BaseProduct) Serializator.readObject(fileDestination + file);
                    list.add(p);
                }
                else if (Serializator.readObject(fileDestination + file) instanceof CompositeProduct){
                    MenuItem p = (CompositeProduct) Serializator.readObject(fileDestination + file);
                    //tre sa fac cumva cast
                    list.add(p);
                }
            }
        } catch ( ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e){
            System.out.println("In total avem in meniu " + files.size() + " feluri de mancare");
        }
        return list;
    }
}
