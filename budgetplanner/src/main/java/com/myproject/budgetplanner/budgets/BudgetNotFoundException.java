package com.myproject.budgetplanner.budgets;

import java.time.Month;

public class BudgetNotFoundException extends RuntimeException {

    public BudgetNotFoundException(String message) {
        super(message);
    }

    public BudgetNotFoundException(Long budgetId) {
        super("Budget not found with id: " + budgetId);
    }

    public BudgetNotFoundException(int year, Month month) {
        super("Budget not found for year: " + year + ", month: " + month);
    }
}
