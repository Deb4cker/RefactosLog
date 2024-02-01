package com.mycompany.refactoslog.Model.Decorators;

public class OrderSecureDecorator extends OrderDecorator{

    public OrderSecureDecorator(OrderDecorator decorator) {
        super(decorator);
    }

    @Override
    public float calculatePrice() {
        return super.calculatePrice() + 20;
    }

    @Override
    public float calculateTime() {
        return super.calculateTime();
    }
}
