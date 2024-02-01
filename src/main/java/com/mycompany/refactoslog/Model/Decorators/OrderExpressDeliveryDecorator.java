package com.mycompany.refactoslog.Model.Decorators;

public class OrderExpressDeliveryDecorator extends OrderDecorator{
    public OrderExpressDeliveryDecorator(CustomService order){
        super(order);
    }

    @Override
    public float calculatePrice() {
        return super.calculatePrice() + 20;
    }

    @Override
    public int calculateTime() {
        return super.calculateTime() - 5;
    }

    @Override
    public int IncreaseSecurity() {
        return super.IncreaseSecurity();
    }
}
