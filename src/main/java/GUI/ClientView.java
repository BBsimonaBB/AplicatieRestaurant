package GUI;

import BusinessLogic.BaseProduct;
import BusinessLogic.DeliveryService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClientView extends JFrame {
    private JLabel welcome = new JLabel("Ce putem face azi pentru tine?");
    private  JComboBox menu = new JComboBox(new String[]{"Vezi toate produsele","Cauta dupa..", "Pune o comanda"});
    private JButton next = new JButton("Mai departe");
    private static final long  serialVersionUID = 42l;

    ClientView(String username){
        JPanel content = new JPanel();
        this.setContentPane(content);
        this.pack();
        this.setTitle("Gutes Essen - CLIENT");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 300);
        content.setLayout(null);

        Font f1 = new Font(Font.SANS_SERIF, Font.ITALIC, 20);
        Font f2 = new Font(Font.DIALOG, Font.HANGING_BASELINE, 20);
        Color color_background = new Color(250,232,224);
        content.setBackground(color_background);

        welcome.setBounds(30,30,300,20);
        welcome.setFont(f1);
        content.add(welcome);

        menu.setBounds(30,100,250,30);
        menu.setFont(f2);
        content.add(menu);

        next.setBounds(80,180,200,15);
        content.add(next);

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(menu.getItemAt(menu.getSelectedIndex()).equals("Vezi toate produsele")){
                    ClientProductsView cpw = new ClientProductsView();
                    cpw.setVisible(true);
                }
                else if(menu.getItemAt(menu.getSelectedIndex()).equals("Cauta dupa..")){
                    ClientSearchView csv = new ClientSearchView();
                    csv.setVisible(true);
                }
                else{
                    ClientOrderView cov = new ClientOrderView(username);
                    cov.setVisible(true);
                }
            }
        });
    }
}
