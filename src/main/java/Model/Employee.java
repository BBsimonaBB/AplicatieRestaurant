package Model;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class Employee extends User {
    public Employee(String username, String password, String name, String address, Role role) throws IOException {
        super(username, password, name, address, role);
    }

}
