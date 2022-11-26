package Model;

import java.io.IOException;

public class Admin extends User{
    public Admin(String username, String password, String name, String address, Role role) throws IOException {
        super(username, password, name, address, role);
    }
}
