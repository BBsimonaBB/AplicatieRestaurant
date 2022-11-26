package GUI;

import BusinessLogic.BaseProduct;
import BusinessLogic.MenuItem;
import Data.FileCsvUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminView extends JFrame {
    private JLabel welcome = new JLabel("Alege actiunea:");
    private JComboBox menu = new JComboBox(new String[]{"Import produse existente", "Produse de baza", "Insert produs compus", "Genereaza rapoarte"});
    private JButton next = new JButton("Mai departe");

    AdminView() {
        JPanel content = new JPanel();
        this.setContentPane(content);
        this.pack();
        this.setTitle("Gutes Essen - ADMIN");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 300);
        content.setLayout(null);

        Font f1 = new Font(Font.SANS_SERIF, Font.ITALIC, 20);
        Font f2 = new Font(Font.DIALOG, Font.HANGING_BASELINE, 20);
        Color color_background = new Color(250, 232, 224);
        content.setBackground(color_background);

        welcome.setBounds(30, 30, 300, 20);
        welcome.setFont(f1);
        content.add(welcome);

        menu.setBounds(30, 100, 250, 30);
        menu.setFont(f2);
        content.add(menu);

        next.setBounds(80, 180, 200, 20);
        content.add(next);

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<MenuItem> productsList = new ArrayList();
                if(menu.getItemAt(menu.getSelectedIndex()).equals("Import produse existente")){
                    FileCsvUtil file = new FileCsvUtil("products.csv");
                    productsList = file.readFromCSV();

                }
                else if(menu.getItemAt(menu.getSelectedIndex()).equals("Produse de baza")){
                    System.out.println("Before");
                    AdminBaseProductView abpw = new AdminBaseProductView();
                    System.out.println("After");
                    abpw.setVisible(true);
                    System.out.println("After2");
                }
                else if(menu.getItemAt(menu.getSelectedIndex()).equals("Insert produs compus")){
                    AdminAddCompositeProductView cpw = new AdminAddCompositeProductView();
                    cpw.setVisible(true);
                }
                else if(menu.getItemAt(menu.getSelectedIndex()).equals("Genereaza rapoarte")){
                    AdminReportsView adw = new AdminReportsView();
                    adw.setVisible(true);
                }
            }
        });
    }
}
