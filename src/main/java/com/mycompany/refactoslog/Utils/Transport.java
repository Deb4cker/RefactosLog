package com.mycompany.refactoslog.Utils;

import com.mycompany.refactoslog.Model.Order;
import com.mycompany.refactoslog.Model.State.OrderCollectedState;
import com.mycompany.refactoslog.Model.State.OrderPendingState;

public class Transport extends Thread{

    private boolean isRunning = false;
    private Order order;

    public Transport(Order order){
        this.order = order;
    }

    @Override
    public void run() {            
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
    public void deliver(){
        isRunning = true;
        start();
    }
    public void shutDown(){
        isRunning = false;
    }
}
