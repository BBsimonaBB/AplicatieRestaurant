package Model;

import java.io.IOException;

public class Client extends User{
    public Client(String username, String password, String name, String address, Role role) throws IOException {
        super(username, password, name, address, role);
    }
}
