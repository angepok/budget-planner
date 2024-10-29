package com.myproject.budgetplanner.budgets;

import java.math.BigDecimal;

    
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/budget")


public class BudgetController {

   
    
    
        private final BudgetService budgetService;
    
        public BudgetController(BudgetService budgetService) {
            this.budgetService = budgetService;
        }
    
        @GetMapping("/summary")
        public Budget getBudgetSummary() {
            return budgetService.getBudgetSummary();
        }
    
        @GetMapping("/balance")
        public BigDecimal getBalance() {
            return budgetService.calculateBalance();
        }
    }
    



