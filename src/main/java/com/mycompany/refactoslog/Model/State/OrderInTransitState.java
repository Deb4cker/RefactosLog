package com.mycompany.refactoslog.Model.State;

import com.mycompany.refactoslog.Model.Order;

public class OrderInTransitState extends OrderState {
    public OrderInTransitState(Order order) {
        super(order);
        inTransit();
    }

    @Override
    public void pendingOrder() {}

    @Override
    public void orderCollected() {
        this.order.setState(new OrderCollectedState(order));
    }

    @Override
    public void inTransit() {
        setName("Order In Transit");
    }

    @Override
    public void delivered() {
        this.order.setState(new OrderDeliveredState(order));
    }
}
