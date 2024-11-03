package com.myproject.budgetplanner.budgets;

import java.math.BigDecimal;
//import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/budget")
public class BudgetController {
    /* 
    
    private final BudgetService budgetService;
    
        @Autowired
        public BudgetController(BudgetService budgetService) {
            this.budgetService = budgetService;
        }
    
        @GetMapping("/summary")
        public Budget getBudgetSummary(String name) {
            return budgetService.getBudgetSummary(name);
        }
    
        @GetMapping("/balance")
        public BigDecimal getBalance() {
            return budgetService.calculateBalance();
        }

        //@GetMapping("/monthly")
        //public MonthlyBudget getMonthlyBudget(@RequestParam int year, @RequestParam Month month) {
        //    return budgetService.calculateMonthlyBudget(year, month);
        //}

        //@GetMapping("/yearly")
        //public YearlyBudget getYearlyBudget(@RequestParam int year) {
        //   return budgetService.calculateYearlyBudget(year);
        //}
        */
    }
    



