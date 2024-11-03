package com.myproject.budgetplanner.expense;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;


public class ExpenseServiceTest {

    ExpenseService expenseService;

    @Mock
    public ExpenseRepository expenseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        expenseService = new ExpenseService(expenseRepository);
    }

    @Test
    void shouldCreateExpense() throws ExpenseException {
        // given
        final Expense expense = new Expense(1L, "", 2020, "JULY", BigDecimal.valueOf(10.00), LocalDate.now(), Timestamp.valueOf(LocalDateTime.now()));

        // when
        expenseService.createExpense(expense);

        // then
        Mockito.verify(expenseRepository).save(expense);
    }

    /* 
    @Test
    void shouldUpdateExpense() {
        // given
        final Long id = 1L;
        final Expense updatedExpense = new Expense(1L, "X", 2020, "JULY", BigDecimal.valueOf(10.00), LocalDate.now(), Timestamp.valueOf(LocalDateTime.now()));

        final Expense expense = new Expense();
        when(expenseRepository.findById(id)).thenReturn(Optional.of(expense));

        // when
        expenseService.updateExpense(id, updatedExpense);

        // then
        final ArgumentCaptor<Expense> expenseCaptor = ArgumentCaptor.forClass(Expense.class);
        verify(expenseRepository, times(1)).findById(id);
        verify(expenseRepository).save(expenseCaptor.capture());

        final Expense capturedIncome = expenseCaptor.getValue();

        assertEquals(updatedExpense.getName(), capturedExpense.getName());
        assertEquals(updatedExpense.getAmount(), capturedExpense.getAmount());
        assertEquals(updatedExpense.getCreationDate(), capturedExpense.getCreationDate());
    }
     
    /* 
    @Test
    void shouldDeleteExpense() {
        // given
        final Long id = 0L;

        when(expenseRepository.existsById(id)).thenReturn(true);

        // when
        expenseService.deleteExpense(id);

        // then
        verify(expenseRepository, times(1)).deleteById(id);
    }
*/
}
