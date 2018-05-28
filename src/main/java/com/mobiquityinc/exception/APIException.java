package com.mobiquityinc.exception;

/**
 * Custom Exception class for the packer app
 */
public class APIException extends Exception {

    public APIException(String message) {
        super(message);
    }
}
