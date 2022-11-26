package GUI;

import BusinessLogic.MenuItem;
import Data.LambdaUtil;
import Data.Reflection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ClientSearchView extends JFrame {
    private JLabel welcome = new JLabel("Search after..");
    private JLabel name = new JLabel("Name:");
    private JLabel rating = new JLabel("Rating:");
    private JLabel calories = new JLabel("Calories nr:");
    private JLabel protein = new JLabel("Protein:");
    private JLabel fat = new JLabel("Fat:");
    private JLabel sodium = new JLabel("Sodium:");
    private JLabel price = new JLabel("Price:");
    private JTextField productName = new JTextField(7);
    private JTextField productRating = new JTextField(7);
    private JTextField productCalories = new JTextField(7);
    private JTextField productProtein = new JTextField(7);
    private JTextField productFat = new JTextField(7);
    private JTextField productSodium = new JTextField(7);
    private JTextField productPrice = new JTextField(7);
    private JButton search = new JButton("Search");


    public ClientSearchView() {

        JPanel content = new JPanel();
        this.setContentPane(content);
        this.pack();
        this.setTitle("Gutes Essen - Cautare dupa criterii");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        content.setLayout(null);

        Font f2 = new Font(Font.DIALOG, Font.ITALIC, 15);
        Color color_background = new Color(250, 232, 224);
        content.setBackground(color_background);

        welcome.setBounds(20, 30, 200, 30);
        welcome.setFont(f2);
        content.add(welcome);

        name.setBounds(20, 60, 100, 20);
        productName.setBounds(20, 80, 100, 20);
        name.setFont(f2);
        content.add(name);
        content.add(productName);

        rating.setBounds(150, 60, 100, 20);
        productRating.setBounds(150, 80, 100, 20);
        rating.setFont(f2);
        content.add(rating);
        content.add(productRating);

        calories.setBounds(280, 60, 100, 20);
        productCalories.setBounds(280, 80, 100, 20);
        calories.setFont(f2);
        content.add(calories);
        content.add(productCalories);

        protein.setBounds(410, 60, 100, 20);
        productProtein.setBounds(410, 80, 100, 20);
        protein.setFont(f2);
        content.add(protein);
        content.add(productProtein);

        fat.setBounds(20, 120, 100, 20);
        productFat.setBounds(20, 140, 100, 20);
        fat.setFont(f2);
        content.add(fat);
        content.add(productFat);

        sodium.setBounds(150, 120, 100, 20);
        productSodium.setBounds(150, 140, 100, 20);
        sodium.setFont(f2);
        content.add(sodium);
        content.add(productSodium);
        price.setBounds(280, 120, 100, 20);
        productPrice.setBounds(280, 140, 100, 20);
        price.setFont(f2);
        content.add(price);
        content.add(productPrice);

        search.setBounds(410, 140, 100, 20);
        search.setFont(f2);
        content.add(search);

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                 if(productName.getText().equals("") && productRating.getText().equals("") && productCalories.getText().equals("")
                 && productPrice.getText().equals("") && productSodium.getText().equals("") && productFat.getText().equals("") &&
                         productProtein.getText().equals("") )
                   showError("Nu ati introdus vreun text");
                {
                    List<MenuItem> list = LambdaUtil.makeCriteria(productName.getText(), productRating.getText(),
                            productCalories.getText(), productProtein.getText(), productFat.getText(),
                            productSodium.getText(), productPrice.getText());

                    content.add(displayTable( (ArrayList<MenuItem>) list ));
                }
            }
        });



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
        scrollPane.setBounds(30, 170, 450,350);

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
