package com.mycompany.refactoslog.Decorators;

public abstract class OrderDecorator implements CustomService{

    private CustomService component;

    public OrderDecorator(CustomService component){
        this.component = component;
    }

    @Override
    public int IncreaseSecurity() {
        return component.IncreaseSecurity();
    }

    @Override
    public float calculatePrice() {
        return component.calculatePrice();
    }

    @Override
    public int calculateTime() {
        return component.calculateTime();
    }
}
