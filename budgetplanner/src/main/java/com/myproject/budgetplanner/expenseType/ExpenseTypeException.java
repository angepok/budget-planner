package com.myproject.budgetplanner.expenseType;



 /*ExpenseTypeException would typically be thrown when a duplicate expense type is being added, 
 such as adding an expense type that already exists in the database. 
 This helps provide a more specific error message and handle this unique situation gracefully in your application code.
 */

public class ExpenseTypeException extends Exception {
    public ExpenseTypeException(String message) {
        super(message);
    }

}



