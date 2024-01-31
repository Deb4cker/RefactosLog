package com.mycompany.refactoslog.Model.Decorators;

public class OrderDecorator implements Decorator{

    private final Decorator decorator;

    public OrderDecorator(Decorator decorator){
        this.decorator = decorator;
    }

    @Override
    public void activeSecure(float price) {
        decorator.activeSecure(price);
    }

    @Override
    public void activeExpressDelivery(float price, int time) {
        decorator.activeExpressDelivery(price, time);
    }
}
