package com.myproject.budgetplanner.expense;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExpenseControllerTest {

    private ExpenseController expenseController;
    @Mock
    private ExpenseService expenseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        expenseController = new ExpenseController(expenseService);
    }

    @Test
    void getAllExpenses() {
        // given, when
        expenseController.getAllExpenses();

        // then
        verify(expenseService).getAllExpensesList();
    }

    @Test
    void getExpenseById() {
        // given
        final Long id = 0L;

        // when
        when(expenseService.getExpenseById(id)).thenReturn(new Expense(1L, "", 2020, Month.JULY,
                BigDecimal.valueOf(10.00), LocalDate.now(), Timestamp.valueOf(LocalDateTime.now())));
        final ResponseEntity<Expense> response = expenseController.getExpenseById(id);

        // then
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        verify(expenseService).getExpenseById(id);
    }

    @Test
    void createExpense() throws ExpenseException {
        // given
        final Expense expense = new Expense(1L, "", 2020, Month.JULY, BigDecimal.valueOf(10.00), LocalDate.now(),
                Timestamp.valueOf(LocalDateTime.now()));

        // when
        when(expenseService.createExpense(expense)).thenReturn(expense);
        final ResponseEntity<Expense> response = expenseController.createExpense(expense);

        // then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expense, response.getBody());
    }

    @Test
    void updateExpense() {
        // given
        final Long id = 0L;
        final Expense updatedExpense = new Expense(0L, "", 2020, Month.JULY, BigDecimal.valueOf(10.00), LocalDate.now(),
                Timestamp.valueOf(LocalDateTime.now()));

        // when
        when(expenseService.updateExpense(id, updatedExpense)).thenReturn(updatedExpense);
        final ResponseEntity<Expense> response = expenseController.updateExpense(id, updatedExpense);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedExpense, response.getBody());
    }

    @Test
    void deleteExpense() {
        // given
        final Long id = 0L;

        // when
        when(expenseService.getExpenseById(id)).thenReturn(new Expense());
        expenseController.deleteExpense(id);

        // then
        verify(expenseService, times(1)).getExpenseById(id);
        verify(expenseService, times(1)).deleteExpense(id);
    }
}
