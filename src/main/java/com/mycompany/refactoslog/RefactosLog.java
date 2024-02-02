package com.mycompany.refactoslog;

import java.util.List;
import java.util.Random;

import com.mycompany.refactoslog.Model.Company;
import com.mycompany.refactoslog.Model.Order;
import com.mycompany.refactoslog.Model.Residence;
import com.mycompany.refactoslog.Model.User;
import com.mycompany.refactoslog.Composite.Box;
import com.mycompany.refactoslog.Composite.Item;
import com.mycompany.refactoslog.Composite.Pack;
import com.mycompany.refactoslog.Decorators.*;
import com.mycompany.refactoslog.Services.TotalPrice.TotalPriceCalculator;
import com.mycompany.refactoslog.Services.TotalPrice.TotalPriceCalculatorAdapter;
import com.mycompany.refactoslog.Utils.Transport;

public class RefactosLog {

    public static void main(String[] args) {
        Random random = new Random();
        User user1 = new User("John", "john@example.com", "password", "12345678901");
        User user2 = new User("Jane", "jane@example.com", "password123", "9876543210");

        int region1 = random.nextInt(11);
        int region2 = random.nextInt(11);

        Residence address1 = new Residence("123 Main St", "City", "State", "12345", "Country", "Building", "Floor", "Apartment", "Landmark", "Postal Code", user1, region1);
        Company address2 = new Company("456 Elm St", "City", "State", "54321", "Country", "Building", "Floor", "Apartment", "Landmark", "Postal Code", user2, region2);
    
        user1.addAddress(address1);
        user2.addAddress(address2);
        
        Order order = new Order("123", address1, address2);
        order.addPacks(createPacks());

        // * Voce pode descomentar para testar os diferentes serviços

        CustomService orderServices = new OrderSecureDecorator(new OrderExpressDeliveryDecorator(order)); //Both services
        //CustomService orderServices = new OrderSecureDecorator(order); //Only secure
        //CustomService orderServices = new OrderExpressDeliveryDecorator(order); //Only express
        //CustomService orderServices = order; //No service

        var costCalculator = new TotalPriceCalculatorAdapter(new TotalPriceCalculator());
        float totalPrice = costCalculator.calculateTotal(order, orderServices);
        
        order.setEstimatedTime(orderServices.calculateTime());
        order.setPrice(totalPrice);
        order.setChanceToLose(orderServices.IncreaseSecurity());

        System.out.println(order.toString());

        new Transport(order).deliver();
    }

    public static List<Pack> createPacks(){

        //Criei 7 itens, uns serão encaixotados e outros não
        Pack i1 = new Item("Celular" , 0.3f);
        Pack i2 = new Item("Notebook" , 2.0f);
        Pack i3 = new Item("Vassoura" , 1.5f);
        Pack i4 = new Item("Cadeira" , 3.0f);
        Pack i5 = new Item("Mesa" , 5.0f);
        Pack i6 = new Item("Caderno" , 0.1f);
        Pack i7 = new Item("Lapis" , 0.01f);

        //Criei 2 caixas com os itens
        List<Pack> boxContent1 = List.of(i1, i2);
        List<Pack> boxContent2 = List.of(i6, i7);

        Box b1 = new Box("Caixa 1");
        Box b2 = new Box("Caixa 2");

        //Adicionei os itens nas caixas
        b1.add(boxContent1);
        b2.add(boxContent2);

        //O pedido é 2 caixas e 3 itens soltos
        return List.of(b1, b2, i3, i4, i5);
    }
}
