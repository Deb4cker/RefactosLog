package com.mycompany.refactoslog.Model;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.refactoslog.Composite.Pack;
import com.mycompany.refactoslog.Decorators.CustomService;
import com.mycompany.refactoslog.State.OrderPendingState;
import com.mycompany.refactoslog.State.OrderState;

public class Order implements CustomService  {
    private OrderState state;
    private String code;
    private Address fromAddress;
    private Address toAddress;
    private User sender;
    private User receiver;
    private int estimatedTime;
    private float price;
    private int chanceToLose;
    private float weight;

    private List<Pack> packs = new ArrayList<>();

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

    public void updateWeight(){
        this.weight = packs.stream().map(Pack::getWeight).reduce(0f, Float::sum);
    }

    public float getWeight() {
        updateWeight();
        return weight;
    }

    public void addPack(Pack pack){
        packs.add(pack);
        updateWeight();
    }

    public void removePack(Pack pack){
        packs.remove(pack);
        updateWeight();
    }

    public List<Pack> getPacks() {
        return packs;
    }

    public void addPacks(List<Pack> packs){
        this.packs.addAll(packs);
        updateWeight();
    }

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

    public void activeSecure(float securePrice) {
        setPrice(price + securePrice);
        setChanceToLose(0);
    }

    
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
        return "Order{" + "\n" +
                "state: " + state + "\n" +
                "* code: '" + code + '\'' + "\n" +
                "* fromAddress: " + fromAddress.toString() + "\n" +
                "* toAddress: " + toAddress.toString() + "\n" +
                "* sender: " + sender.getName() + "\n" +
                "* receiver: " + receiver.getName() + "\n" +
                "* estimatedTime: " + estimatedTime + " days\n" +
                "* weight: " + weight + "kg\n" +
                "* price: $" + price + "\n" +
                "* chanceToLose: %" + chanceToLose + "\n" +
                '}';
    }

    @Override
    public float calculatePrice() {
        return 10;
    }

    @Override
    public int calculateTime() {
        return 10;
    }

    @Override
    public int IncreaseSecurity() {
        return 50;
    }
}
