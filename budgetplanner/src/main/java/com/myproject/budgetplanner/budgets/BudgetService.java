package com.myproject.budgetplanner.budgets;

import com.myproject.budgetplanner.expense.Expense;
import com.myproject.budgetplanner.expense.ExpenseService;
import com.myproject.budgetplanner.income.Income;
import com.myproject.budgetplanner.income.IncomeService;
import com.myproject.budgetplanner.budgets.MonthlyBudget;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

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

    public MonthlyBudget calculateMonthlyBudget(int year, Month month) {
        List<Income> monthlyIncome = incomeService.getIncomeByMonth(year, month);
        List<Expense> monthlyExpenses = expenseService.getExpensesByMonth(year, month);

        BigDecimal totalIncome = monthlyIncome.stream()
                .map(Income::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpenses = monthlyExpenses.stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new MonthlyBudget(month, year, totalIncome, totalExpenses);
    }

    public YearlyBudget calculateYearlyBudget(int year) {
        List<Income> yearlyIncome = incomeService.getIncomeByYear(year);
        List<Expense> yearlyExpenses = expenseService.getExpensesByYear(year);

        BigDecimal totalIncome = yearlyIncome.stream()
                .map(Income::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpenses = yearlyExpenses.stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new YearlyBudget(year, totalIncome, totalExpenses);
    }
}


