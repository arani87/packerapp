package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.InputObject;
import com.mobiquityinc.model.Item;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class supporting the packer app.
 * This class provided functionality like reading content from file, validating the content, parsing content to POJO
 */
class Util {

    private static final Logger logger = LoggerFactory.getLogger(Util.class);

    /**
     * @param filePath the absolute path to the input file
     * @return contents of the file as a List of Strings. Where each item of the list depicts a single line of the file
     */
    List<String> readFile(String filePath) throws APIException {
        List<String> contentList;
        try {
            File f = new File(filePath);
            contentList = FileUtils.readLines(f, "UTF-8");
        } catch (IOException e) {
            logger.error("Exception while reading input file : ", e);
            throw new APIException("Unable to read file");
        }
        return contentList;
    }

    /**
     * @param lineContent the text to be parsed
     * @return parsed content
     * @throws APIException in case of invalid input content
     */
    InputObject parseLineToItemObject(String lineContent) throws APIException {
        InputObject inputObj = new InputObject();
        if (StringUtils.isEmpty(lineContent)) {
            throw new APIException("Non parse-able file content");
        }

        int indexOfColon = lineContent.indexOf(':');
        if (indexOfColon == -1) {
            logger.warn("Could not parse line content = {0}", lineContent);
            throw new APIException("Incorrect content. Weight not specified as per specification");
        }
        try {
            Double packageWeightLimit = Double.parseDouble(lineContent.substring(0, indexOfColon).trim());
            inputObj.setPackageWeightCapacity(packageWeightLimit);
            inputObj.setItemChoiceList(getListOfItemsFromInputLine(lineContent));
        } catch (NumberFormatException nfe) {
            logger.warn("Could not parse package weight limit = {0}", lineContent.substring(0, indexOfColon));
            throw new APIException("Incorrect content. Weight not specified as per specification");
        }
        return inputObj;
    }

    /**
     * @param lineContent single line of input file as String
     * @return list of Item object
     * @throws APIException in case of incorrect input values
     */
    List<Item> getListOfItemsFromInputLine(String lineContent) throws APIException {
        List<Item> itemList = new ArrayList<>();
        lineContent = lineContent.substring(lineContent.indexOf('('), lineContent.length());
        String[] content = lineContent.split("[)]");
        try {
            for (String val : content) {
                val = val.replace("(", "");
                Item item = new Item();
                String[] params = val.split(",");
                item.setIndex(Integer.parseInt(params[0].trim()));
                item.setWeight(Double.parseDouble(params[1].trim()));
                item.setPrice(Double.parseDouble(params[2].substring(1, params[2].length()).trim()));
                itemList.add(item);
            }
        } catch (NumberFormatException nfe) {
            logger.error("Error during parsing", nfe);
            throw new APIException("Input values specified have incorrect syntax");
        }

        return itemList;
    }

}
