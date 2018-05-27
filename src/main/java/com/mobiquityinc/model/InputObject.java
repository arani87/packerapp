package com.mobiquityinc.model;

import java.util.List;

/**
 * This is a representation of each line of the input text.
 * It contains the package weight limit along with the item options to choose from as a list of Items
 * @see Item
 */
public class InputObject {
    private double packageWeightCapacity;
    private List<Item> itemChoiceList;

    public double getPackageWeightCapacity() {
        return packageWeightCapacity;
    }

    public void setPackageWeightCapacity(double packageWeightCapacity) {
        this.packageWeightCapacity = packageWeightCapacity;
    }

    public List<Item> getItemChoiceList() {
        return itemChoiceList;
    }

    public void setItemChoiceList(List<Item> itemChoiceList) {
        this.itemChoiceList = itemChoiceList;
    }
}
