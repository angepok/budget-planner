package com.myproject.budgetplanner.budgets;

 
import com.myproject.budgetplanner.NoDataAvailableException;
import com.myproject.budgetplanner.expense.Expense;
import com.myproject.budgetplanner.expense.ExpenseService;
import com.myproject.budgetplanner.income.Income;
import com.myproject.budgetplanner.income.IncomeService;
import java.math.BigDecimal;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {

    @Autowired
    private final BudgetRepository budgetRepository;
    private final IncomeService incomeService;
    private final ExpenseService expenseService;


    public BudgetService(BudgetRepository budgetRepository, IncomeService incomeService, ExpenseService expenseService){
        this.budgetRepository = budgetRepository;
        this.incomeService = incomeService;
        this.expenseService = expenseService;
    } 

    // Calculation methods
    public BigDecimal calculateTotalIncome(int year,Month month){
        BigDecimal totalIncome = incomeService.getIncomeByMonth(year, month);
        return totalIncome != null ? totalIncome : BigDecimal.ZERO;
    }

    public BigDecimal calculateTotalExpenses(int year, Month month){
        BigDecimal totalExpense = expenseService.getExpensesByMonth(year, month);
        return totalExpense != null ? totalExpense : BigDecimal.ZERO;
    }

    public BigDecimal calculateBalance(int year, Month month){
        return calculateTotalIncome(year, month).subtract(calculateTotalExpenses(year, month));
    }

    // Generate a budget for a specific month and year
    public Budget getBudgetForMonth(int year, Month month) {
      //  Optional<Budget> existingBudget = budgetRepository.findByYearAndMonth(year, month);
    //if (existingBudget.isPresent()) {
    //    return existingBudget.get();
    //}    
        
        
        Budget budget = new Budget(month.name() + " " + year);
        //try {
        budget.setTotalIncome(calculateTotalIncome(year, month));
        budget.setTotalExpenses(calculateTotalExpenses(year, month));
        budget.setBalance(calculateBalance(year, month)); // Updates balance based on total income and expenses
        return budget;
        // } catch (Exception e) {
        //    throw new BudgetException("Error calculating budget for " + month + " " + year, e);
        }
        //return budgetRepository.save(budget);
        
    //}
   
        
}
    



