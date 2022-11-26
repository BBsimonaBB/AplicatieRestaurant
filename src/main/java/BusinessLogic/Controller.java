package BusinessLogic;

import BusinessLogic.BaseProduct;
import BusinessLogic.DeliveryService;
import GUI.EmployeeView;
import GUI.LogInView;
import Model.Admin;
import Model.Client;
import Model.Employee;
import Model.User;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    private DeliveryService ds = new DeliveryService();
    private LogInView logInView;
    private ArrayList<User> users;

    public Controller(DeliveryService ds,LogInView logInView,ArrayList<User> users) {
        this.ds = ds;
        this.logInView = logInView;
        this.users = users;
    }

}

