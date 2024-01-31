package com.mycompany.refactoslog.Model.Decorators;

public class OrderSecureDecorator extends OrderDecorator implements Decorator {

    public OrderSecureDecorator(Decorator decorator) {
        super(decorator);
    }

    @Override
    public void activeSecure(float price) {
        super.activeSecure(price + 20);
    }

    @Override
    public void activeExpressDelivery(float price, int time) {
        super.activeExpressDelivery(price, time);
    }
}
