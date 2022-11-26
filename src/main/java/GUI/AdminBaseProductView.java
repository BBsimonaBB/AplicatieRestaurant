package GUI;

import BusinessLogic.BaseProduct;
import BusinessLogic.DeliveryService;
import BusinessLogic.MenuItem;
import Data.FileCsvUtil;
import Data.FilesAndFoldersUtil;
import Data.Serializator;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AdminBaseProductView extends JFrame {
    private JLabel welcome = new JLabel("Aici se pot modifica produsele");
    private JLabel name = new JLabel("Titlu");
    private JTextField productName = new JTextField(7);
    private JButton delete = new JButton("Stergere");

    private JComboBox field = new JComboBox(new String[]{"Rating","Calories","Protein","Fat","Sodium","Price"});
    private JTextField value = new JTextField(7);
    private JButton edit = new JButton("Editare ");

    private JLabel rating = new JLabel("Rating:");
    private JLabel calories = new JLabel("Calories:");
    private JLabel protein = new JLabel("Protein");
    private JLabel fat = new JLabel("Fat:");
    private JLabel sodium = new JLabel("Sodium:");
    private JLabel price = new JLabel("Price");
    private JTextField productRating = new JTextField(7);
    private JTextField productCalories = new JTextField(7);
    private JTextField productProtein = new JTextField(7);
    private JTextField productFat = new JTextField(7);
    private JTextField productSodium = new JTextField(7);
    private JTextField productPrice = new JTextField(7);
    private JButton insert = new JButton("Inserare");

    private String fileSource = "D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\baseproducts\\";

    public AdminBaseProductView() {
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

        name.setBounds(30, 130, 100, 20);
        productName.setBounds(180, 130, 200, 20);
        name.setFont(f2);
        content.add(name);
        content.add(productName);

        delete.setBounds(480,130,130,20);
        delete.setFont(f2);
        content.add(delete);

        field.setBounds(30,170,100,20);
        value.setBounds(180,170,100,20);
        field.setFont(f2);
        content.add(field);
        content.add(value);

        edit.setBounds(480,170,130,20);
        edit.setFont(f2);
        content.add(edit);

        rating.setBounds(30,300,100,20);
        productRating.setBounds(150,300,100,20);
        rating.setFont(f2);
        content.add(rating);
        content.add(productRating);
        calories.setBounds(30,340,100,20);
        productCalories.setBounds(150,340,100,20);
        calories.setFont(f2);
        content.add(calories);
        content.add(productCalories);
        protein.setBounds(30,380,100,20);
        productProtein.setBounds(150,380,100,20);
        protein.setFont(f2);
        content.add(protein);
        content.add(productProtein);

        fat.setBounds(400,300,100,20);
        productFat.setBounds(520,300,100,20);
        fat.setFont(f2);
        content.add(fat);
        content.add(productFat);
        sodium.setBounds(400,340,100,20);
        productSodium.setBounds(520,340,100,20);
        sodium.setFont(f2);
        content.add(sodium);
        content.add(productSodium);
        price.setBounds(400,380,100,20);
        productPrice.setBounds(520,380,100,20);
        price.setFont(f2);
        content.add(price);
        content.add(productPrice);

        insert.setBounds(200,420,150,20);
        insert.setFont(f2);
        content.add(insert);

        /*FileCsvUtil file = new FileCsvUtil(fileSource);
        ArrayList<MenuItem> products = file.deserialization();*/
        DeliveryService ds = new DeliveryService();
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ds.deleteProduct(productName.getText()) == -1)
                    showError("Nu exista fel de mancare cu numele introdus");
                }
        });
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if( ds.editProduct(productName.getText(), field.getItemAt(field.getSelectedIndex()).toString(),
                        Integer.parseInt(value.getText()) ) == -1)
                    showError("Produsul nu exista in meniu - deci nu poate fi nici modificat");
            }
        });
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuItem b = new BaseProduct(productName.getText(), Double.parseDouble(productRating.getText()),
                        Integer.parseInt( productCalories.getText()), Integer.parseInt(productFat.getText()),
                        Integer.parseInt(productProtein.getText()), Integer.parseInt(productSodium.getText()),
                        Integer.parseInt(productPrice.getText()) );
                try {
                    if(ds.insertProduct(b) == -1 )
                        showError("Produsul cu acest nume exista deja in meniu, nu poate fi inserat, doar modificat");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }
}
