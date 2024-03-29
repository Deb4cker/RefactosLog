package com.mycompany.refactoslog.Decorators;

public class OrderSecureDecorator extends OrderDecorator{

    public OrderSecureDecorator(CustomService decorator) {
        super(decorator);
    }

    @Override
    public int IncreaseSecurity() {
        return super.IncreaseSecurity() - 50;
    }

    @Override
    public float calculatePrice() {
        return super.calculatePrice() + 20;
    }

    @Override
    public int calculateTime() {
        return super.calculateTime();
    }
}
