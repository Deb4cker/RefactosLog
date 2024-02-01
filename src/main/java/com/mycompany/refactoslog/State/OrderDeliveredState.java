package com.mycompany.refactoslog.State;

import com.mycompany.refactoslog.Model.Order;

public class OrderDeliveredState extends OrderState {
    public OrderDeliveredState(Order order) {
        super(order);
        delivered();
    }

    @Override
    public void pendingOrder() {}

    @Override
    public void orderCollected() {}

    @Override
    public void inTransit() {
        this.order.setState(new OrderInTransitState(order));
    }

    @Override
    public void delivered() {
        setName("Order Delivered!");
    }
}
