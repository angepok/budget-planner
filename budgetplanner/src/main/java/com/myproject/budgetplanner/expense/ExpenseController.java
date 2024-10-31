package com.myproject.budgetplanner.expense;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class ExpenseController {
    //constructor based dependency injection
    public final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @GetMapping
    public List<Expense> getAllExpenses(){
        return expenseService.getAllExpenses();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Expense getExpenseById(@PathVariable long id){
        return expenseService.getExpenseById(id);
    }

    @PostMapping(produces = "application/json")
    public Expense createExpense(@RequestBody Expense expense) throws ExpenseException{
        return expenseService.createExpense(expense);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteExpense(Expense expense){
        expenseService.deleteExpense(expense);
    }
}


