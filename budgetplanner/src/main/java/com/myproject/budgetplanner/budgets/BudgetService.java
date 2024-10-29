package com.myproject.budgetplanner.budgets;

import com.myproject.budgetplanner.expense.ExpenseService;
import com.myproject.budgetplanner.income.IncomeService;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public class BudgetService {

    private final IncomeService incomeService;
    private final ExpenseService expenseService;

    public BudgetService(IncomeService incomeService, ExpenseService expenseService){
        this.incomeService = incomeService;
        this.expenseService = expenseService;
    }

    public BigDecimal calculateTotalIncome(){
        return incomeService.getTotalIncome();
    }

    public BigDecimal calculateTotalExpenses(){
        return expenseService.getTotalAmount(expenseService.getAllExpenses());
    }

    public BigDecimal calculateBalance(){
        return calculateTotalIncome().subtract(calculateTotalExpenses());
    }


    public Budget getBudgetSummary() {
        BigDecimal totalIncome = calculateTotalIncome();
        BigDecimal totalExpenses = calculateTotalExpenses();
        BigDecimal balance = totalIncome.subtract(totalExpenses);
        return new Budget(totalIncome, totalExpenses, balance);
    }
}

