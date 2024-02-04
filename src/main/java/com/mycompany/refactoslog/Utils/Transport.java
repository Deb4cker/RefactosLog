package com.mycompany.refactoslog.Utils;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

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

        JFrame frame = new JFrame();
        JTextArea area = new JTextArea("Order Processing... \n");
        area.setBounds(10, 30, 600, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(area);
        frame.setSize(700, 700);
        frame.setLayout(null);
        frame.setVisible(true);

        while (isRunning && !orderLost) {

            var orderState = order.getState();
            if(orderState instanceof OrderPendingState){
                order.orderCollected();
                area.append(orderState.getName() + "\n");
            }
            else if (orderState instanceof OrderCollectedState) {
                order.inTransit();
                area.append(orderState.getName() + "\n");
            } else {
                order.delivered();
                area.append(orderState.getName() + "\n");
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
                area.append("Order lost");
            }
        }
        area.append(order.getState().getName());
        ConcreteVisitor visitor = new ConcreteVisitor();
        var address = order.getFromAddress();
        visitor.generateDeliveryReceipt(address);
    }
    public void deliver(){
        isRunning = true;
        start();
    }
    public void shutDown(){
        isRunning = false;
    }
}
