package GUI;

import BusinessLogic.*;
import BusinessLogic.MenuItem;
import Data.FileCsvUtil;
import Data.Reflection;
import Data.Serializator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class EmployeeView extends JFrame implements Observer {
    private JLabel welcome = new JLabel("Acestea sunt comenzile de azi");
    private JButton fin = new JButton("Marcati ca vizualizate");
    private JScrollPane scrollPane = new JScrollPane();
    private String fileSource = "D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\orders\\";
    JPanel content = new JPanel();
    DeliveryService ds = new DeliveryService();

    EmployeeView(){
        this.setContentPane(content);
        this.pack();
        this.setTitle("Gutes Essen");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(650, 500);
        content.setLayout(null);
        Color color_background = new Color(250,232,224);
        content.setBackground(color_background);
        welcome.setBounds(30, 30, 300, 20);
        content.add(welcome);
        fin.setBounds(200,400,200,20);
        content.add(fin);
        if(new File("D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\map").exists())
        try {
            ds = (DeliveryService) Serializator.readObject("D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\map");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        scrollPane = displayTable(ds);
        content.add(scrollPane);
        fin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel)createTable(ds).getModel();
                System.out.println(model.getDataVector());
            }
        });

    }

    @Override
    public void update(Observable o, Object arg) {
        content.remove(scrollPane);
        dispose();
        scrollPane = displayTable((DeliveryService) arg);
        content.add(scrollPane);
        this.setVisible(true);
    }

    public JTable createTable(DeliveryService ds){
        String[] column = new String[]{"OrderID","ClientID","OrderDate", "Price"};
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.setColumnIdentifiers(column);
        JTable table = new JTable(defaultTableModel);
        ArrayList<Order> orders = ds.getOrderList();
        for(Order o : orders){
            Object[] obj = new Object[4];
            obj[0] = o.getOrderID();
            obj[1] = o.getClientID();
            obj[2] = o.getOrderDate();
            for(Map.Entry<Order,ArrayList<MenuItem>> it : ds.getUpdatedMap().entrySet())
                if(it.getKey().equals(o)) {
                    obj[3] = ds.calculateSinglePrice(it.getValue());
                    break;
                }

            defaultTableModel.addRow((Object[]) obj);
        }

        return table;
    }
    public JScrollPane displayTable(DeliveryService ds){
        JScrollPane scrollPane = new JScrollPane();
        JTable table;
        table = createTable(ds);
        table.setVisible(true);
        table.setEnabled(true);
        scrollPane.setBounds(20, 120, 530,200);
        scrollPane.setViewportView(table);

        return scrollPane;
    }
}