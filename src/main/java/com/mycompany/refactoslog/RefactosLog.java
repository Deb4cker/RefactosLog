/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.refactoslog;

import com.mycompany.refactoslog.Model.Address;
import com.mycompany.refactoslog.Model.User;

/**
 *
 * @author nicolas
 */
public class RefactosLog {

    public static void main(String[] args) {
        User user1 = new User("John", "john@example.com", "password", "12345678901");
        Address address = new Address("123 Main St", "City", "State", "12345", "Country", "Building", "Floor", "Apartment", "Landmark", "Postal Code", user1);
    
        user1.addAddress(address);
        System.out.println(user1.getAddresses().get(0).getStreet());

        
    }


}
