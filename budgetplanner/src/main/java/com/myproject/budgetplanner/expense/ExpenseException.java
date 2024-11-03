package com.myproject.budgetplanner.expense;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/* 
public class ExpenseException extends Exception {

// Default constructor with a general message
    public ExpenseException() {
        super("Income-related error occurred.");
    }

    // Constructor that accepts a custom message
    public ExpenseException(String message) {
        super(message);
    }

    // Constructor that accepts a message and a cause
    public ExpenseException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor that accepts a cause
    public ExpenseException(Throwable cause) {
        super(cause);
    }
*/

@ControllerAdvice
public class ExpenseException extends Exception {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public String handleEntityNotFoundException(EntityNotFoundException e, Model model) {
        String message = e.getMessage();
        model.addAttribute("errorMessage", message);
        return "error";
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handleGetMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return "redirect:/expenses";
    }

}



