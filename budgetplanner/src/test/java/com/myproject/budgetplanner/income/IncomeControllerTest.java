package com.myproject.budgetplanner.income;

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

class IncomeControllerTest {

    private IncomeController incomeController;

    @Mock
    private IncomeService incomeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        incomeController = new IncomeController(incomeService);
    }

    @Test
    void getAllIncome() {
        // given, when
        incomeController.getAllIncome();

        // then
        verify(incomeService).getAllIncome();
    }

    @Test
    void getIncomeById() {
        // given
        final Long id = 0L;

        // when
        when(incomeService.getIncome(id)).thenReturn(new Income(1L, "", 2020, Month.JULY, BigDecimal.valueOf(10.00), LocalDate.now(), LocalDate.now(), Timestamp.valueOf(LocalDateTime.now())));
        final ResponseEntity<Income> response = incomeController.getIncomeById(id);

        // then
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        verify(incomeService).getIncome(id);
    }

    @Test
    void createIncome() throws IncomeException {
        // given
        final Income income = new Income(1L, "", 2020, Month.JULY, BigDecimal.valueOf(10.00), LocalDate.now(), LocalDate.now(), Timestamp.valueOf(LocalDateTime.now()));

        // when
        when(incomeService.createIncome(income)).thenReturn(income);
        final ResponseEntity<Income> response = incomeController.createIncome(income);

        // then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(income, response.getBody());
    }

    @Test
    void updateIncome() {
        // given
        final Long id = 0L;
        final Income updatedIncome = new Income(0L, "", 2020, Month.JULY, BigDecimal.valueOf(10.00), LocalDate.now(), LocalDate.now(), Timestamp.valueOf(LocalDateTime.now()));

        // when
        when(incomeService.updateIncome(id, updatedIncome)).thenReturn(updatedIncome);
        final ResponseEntity<Income> response = incomeController.updateIncome(id, updatedIncome);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedIncome, response.getBody());
    }

    @Test
    void deleteIncome() {
        // given
        final Long id = 0L;

        // when
        when(incomeService.getIncome(id)).thenReturn(new Income());
        incomeController.deleteIncome(id);

        // then
        verify(incomeService, times(1)).getIncome(id);
        verify(incomeService, times(1)).deleteIncome(id);
    }
}