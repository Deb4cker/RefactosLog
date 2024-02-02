package com.mycompany.refactoslog.Utils;

import java.util.Random;

import com.mycompany.refactoslog.Model.Order;
import com.mycompany.refactoslog.State.OrderCollectedState;
import com.mycompany.refactoslog.State.OrderPendingState;
import com.mycompany.refactoslog.Visitor.ConcreteVisitor;

public class Transport extends Thread{

    private boolean isRunning = false;
    private Order order;
    private final Random random = new Random();

    public Transport(Order order){
        this.order = order;
    }

    @Override
    public void run() {            
        isRunning = true;
        boolean orderLost = false;
        while (isRunning && !orderLost) {
            
            var orderState = order.getState();
            if(orderState instanceof OrderPendingState){
                order.orderCollected();
                System.out.println(orderState.getName());
            }
            else if (orderState instanceof OrderCollectedState) {
                order.inTransit();
                System.out.println(orderState.getName());
            } else {
                ConcreteVisitor visitor = new ConcreteVisitor();
                var address = order.getFromAddress();
                visitor.generateDeliveryReceipt(address);
                order.delivered();
                System.out.println(orderState.getName());
                shutDown();
            }
            
            try {
                Thread.sleep(order.getEstimatedTime() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //A random chance to lose the order wiht a percent coeficient
            if(random.nextInt(100) < order.getChanceToLose()){
                orderLost = true;
                System.out.println("Order lost");
            }
        }
    }
    public void deliver(){
        isRunning = true;
        start();
    }
    public void shutDown(){
        isRunning = false;
    }
}
