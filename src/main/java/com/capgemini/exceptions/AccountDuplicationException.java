package com.capgemini.exceptions;

public class AccountDuplicationException extends Exception {

    private String message = null;

    public AccountDuplicationException(String message) {
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
