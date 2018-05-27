package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;

/**
 * Man executable class for the Packer application
 */
public class App {
    public static void main(String[] args) throws APIException {
        System.out.println("Please provide absolute path to the test file : ");
        String input = System.console().readLine();
        System.out.println(Packer.pack(input));
    }
}
