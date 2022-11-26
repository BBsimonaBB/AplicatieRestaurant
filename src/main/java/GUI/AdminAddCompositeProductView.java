package GUI;

import BusinessLogic.BaseProduct;
import BusinessLogic.CompositeProduct;
import BusinessLogic.DeliveryService;
import BusinessLogic.MenuItem;
import Data.FileCsvUtil;
import Data.FilesAndFoldersUtil;
import Data.Reflection;
import Data.Serializator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class AdminAddCompositeProductView extends JFrame {
    private JLabel welcome = new JLabel("Introduceti numele produselor");
    private JTextField productName = new JTextField(7);
    private JButton addi = new JButton("Adauga");
    private JButton delete = new JButton("Sterge");
    private JButton fin = new JButton("Finalizeaza crearea produsului");

    private ArrayList<MenuItem> components = new ArrayList<>();
    private DeliveryService ds = new DeliveryService();

    private String fileSource = "D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\baseproducts\\";


    AdminAddCompositeProductView(){
        JPanel content = new JPanel();
        this.setContentPane(content);
        this.pack();
        this.setTitle("Gutes Essen");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(650, 500);
        content.setLayout(null);

        Font f1 = new Font(Font.SANS_SERIF, Font.ITALIC, 30);
        Font f2 = new Font(Font.DIALOG, Font.HANGING_BASELINE, 20);
        Color color_background = new Color(250, 232, 224);
        content.setBackground(color_background);

        welcome.setBounds(70, 30, 500, 30);
        welcome.setFont(f1);
        content.add(welcome);

        productName.setBounds(30,70,530,30);
        content.add(productName);

        addi.setBounds(30,110,100,20);
        delete.setBounds(150,110,100,20);
        fin.setBounds(270,110,100,20);
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
                        ds.addToOrder(productName.getText(),components);
                        content.add(displayTable(components));
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
                    ds.deleteFromOrder(productName.getText(), components);
                    content.add(displayTable(components));
                }
            }
        });
        fin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BaseProduct b = (BaseProduct) components.get(0);
                MenuItem c = new CompositeProduct("DailyMenu-" + b.getName(),components);
                try {
                    Serializator.writeObject(c,fileSource + "DailyMenu-" + b.getName());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public JTable createTable(ArrayList<BusinessLogic.MenuItem> arrayList){
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
