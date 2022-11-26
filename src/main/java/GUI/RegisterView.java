package GUI;

import Data.Serializator;
import Model.Client;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class RegisterView extends JFrame {
    private JLabel welcome = new JLabel("Fill in to create an account");
    private JLabel username = new JLabel("Username:");
    private JLabel passcode = new JLabel("Passcode:");
    private JLabel name = new JLabel("Nume:");
    private JLabel address = new JLabel("Adresa:");
    private JTextField userField = new JTextField(7);
    private JTextField userPass = new JTextField(7);
    private JTextField nameField = new JTextField(7);
    private JTextField addressField = new JTextField(7);
    private JButton register = new JButton("Register");

    private String fileSource = "D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\users\\";

    RegisterView(ArrayList<User> users){
        JPanel content = new JPanel();
        this.setContentPane(content);
        this.pack();
        this.setTitle("Gutes Essen - Register");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 300);
        content.setLayout(null);

        Font f2 = new Font(Font.DIALOG, Font.HANGING_BASELINE, 20);
        Color color_background = new Color(250,232,224);
        content.setBackground(color_background);

        welcome.setBounds(20,30,300,20);
        welcome.setFont(f2);
        content.add(welcome);

        username.setBounds(20,80,100,15);
        userField.setBounds(120,80,100,15);
        content.add(username);
        content.add(userField);

        passcode.setBounds(20,100,100,15);
        userPass.setBounds(120,100,100,15);
        content.add(userPass);
        content.add(passcode);

        name.setBounds(20,120,100,15);
        nameField.setBounds(120,120,100,15);
        content.add(name);
        content.add(nameField);

        address.setBounds(20,140,100,15);
        addressField.setBounds(120,140,100,15);
        content.add(address);
        content.add(addressField);

        register.setBounds(130,180,100,20);
        content.add(register);

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            if(userField.getText().equals("") || userPass.getText().equals("") || nameField.getText().equals("") || addressField.getText().equals(""))
                showError("Nu ati completat toate casutele obligatorii !");
            else{
                for(User u : users)
                {
                    if(u.getUsername().equals(userField.getText())) {
                        showError("Username-ul este deja luat ! ");
                        break;
                    }
                }
                try {
                    User u = new Client(userField.getText(),userPass.getText(),nameField.getText(),addressField.getText(), User.Role.CLIENT);
                    users.add(u);
                    Serializator.writeObject(u,fileSource + u.getUsername());
                    showError("Client creat cu succes");
                    dispose();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            }
        });
    }
    public void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }
}
