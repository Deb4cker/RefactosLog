package com.mycompany.refactoslog.Model;

import com.mycompany.refactoslog.Model.Decorators.Decorator;
import com.mycompany.refactoslog.Model.State.OrderPendingState;
import com.mycompany.refactoslog.Model.State.OrderState;

public class Order implements Decorator {
    private OrderState state;
    private String code;
    private Address fromAddress;
    private Address toAddress;
    private User sender;
    private User receiver;
    private int estimatedTime;
    private float price;
    private int chanceToLose;
    private Decorator orderDecorators;

    public Order(String code, Address fromAddress, Address toAddress) {
        this.state = new OrderPendingState(this);
        this.code = code;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        estimatedTime = 10;
        chanceToLose = 50;
        price = 10.0f;
        sender = fromAddress.getUser();
        receiver = toAddress.getUser();
    }

    public void orderCollected(){this.state.orderCollected();}
    public void inTransit() {this.state.inTransit();}
    public void delivered() {this.state.delivered();}

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Address getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(Address fromAddress) {
        this.fromAddress = fromAddress;
    }

    public Address getToAddress() {
        return toAddress;
    }

    public void setToAddress(Address toAddress) {
        this.toAddress = toAddress;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setDecorator(Decorator decorators){
        this.orderDecorators = decorators;
    }
    @Override
    public void activeSecure(float securePrice) {
        setPrice(price + securePrice);
        setChanceToLose(0);
    }

    @Override
    public void activeExpressDelivery(float deliveryPrice, int time){
        setPrice(deliveryPrice);
        setEstimatedTime(estimatedTime - time);
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getChanceToLose() {
        return chanceToLose;
    }

    public void setChanceToLose(int chanceToLose) {
        this.chanceToLose = chanceToLose;
    }

    @Override
    public String toString() {
        return "Order{" +
                "state=" + state +
                ", code='" + code + '\'' +
                ", fromAddress=" + fromAddress +
                ", toAddress=" + toAddress +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", estimatedTime=" + estimatedTime +
                ", price=" + price +
                ", chanceToLose=" + chanceToLose +
                ", orderDecorators=" + orderDecorators +
                '}';
    }
}
