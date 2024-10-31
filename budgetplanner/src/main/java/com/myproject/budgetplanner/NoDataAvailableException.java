package com.myproject.budgetplanner;

public class NoDataAvailableException extends  RuntimeException {

    public NoDataAvailableException(String message) {
        super(message);
    }
}
