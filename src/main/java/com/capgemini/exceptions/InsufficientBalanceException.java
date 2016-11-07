package com.capgemini.exceptions;

public class InsufficientBalanceException extends Exception {

    private String message = null;

    public InsufficientBalanceException(String message) {
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
