package com.capgemini.exceptions;

public class InsufficientInitialBalanceException extends Exception {

    private String message = null;

    public InsufficientInitialBalanceException(String message) {
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
