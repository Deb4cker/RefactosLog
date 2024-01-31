package com.mycompany.refactoslog.Model.State;

import com.mycompany.refactoslog.Model.Order;

public class OrderCollectedState extends OrderState {
    public OrderCollectedState(Order order) {
        super(order);
        orderCollected();
    }

    @Override
    public void pendingOrder() {
        this.order.setState(new OrderPendingState(order));
    }

    @Override
    public void orderCollected() {
        setName("Order Collected in senders address!");
    }

    @Override
    public void inTransit() {
        this.order.setState(new OrderInTransitState(order));
    }

    @Override
    public void delivered() {}
}
