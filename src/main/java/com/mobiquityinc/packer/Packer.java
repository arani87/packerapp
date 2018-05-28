package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.InputObject;
import com.mobiquityinc.model.Item;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Packer class with the main business logic to apply the optimal packing algorithm.
 *
 * @author Arani
 */
public class Packer {

    /**
     * Private Constructor to hide the implicit public one
     */
    private Packer() {
    }

    private static final Util util = new Util();

    /**
     * @param filePath accepts the absolute path to a test file as a String
     * @return result with the optimal package structure as String.
     */
    public static String pack(String filePath) throws APIException {
        StringBuilder finalResult = new StringBuilder();
        List<String> fileContents = util.readFile(filePath);
        List<InputObject> inputObjects = new ArrayList<>();
        for (String line : fileContents) {
            inputObjects.add(util.parseLineToItemObject(line));
        }
        validateConstraints(inputObjects);
        for (InputObject inputObject : inputObjects) {
            finalResult = finalResult.append(findOptimalItems(inputObject));
        }
        return finalResult.toString();
    }

    /**
     * This method validates the additional constraints
     *
     * @param inputObjects Input file parsed into Object model
     * @throws APIException in case of violation of any validation constraints
     */
    protected static void validateConstraints(List<InputObject> inputObjects) throws APIException {
        for (InputObject inputObject : inputObjects) {
            if (inputObject.getPackageWeightCapacity() > 100) {
                throw new APIException("Max Package weight constraint violated");
            }
            for (Item item : inputObject.getItemChoiceList()) {
                if (item.getWeight() > 100) {
                    throw new APIException("Max Item weight constraint violated");
                }
                if (item.getPrice() > 100) {
                    throw new APIException("Max Item price constraint violated");
                }
            }
        }
    }

    /**
     * Apply greedy approximation algorithm to find optimal items
     * 1. Sort items based on price in descending order. (Highest price first), and weight in ascending order.
     * 2. Iterate over the sorted items. If : Capacity of package (W) > weight of item(w) + (weight of items already selected): then: add this item to selected list
     *
     * @param input the InputObject POJO representing each line of the input text file
     * @return required optimum item list in a comma separated String format
     */
    private static String findOptimalItems(InputObject input) {
        String optimumItems = "";
        List<Item> itemList = input.getItemChoiceList();
        itemList.sort(Comparator.comparingDouble(Item::getPrice).reversed().thenComparing(Item::getWeight));

        double collectiveWeightOfItems = 0.0;
        for (Item item : itemList) {
            if ((collectiveWeightOfItems + item.getWeight()) <= input.getPackageWeightCapacity()) {
                collectiveWeightOfItems = collectiveWeightOfItems + item.getWeight();
                optimumItems = "".equalsIgnoreCase(optimumItems) ? String.valueOf(item.getIndex()) : optimumItems + ", " + item.getIndex();
            }
        }
        return optimumItems.concat("\n");

    }


}
