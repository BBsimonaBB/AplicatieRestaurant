package GUI;

import BusinessLogic.DeliveryService;
import BusinessLogic.MenuItem;
import BusinessLogic.Order;
import Data.FileCsvUtil;
import Data.Serializator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;

public class AdminReportsView extends JFrame {
    private JLabel welcome = new JLabel("Alege tipul de raport de generat:");
    private JComboBox menu = new JComboBox(new String[]{"Comenzi dintr-un interval de timp",
            "Produse comandate de .. ori",
            "Clienti care au comandat de .. ori",
            "Produsele comandate in data de .."});
    private JButton next = new JButton("NEXT");
    private JButton generate = new JButton("Generate");

    private JLabel start = new JLabel("Ora inceperii");
    private JLabel finish = new JLabel("Ora incheierii");
    private JComboBox timeStart = new JComboBox(new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13", "14",
            "15","16","17","18","19","20","21","22","23","24"});
    private JComboBox timeFinish = new JComboBox(new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13", "14",
            "15","16","17","18","19","20","21","22","23","24"});

    private JLabel name = new JLabel("Nume produs");
    private JLabel times = new JLabel("Numar bucati minime ");
    private JTextField fieldName = new JTextField(7);
    private JTextField fieldTimes = new JTextField(7);

    private JLabel name2 = new JLabel("Nume client");
    private JLabel amount = new JLabel("Suma minima cheltuita");
    private JTextField fieldAmount = new JTextField(7);

    private JLabel month = new JLabel("Luna:");
    private JLabel day = new JLabel("Ziua");

    private JComboBox fieldMonth = new JComboBox(new String[]{"JAN","FEB","MAR","APR","MAY","JUN","JUL",
            "AUG","SEP","OCT","NOV","DEC" });
    private JComboBox fieldDay = new JComboBox(new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13", "14",
            "15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"});

    private DeliveryService ds = new DeliveryService();
    private HashMap<Order, ArrayList<MenuItem>> map = new HashMap<Order, ArrayList<MenuItem>>();

    AdminReportsView() {
        JPanel content = new JPanel();
        this.setContentPane(content);
        this.pack();
        this.setTitle("Gutes Essen - ADMIN");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        content.setLayout(null);

        try {
            ds = (DeliveryService) Serializator.readObject("D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\map");
            //aici am toate comenzile efectuate vreodata
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Font f1 = new Font(Font.SANS_SERIF, Font.ITALIC, 20);
        Font f2 = new Font(Font.DIALOG, Font.HANGING_BASELINE, 20);
        Color color_background = new Color(250, 232, 224);
        content.setBackground(color_background);

        welcome.setBounds(30, 30, 300, 30);
        welcome.setFont(f1);
        content.add(welcome);

        menu.setBounds(30, 100, 400, 30);
        menu.setFont(f2);
        content.add(menu);

        next.setBounds(450, 100, 100, 30);
        content.add(next);

        generate.setBounds(250,330,130,20);

        timeStart.setBounds(30,200,100,20);
        timeFinish.setBounds(180,200,100,20);
        start.setBounds(30,170,100,20);
        finish.setBounds(180,170,100,20);

        name.setBounds(30,200,100,20);
        fieldName.setBounds(150,200,100,20);
        times.setBounds(30,200,200,20);
        fieldTimes.setBounds(270,200,100,20);

        name2.setBounds(30,200,100,20);
        amount.setBounds(30,230,150,20);
        fieldAmount.setBounds(200,230,100,20);

        month.setBounds(30,200,100,20);
        fieldMonth.setBounds(150,200,100,20);
        day.setBounds(30,230,100,20);
        fieldDay.setBounds(150,230,100,20);

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                content.add(generate);
                if(menu.getItemAt(menu.getSelectedIndex()).equals("Comenzi dintr-un interval de timp")){
                    content.add(timeStart);
                    content.add(timeFinish);
                    content.add(start);
                    content.add(finish);
                    content.remove(name);
                    content.remove(fieldName);
                    content.remove(times);
                    content.remove(fieldTimes);
                    content.remove(amount);
                    content.remove(fieldAmount);
                    content.remove(name2);
                    content.remove(day);
                    content.remove(month);
                    content.remove(fieldDay);
                    content.remove(fieldMonth);
                    content.revalidate();
                    content.repaint();
                    generate.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (Integer.parseInt((String) timeStart.getSelectedItem()) > Integer.parseInt((String) timeFinish.getSelectedItem()))
                                showError("Interval orar invalid");
                            else {
                                File f = new File("D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\reports\\" +
                                        "1stType" + timeStart.getSelectedItem() + "-" + timeFinish.getSelectedItem() + ".txt");
                                try {
                                    FileWriter fileWriter = new FileWriter(f);
                                    fileWriter.write("In intervalul orar dat s-au facut comenzile: \n");
                                    ds.generateType1Report(fileWriter, Integer.parseInt((String) timeStart.getSelectedItem()), Integer.parseInt((String) timeFinish.getSelectedItem()));
                                    fileWriter.close();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    });

                }
                else if(menu.getItemAt(menu.getSelectedIndex()).equals("Produse comandate de .. ori")){
                    content.remove(timeStart);
                    content.remove(timeFinish);
                    content.remove(amount);
                    content.remove(fieldAmount);
                    content.remove(day);
                    content.remove(month);
                    content.remove(fieldDay);
                    content.remove(fieldMonth);
                    content.remove(name2);
                    content.remove(start);
                    content.remove(finish);
                    content.add(times);
                    content.add(fieldTimes);
                    content.revalidate();
                    content.repaint();
                    generate.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                int amount = Integer.parseInt(fieldTimes.getText());
                                File f = new File("D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\reports\\comandat_de_" + amount + "_ori.txt");
                                FileWriter fileWriter = new FileWriter(f);
                                fileWriter.write("S-au comandat de minimum " + amount + " ori produsele \n");
                                ds.generateType2Report(fileWriter,amount);
                                fileWriter.close();
                            } catch (NumberFormatException nfe) {
                                showError("Valoare invalida introdusa");
                            }catch(IOException nfe){
                                showError("Error opening file");
                            }
                        }
                    });

                }
                else if(menu.getItemAt(menu.getSelectedIndex()).equals("Clienti care au comandat de .. ori")){
                    content.remove(timeStart);
                    content.remove(timeFinish);
                    content.remove(name);
                    content.remove(day);
                    content.remove(month);
                    content.remove(fieldDay);
                    content.remove(fieldMonth);
                    content.remove(start);
                    content.remove(finish);
                   // content.add(name2);
                   // content.add(fieldName);
                    content.add(times);
                    content.add(fieldTimes);
                    content.add(amount);
                    content.add(fieldAmount);
                    content.revalidate();
                    content.repaint();
                    generate.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                int times = Integer.parseInt(fieldTimes.getText());
                                int miniSum = Integer.parseInt(fieldAmount.getText());
                                File f = new File("D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\reports\\comandat_de_" + times + "_ori_miniSum_" + miniSum+ ".txt");
                                FileWriter fileWriter = new FileWriter(f);
                                fileWriter.write("Clientii care au comandat de minimum " + miniSum +  " de RON de "+ times +" ori sunt \n");
                                ds.generateType3Report(fileWriter,times,miniSum);
                                fileWriter.close();
                            } catch (NumberFormatException nfe) {
                                showError("Valoare invalida introdusa");
                            }catch(IOException nfe){
                                nfe.printStackTrace();
                                showError("Error opening file");
                            }
                        }
                    });
                }
                else if(menu.getItemAt(menu.getSelectedIndex()).equals("Produsele comandate in data de ..")){
                    content.remove(timeStart);
                    content.remove(timeFinish);
                    content.remove(name);
                    content.remove(name2);
                    content.remove(fieldName);
                    content.remove(times);
                    content.remove(fieldTimes);
                    content.remove(amount);
                    content.remove(fieldAmount);
                    content.remove(start);
                    content.remove(finish);
                    content.add(month);
                    content.add(fieldMonth);
                    content.add(day);
                    content.add(fieldDay);
                    content.revalidate();
                    content.repaint();
                    generate.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                String month = ((String) fieldMonth.getSelectedItem());
                                int day = Integer.parseInt((String) fieldDay.getSelectedItem());
                                File f = new File("D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\reports\\" + "luna_" + month + "_ziua_" + day + ".txt");
                                FileWriter fileWriter = new FileWriter(f);
                                fileWriter.write("Produsele comandate in data de " + month +  ", " + day  +" si cantitatile lor sunt \n");
                                ds.generateType4Report(fileWriter,month,day);
                                fileWriter.close();
                            } catch (NumberFormatException nfe) {
                                showError("Valoare invalida introdusa");
                            }catch(IOException nfe){
                                showError("Error opening file");
                            }

                        }
                    });

                }
            }
        });
    }
    public void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }
}
