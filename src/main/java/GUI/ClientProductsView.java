package GUI;

import BusinessLogic.BaseProduct;
import BusinessLogic.CompositeProduct;
import BusinessLogic.MenuItem;
import Data.FileCsvUtil;
import Data.Reflection;
import Model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class ClientProductsView extends JFrame {
    private JLabel welcome = new JLabel("Produsele noastre sunt");
    private String fileSource = "D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\baseproducts\\";
    private static final long  serialVersionUID = 42l;

    ClientProductsView(){
        JPanel content = new JPanel();
        this.setContentPane(content);
        this.pack();
        this.setTitle("Gutes Essen - Meniu produse");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        content.setLayout(null);

        Font f2 = new Font(Font.DIALOG, Font.ITALIC, 15);
        Color color_background = new Color(250,232,224);
        content.setBackground(color_background);

        welcome.setBounds(20,30,200,30);
        welcome.setFont(f2);
        content.add(welcome);

        ArrayList<MenuItem> products;
        FileCsvUtil file = new FileCsvUtil(fileSource);
        products = file.deserialization();
        content.add(displayTable(products));


    }
    public JTable createTable(ArrayList<MenuItem> arrayList){
        String[] column = new String[]{"Title","Rating","Calories","Protein","Fat","Sodium","Price"};
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.setColumnIdentifiers(column);
        JTable table = new JTable(defaultTableModel);
        Object[][] obj = Reflection.retrieveData(arrayList);
        for(Object o : obj) {
            defaultTableModel.addRow((Object[]) o);
        }
        return table;
    }
    public JScrollPane displayTable(ArrayList<MenuItem> products){
        JScrollPane scrollPane= new JScrollPane();
        scrollPane.setBounds(20, 100, 520,400);

        JTable productTable;
        productTable = createTable(products);
        productTable.setVisible(true);
        productTable.setEnabled(true);

        scrollPane.setViewportView(productTable);

        return scrollPane;
    }
}
