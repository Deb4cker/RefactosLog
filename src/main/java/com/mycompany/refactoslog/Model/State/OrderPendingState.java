package com.mycompany.refactoslog.Model.State;

import com.mycompany.refactoslog.Model.Order;

public class OrderPendingState extends  OrderState{
    public OrderPendingState(Order order) {
        super(order);
        pendingOrder();
    }

    @Override
    public void pendingOrder() {
        setName("Pending Order");
    }

    @Override
    public void orderCollected() {
        this.order.setState(new OrderCollectedState(order));
    }

    @Override
    public void inTransit() {}

    @Override
    public void delivered() {}
}
