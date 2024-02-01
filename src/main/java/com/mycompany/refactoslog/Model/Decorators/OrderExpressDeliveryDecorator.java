package com.mycompany.refactoslog.Model.Decorators;

public class OrderExpressDeliveryDecorator extends OrderDecorator{
    public OrderExpressDeliveryDecorator(Component order){
        super(order);
    }

    @Override
    public float calculatePrice() {
        return super.calculatePrice() + 20;
    }

    @Override
    public float calculateTime() {
        return super.calculateTime() - 5;
    }
}
