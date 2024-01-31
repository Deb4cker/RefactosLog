package com.mycompany.refactoslog.Model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private String email;
    private String password;
    private String cpf;

    private List<Address> addresses = new ArrayList<>();

    public User(String name, String email, String password, String cpf) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
    }

    public void addAddress(Address address) {
        addresses.add(address);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

}
