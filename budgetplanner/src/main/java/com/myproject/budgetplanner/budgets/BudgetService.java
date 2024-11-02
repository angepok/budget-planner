package com.myproject.budgetplanner.budgets;

 
import com.myproject.budgetplanner.NoDataAvailableException;
import com.myproject.budgetplanner.expense.Expense;
import com.myproject.budgetplanner.expense.ExpenseService;
import com.myproject.budgetplanner.income.Income;
import com.myproject.budgetplanner.income.IncomeService;
import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {
     


    private final IncomeService incomeService;
    private final ExpenseService expenseService;

    @Autowired
    public BudgetService(IncomeService incomeService, ExpenseService expenseService){
        this.incomeService = incomeService;
        this.expenseService = expenseService;
    }

    // Calculation methods
    public BigDecimal calculateTotalIncome(){
        return incomeService.getTotalIncome();
    }

    public BigDecimal calculateTotalExpenses(){
        return expenseService.getTotalExpense();
    }

    public BigDecimal calculateBalance(){
        return calculateTotalIncome().subtract(calculateTotalExpenses());
    }


    // Budget summary method with name parameter
    public Budget getBudgetSummary(String name) {
        if (name == null || name.trim().isEmpty()) {
        throw new IllegalArgumentException("Budget name must not be empty.");
    }
        BigDecimal totalIncome = calculateTotalIncome();
        BigDecimal totalExpenses = calculateTotalExpenses();
        BigDecimal balance = totalIncome.subtract(totalExpenses);

    return new Budget(name, totalIncome, totalExpenses, balance);
        
    }

    /* 
    // Monthly budget with error handling
    public MonthlyBudget calculateMonthlyBudget(int year, Month month) {
        List<Income> monthlyIncome = incomeService.getIncomeByMonth(year, month);
        //List<Expense> monthlyExpenses = expenseService.getExpensesByMonth(year, month);

        /* 
        if (monthlyIncome.isEmpty() && monthlyExpenses.isEmpty()) {
            // Option 1: Return a default budget with zero values
            //return new MonthlyBudget(month, year, BigDecimal.ZERO, BigDecimal.ZERO);
            throw new NoDataAvailableException("No data available for the specified month: " + month + " " + year);
        }
            BigDecimal totalIncome = monthlyIncome.stream()
                .map(Income::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal totalExpenses = monthlyExpenses.stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new MonthlyBudget(month, year, totalIncome, totalExpenses);
    }
        

    // Yearly budget with error handling
    public YearlyBudget calculateYearlyBudget(int year) {
        BigDecimal yearlyIncome = incomeService.getTotalIncomeByYear(year);
        List<Expense> yearlyExpenses = expenseService.findExpensesByYear(year);


        //Check for null instead of using isEmpty()
        boolean incomeAvailable = (yearlyIncome != null && yearlyIncome > 0);
        boolean expensesAvailable = (yearlyExpenses != null && !yearlyExpenses.isEmpty());
       
        if (!incomeAvailable && !expensesAvailable) {
            throw new NoDataAvailableException("No income or expense data available for the year " + year);
        }
        
        BigDecimal totalIncome = (yearlyIncome != null) ? BigDecimal.valueOf(yearlyIncome) : BigDecimal.ZERO;

        BigDecimal totalExpenses = yearlyExpenses.stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new YearlyBudget(year, totalIncome, totalExpenses); 
        
    }
    */    
}
    



