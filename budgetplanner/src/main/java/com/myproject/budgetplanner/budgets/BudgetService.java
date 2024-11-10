package com.myproject.budgetplanner.budgets;

import com.myproject.budgetplanner.expense.ExpenseService;
import com.myproject.budgetplanner.income.IncomeService;

import java.math.BigDecimal;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {

    @Autowired
    private final BudgetRepository budgetRepository;
    private final IncomeService incomeService;
    private final ExpenseService expenseService;

    public BudgetService(BudgetRepository budgetRepository, IncomeService incomeService,
            ExpenseService expenseService) {
        this.budgetRepository = budgetRepository;
        this.incomeService = incomeService;
        this.expenseService = expenseService;
    }

    /**
     * An array to store monthly expenses for each month of the year.
     * The array has 12 elements, each representing a month (0 for January, 11 for
     * December).
     */
    private BigDecimal[] monthlyExpenses = new BigDecimal[12];

    /**
     * Constructor for the BudgetService class.
     * Initializes each element in the monthlyExpenses array to BigDecimal.ZERO
     * to avoid null values, ensuring each month starts with a zero expense balance.
     */
    public void BudgetService1() {
        // Initialize monthlyExpenses with BigDecimal.ZERO to avoid null values
        for (int i = 0; i < 12; i++) {
            monthlyExpenses[i] = BigDecimal.ZERO;
        }
    }

    // Method to get expenses by month using the zero-based index
    public BigDecimal getExpensesByMonth(Month month) {
        int index = month.getValue() - 1; // Convert Month enum to zero-based index
        return monthlyExpenses[index];
    }

    // Calculation methods
    public BigDecimal calculateTotalIncome(int year, Month month) {
        BigDecimal totalIncome = incomeService.getIncomeByMonth(year, month);
        return totalIncome != null ? totalIncome : BigDecimal.ZERO;
    }

    public BigDecimal calculateTotalExpenses(int year, Month month) {
        BigDecimal totalExpense = expenseService.getExpensesByMonth(year, month);
        return totalExpense != null ? totalExpense : BigDecimal.ZERO;
    }

    public BigDecimal calculateBalance(int year, Month month) {
        return calculateTotalIncome(year, month).subtract(calculateTotalExpenses(year, month));
    }

    // Generate a budget for a specific month and year
    public Budget getBudgetForMonth(int year, Month month) {

        Budget budget = new Budget(month.name() + " " + year);

        budget.setTotalIncome(calculateTotalIncome(year, month));
        budget.setTotalExpenses(calculateTotalExpenses(year, month));
        budget.setBalance(calculateBalance(year, month)); // Updates balance based on total income and expenses
        return budgetRepository.save(budget);

    }

}
