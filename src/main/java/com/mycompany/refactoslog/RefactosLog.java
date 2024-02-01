/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.refactoslog;

import com.mycompany.refactoslog.Model.Address;
import com.mycompany.refactoslog.Model.Order;
import com.mycompany.refactoslog.Model.User;
import com.mycompany.refactoslog.Model.Decorators.*;
import com.mycompany.refactoslog.Model.State.OrderCollectedState;
import com.mycompany.refactoslog.Model.State.OrderPendingState;

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
        
        Component decorator = new OrderSecureDecorator(new OrderExpressDeliveryDecorator(order));

        decorator.calculatePrice();
        decorator.calculateTime();

        System.out.println(
            "Dados do pedido:\n" +
            "Código: " + order.getCode() + "\n" +
            "Endereço de origem: " + order.getFromAddress().getStreet() + "\n" +
            "Endereço de destino: " + order.getToAddress().getStreet() + "\n" +
            "Preço: " + order.getPrice() + "\n" +
            "Tempo estimado: " + order.getEstimatedTime() + "\n" +
            "Chance de perder: " + order.getChanceToLose() + "\n" +
            "Status: " + order.getState().getName() + "\n"
        );
        
        new Transport(order).start();
    }
}

class Transport extends Thread{

    private boolean isRunning = false;
    private Order order;

    public Transport(Order order){
        this.order = order;
    }

    @Override
    public void run() {
        isRunning = true;
        while (isRunning) {

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {e.printStackTrace();}

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
        }
    }

    public void shutDown(){
        isRunning = false;
    }
}