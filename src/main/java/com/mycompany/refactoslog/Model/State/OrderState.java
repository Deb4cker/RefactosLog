package com.mycompany.refactoslog.Model.State;

import com.mycompany.refactoslog.Model.Order;

public abstract class OrderState {

    protected String name = "Order Processing";
    protected Order order;

    public OrderState(Order order){
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return name;
    }

    public abstract void pendingOrder();
    public abstract void orderCollected();
    public abstract void inTransit();
    public abstract void delivered();


}
