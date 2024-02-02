package com.mycompany.refactoslog.Model;

import com.mycompany.refactoslog.Visitor.Visitor;

public class Company extends Address{

    public Company(String street, String city, String state, String zipCode, String country, String number,
            String complement, String neighborhood, String phone, String email, User user, int Region) {
        super(street, city, state, zipCode, country, number, complement, neighborhood, phone, email, user, Region);
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitCompany(this);
    }
}
