package com.myproject.budgetplanner.budget;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myproject.budgetplanner.budgets.Budget;
import com.myproject.budgetplanner.budgets.BudgetRepository;
import com.myproject.budgetplanner.budgets.BudgetService;
import com.myproject.budgetplanner.expense.ExpenseService;
import com.myproject.budgetplanner.income.IncomeService;

@ExtendWith(MockitoExtension.class)
public class BudgetServiceTest {

    @Mock
    private BudgetRepository budgetRepository;

    @Mock
    private IncomeService incomeService;

    @Mock
    private ExpenseService expenseService;

    @InjectMocks
    private BudgetService budgetService;

    @Test
    void shouldCalculateTotalIncome() {
        // given
        int year = 2024;
        Month month = Month.JANUARY;
        BigDecimal income = BigDecimal.valueOf(5000);
        when(incomeService.getIncomeByMonth(year, month)).thenReturn(income);

        // when
        BigDecimal result = budgetService.calculateTotalIncome(year, month);

        // then
        assertEquals(income, result);
    }

    @Test
    void shouldCalculateTotalExpenses() {
        // given
        int year = 2024;
        Month month = Month.JANUARY;
        BigDecimal expenses = BigDecimal.valueOf(3000);
        when(expenseService.getExpensesByMonth(year, month)).thenReturn(expenses);

        // when
        BigDecimal result = budgetService.calculateTotalExpenses(year, month);

        // then
        assertEquals(expenses, result);
    }

    @Test
    void shouldCalculateBalance() {
        // given
        int year = 2024;
        Month month = Month.JANUARY;
        BigDecimal income = BigDecimal.valueOf(5000);
        BigDecimal expenses = BigDecimal.valueOf(2000);
        when(incomeService.getIncomeByMonth(year, month)).thenReturn(income);
        when(expenseService.getExpensesByMonth(year, month)).thenReturn(expenses);

        // when
        BigDecimal result = budgetService.calculateBalance(year, month);

        // then
        assertEquals(BigDecimal.valueOf(3000), result);
    }

    @Test
    void shouldGenerateAndSaveBudgetForMonth() {
        // given
        int year = 2024;
        Month month = Month.JANUARY;
        BigDecimal income = BigDecimal.valueOf(5000);
        BigDecimal expenses = BigDecimal.valueOf(2000);
        when(incomeService.getIncomeByMonth(year, month)).thenReturn(income);
        when(expenseService.getExpensesByMonth(year, month)).thenReturn(expenses);
        //when(budgetRepository.findByYearAndMonth(year, month)).thenReturn(Optional.empty());

        Budget savedBudget = new Budget("JANUARY 2024");
        savedBudget.setTotalIncome(income);
        savedBudget.setTotalExpenses(expenses);
        savedBudget.setBalance(income.subtract(expenses));
        when(budgetRepository.save(any(Budget.class))).thenReturn(savedBudget);

        // when
        Budget result = budgetService.getBudgetForMonth(year, month);

        // then
        assertEquals("JANUARY 2024", result.getName());
        assertEquals(income, result.getTotalIncome());
        assertEquals(expenses, result.getTotalExpenses());
        assertEquals(BigDecimal.valueOf(3000), result.getBalance());
        verify(budgetRepository, times(1)).save(any(Budget.class));
    }
}
