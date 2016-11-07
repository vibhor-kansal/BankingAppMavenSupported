package com.capgemini.exceptions;

public class InvalidAccountNumberException extends Exception {

    private String message = null;

    public InvalidAccountNumberException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
