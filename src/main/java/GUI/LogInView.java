package GUI;

import BusinessLogic.BaseProduct;
import BusinessLogic.DeliveryService;
import BusinessLogic.MenuItem;
import Data.FilesAndFoldersUtil;
import Data.Serializator;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogInView extends JFrame {
    private EmployeeView ew = new EmployeeView();
    private JLabel welcome = new JLabel("Welcome!");
    private JLabel username = new JLabel("Username:");
    private JLabel passcode = new JLabel("Passcode:");
    private JPasswordField userPass = new JPasswordField(7);
    private JTextField userField = new JTextField(7);
    private JButton logIn = new JButton("Log In");
    private JButton register = new JButton("Become our client - Register");

    private String fileUsers = "D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\users\\";
    private String fileProducts = "D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\baseproducts\\";

    private static final long  serialVersionUID = 42l;

    public LogInView(ArrayList<User> users) {
        JPanel content = new JPanel();
        this.setContentPane(content);
        this.pack();
        this.setTitle("Gutes Essen");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(650, 500);
        content.setLayout(null);

        Font f1 = new Font(Font.SANS_SERIF, Font.ITALIC, 30);
        Font f2 = new Font(Font.DIALOG, Font.HANGING_BASELINE, 20);
        Color color_background = new Color(250,232,224);
        content.setBackground(color_background);

        welcome.setBounds(70,30,300,30);
        welcome.setFont(f1);
        content.add(welcome);

        username.setBounds(70,130,100,20);
        userField.setBounds(200,130,100,20);
        username.setFont(f2);
        userField.setFont(f2);
        content.add(username);
        content.add(userField);

        passcode.setBounds(70,160,100,20);
        userPass.setBounds(200,160,100,20);
        passcode.setFont(f2);
        userPass.setFont(f2);
        userPass.setEchoChar('*');
        content.add(userPass);
        content.add(passcode);

        logIn.setBounds(130,230,120,30);
        content.add(logIn);

        register.setBounds(100,300,250,20);
        content.add(register);

        ImageIcon serveIcon = new ImageIcon("serve.png");
        JLabel serveLabel = new JLabel(serveIcon);
        serveLabel.setBounds(450, 30 ,120, 120);
        content.add(serveLabel);

        ImageIcon dwIcon = new ImageIcon("dwa.png");
        JLabel dwLabel = new JLabel(dwIcon);
        dwLabel.setBounds(330, 200 ,300, 300);
        content.add(dwLabel);


        try {
            List<String> files = FilesAndFoldersUtil.getFilesInFolder(fileUsers);
            for (String file : files) {
                User u = (User) Serializator.readObject(fileUsers + file);
                users.add(u);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            RegisterView rw = new RegisterView(users);
            rw.setVisible(true);
            }
        });

        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(userField.getText().equals("") || userPass.getText().equals(""))
                    showError("Ati uitat sa introduceti username-ul sau parola");
                else {
                    int found = 0;
                    for (User u : users) {
                        if (u.getUsername().equals(userField.getText())) {
                            found = 1;
                            if (u.getPassword().equals(userPass.getText())) {
                                if(u.getRole().equals(User.Role.CLIENT)) {
                                    ClientView cw = new ClientView(userField.getText());
                                    cw.setVisible(true);
                                    break;
                                }
                                if(u.getRole().equals(User.Role.ADMIN)){
                                    AdminView aw = new AdminView();
                                    aw.setVisible(true);
                                    break;
                                }
                                if(u.getRole().equals(User.Role.EMPLOYEE)){
                                    ew.setVisible(true);
                                    break;
                                }
                            }
                            else {
                                showError("Parola este incorecta !");
                                break;
                            }
                        }
                    }
                    if (found == 0)
                        showError("User-ul nu exista in baza noastra de date");
                }
            }
        });
    }

    public EmployeeView getEw() {
        return ew;
    }

    public void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }
}
