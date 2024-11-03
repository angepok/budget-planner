package com.myproject.budgetplanner.expense;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

    // constructor based dependency injection
    public final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Expense> getExpenseById(@PathVariable long id) {
        Expense expense = expenseService.getExpenseById(id);
        if (expense == null) {
            throw new ExpenseNotFoundException("Expense not found with id: " + id);
        }
        return ResponseEntity.ok(expense);

    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) throws ExpenseException {
        Expense createdExpense = expenseService.createExpense(expense);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense updatedExpense) {
        Expense expense = expenseService.updateExpense(id, updatedExpense);
        return ResponseEntity.ok(expense);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        Expense expense = expenseService.getExpenseById(id);
        if (expense == null) {
            throw new ExpenseNotFoundException("Expense not found with id: " + id);
        }
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}
