package com.mycompany.refactoslog.Composite;

import java.util.ArrayList;
import java.util.List;

public class Box implements Pack{

    private String name;
    private float weight;
    private List<Pack> packs = new ArrayList<>();

    public Box(String name) {
        this.name = name;
    }

    public void add(Pack pack){
        packs.add(pack);
    }

    public void add(List<Pack> packs){
        this.packs.addAll(packs);
    }

    public void remove(Pack pack){
        packs.remove(pack);
    }

    public List<Pack> getPacks() {
        return packs;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void updateWeight(){
        this.weight = packs.stream().map(Pack::getWeight).reduce(0f, Float::sum);
    }

    @Override
    public float getWeight() {
        updateWeight();
        return weight;
    }
}
