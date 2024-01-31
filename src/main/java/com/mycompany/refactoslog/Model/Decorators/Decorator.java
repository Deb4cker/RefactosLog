package com.mycompany.refactoslog.Model.Decorators;

public interface Decorator {
    void activeSecure(float securePrice);
    void activeExpressDelivery(float deliveryPrice, int time);
}
