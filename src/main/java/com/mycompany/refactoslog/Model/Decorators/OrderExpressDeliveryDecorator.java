package com.mycompany.refactoslog.Model.Decorators;

public class OrderExpressDeliveryDecorator extends OrderDecorator{
    public OrderExpressDeliveryDecorator(Decorator order){
        super(order);
    }

    @Override
    public void activeSecure(float price) {
        super.activeSecure(price);
    }

    @Override
    public void activeExpressDelivery(float price, int time) {
        super.activeExpressDelivery(price + 20, time - 5);
    }

}
