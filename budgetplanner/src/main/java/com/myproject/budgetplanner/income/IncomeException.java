package com.myproject.budgetplanner.income;

public class IncomeException extends Exception {

   
//public class IncomeException extends RuntimeException {
    
    // Default constructor with a general message
    public IncomeException() {
        super("Income-related error occurred.");
    }

    // Constructor that accepts a custom message
    public IncomeException(String message) {
        super(message);
    }

    // Constructor that accepts a message and a cause
    public IncomeException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor that accepts a cause
    public IncomeException(Throwable cause) {
        super(cause);
    }
}



