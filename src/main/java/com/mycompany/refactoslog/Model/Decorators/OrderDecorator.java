package com.mycompany.refactoslog.Model.Decorators;

public abstract class OrderDecorator implements Component{

    private final Component component;

    public OrderDecorator(Component component){
        this.component = component;
    }

    @Override
    public float calculatePrice() {
        return component.calculatePrice();
    }

    @Override
    public float calculateTime() {
        return component.calculateTime();
    }
}
