package com.mycompany.refactoslog.Model.Composite;

public class Item implements Pack{
    private String name;
    private float weight;

    public Item(String name, float weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    @Override
    public float getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
