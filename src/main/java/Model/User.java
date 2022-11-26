package Model;

import Data.Serializator;

import java.io.*;

public class User implements Serializable {
    private String username;
    private String password;
    private String name;
    private String address;
    private Role role;
    public enum Role {
        ADMIN, EMPLOYEE, CLIENT;
    }
    private static final long  serialVersionUID = 42l;

    public User(String username, String password, String name, String address, Role role) throws IOException {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.role = role;

        Serializator.writeObject(this,"D:\\An2\\Sem2\\TP\\PT2022_30223_Tivadar_Maria_Assignment_4\\users\\" + username);

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}