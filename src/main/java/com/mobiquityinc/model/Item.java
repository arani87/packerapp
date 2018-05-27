package com.mobiquityinc.model;

/**
 * POJO depicting each item that are available as options that can be placed in the package.
 */
public class Item {
    private int index;
    private double weight;
    private double price;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
