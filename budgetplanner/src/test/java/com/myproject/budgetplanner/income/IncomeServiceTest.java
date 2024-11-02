package com.myproject.budgetplanner.income;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class IncomeServiceTest {

    IncomeService incomeService;

    @Mock
    public IncomeRepository incomeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        incomeService = new IncomeService(incomeRepository);
    }

    @Test
    void shouldCreateIncome() throws IncomeException {
        // given
        final Income income = new Income(1L, "", 2020, Month.JULY, BigDecimal.valueOf(10.00), LocalDate.now(), LocalDate.now(), Timestamp.valueOf(LocalDateTime.now()));

        // when
        incomeService.createIncome(income);

        // then
        Mockito.verify(incomeRepository).save(income);
    }

    @Test
    void shouldUpdateIncome() {
        // given
        final Long id = 1L;
        final Income updatedIncome = new Income(1L, "X", 2020, Month.JULY, BigDecimal.valueOf(10.00), LocalDate.now(), LocalDate.now(), Timestamp.valueOf(LocalDateTime.now()));

        final Income income = new Income();
        when(incomeRepository.findById(id)).thenReturn(Optional.of(income));

        // when
        incomeService.updateIncome(id, updatedIncome);

        // then
        final ArgumentCaptor<Income> incomeCaptor = ArgumentCaptor.forClass(Income.class);
        verify(incomeRepository, times(1)).findById(id);
        verify(incomeRepository).save(incomeCaptor.capture());

        final Income capturedIncome = incomeCaptor.getValue();
        assertEquals(updatedIncome.getName(), capturedIncome.getName());
        assertEquals(updatedIncome.getAmount(), capturedIncome.getAmount());
        assertEquals(updatedIncome.getCreationDate(), capturedIncome.getCreationDate());
    }

    @Test
    void shouldDeleteIncome() {
        // given
        final Long id = 0L;

        when(incomeRepository.existsById(id)).thenReturn(true);

        // when
        incomeService.deleteIncome(id);

        // then
        verify(incomeRepository, times(1)).deleteById(id);
    }
}
