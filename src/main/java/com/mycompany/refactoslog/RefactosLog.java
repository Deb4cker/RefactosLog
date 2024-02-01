/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.refactoslog;

import java.util.List;

import com.mycompany.refactoslog.Model.Address;
import com.mycompany.refactoslog.Model.Order;
import com.mycompany.refactoslog.Model.User;
import com.mycompany.refactoslog.Model.Composite.Box;
import com.mycompany.refactoslog.Model.Composite.Item;
import com.mycompany.refactoslog.Model.Composite.Pack;
import com.mycompany.refactoslog.Model.Decorators.*;
import com.mycompany.refactoslog.Model.State.OrderCollectedState;
import com.mycompany.refactoslog.Model.State.OrderPendingState;
import com.mycompany.refactoslog.Services.TotalPrice.TotalPriceCalculator;
import com.mycompany.refactoslog.Services.TotalPrice.TotalPriceCalculatorAdapter;

/**
 *
 * @author nicolas
 */
public class RefactosLog {

    public static void main(String[] args) {
        User user1 = new User("John", "john@example.com", "password", "12345678901");
        User user2 = new User("Jane", "jane@example.com", "password123", "9876543210");

        Address address1 = new Address("123 Main St", "City", "State", "12345", "Country", "Building", "Floor", "Apartment", "Landmark", "Postal Code", user1, 9);
        Address address2 = new Address("456 Elm St", "City", "State", "54321", "Country", "Building", "Floor", "Apartment", "Landmark", "Postal Code", user2, 1);
    
        user1.addAddress(address1);
        user2.addAddress(address2);
        
        Order order = new Order("123", address1, address2);
        order.addPacks(createPacks());
        
        CustomService decorator = new OrderSecureDecorator(new OrderExpressDeliveryDecorator(order));
        
        var costCalculator = new TotalPriceCalculatorAdapter(new TotalPriceCalculator());
        float totalPrice = costCalculator.calculateTotal(order, decorator);
        
        order.setEstimatedTime(decorator.calculateTime());
        order.setPrice(totalPrice);

        System.out.println(order.toString());

        new Transport(order).start();
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

class Transport extends Thread{

    private boolean isRunning = false;
    private Order order;

    public Transport(CustomService order){
        this.order = order instanceof Order? (Order)order : null;
    }

    @Override
    public void run() {
        if (order != null) {
            
            isRunning = true;
            while (isRunning) {
                
                var orderState = order.getState();
                if(orderState instanceof OrderPendingState){
                    order.orderCollected();
                    System.out.println(orderState.getName());
                }
                else if (orderState instanceof OrderCollectedState) {
                    order.inTransit();
                    System.out.println(orderState.getName());
                } else {
                    order.delivered();
                    System.out.println(orderState.getName());
                    shutDown();
                }
                
                try {
                    Thread.sleep(order.getEstimatedTime() * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void shutDown(){
        isRunning = false;
    }
}