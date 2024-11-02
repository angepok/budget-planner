package com.myproject.budgetplanner.expense;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/expense")
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
    public Optional<Expense> getExpenseById(@PathVariable long id){
        return expenseService.getExpenseById(id);
    }

    @PostMapping(produces = "application/json")
    public Expense createExpense(@RequestBody Expense expense) throws ExpenseException{
        return expenseService.createExpense(expense);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteExpense(Long id){
        expenseService.deleteExpense(id);
    }
}


