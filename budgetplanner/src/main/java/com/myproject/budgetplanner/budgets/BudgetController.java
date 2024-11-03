package com.myproject.budgetplanner.budgets;

import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/budget")
public class BudgetController {

    private final BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    /**
     * Retrieves the budget details for a specified year and month.
     *
     * <p>
     * This endpoint allows users to obtain budget data for a given year and month,
     * including
     * total income, total expenses, and balance for that period. The month
     * parameter can be
     * provided as a case-insensitive month name (e.g., "January" or "JANUARY").
     * 
     */

    @GetMapping("/{year}/{month}")
    public ResponseEntity<Budget> getBudgetForMonth(@PathVariable int year, @PathVariable String month) {
        try {
            Month monthEnum = Month.valueOf(month.toUpperCase()); // Convert string to Month
            Budget budget = budgetService.getBudgetForMonth(year, monthEnum);
            return ResponseEntity.ok(budget);
        } catch (IllegalArgumentException e) {
            // Return 400 Bad Request if the month is invalid
            return ResponseEntity.badRequest().body(null);
        }
    }

    /*
     * @GetMapping("/{year}/{month}")
     * public ResponseEntity<Budget> getBudgetForMonth(@PathVariable int
     * year, @PathVariable Month month) {
     * Budget budget = budgetService.getBudgetForMonth(year, month);
     * return ResponseEntity.ok(budget);
     * }
     */

}
