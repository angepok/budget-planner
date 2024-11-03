package com.myproject.budgetplanner.budget;
import java.math.BigDecimal;
import java.time.Month;

import com.myproject.budgetplanner.budgets.Budget;
import com.myproject.budgetplanner.budgets.BudgetController;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.myproject.budgetplanner.budgets.BudgetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class BudgetControllerTest {
    
        private MockMvc mockMvc;
    
        @Mock
        private BudgetService budgetService;
    
        @InjectMocks
        private BudgetController budgetController;
    
        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this);
            mockMvc = MockMvcBuilders.standaloneSetup(budgetController).build();
        }
    
        @Test
        public void getBudgetForMonth_ValidMonth_ReturnsBudget() throws Exception {
            // Arrange
            int year = 2024;
            String month = "NOVEMBER";
            Budget budget = new Budget("NOVEMBER 2024");
            budget.setTotalIncome(BigDecimal.valueOf(1000));
            budget.setTotalExpenses(BigDecimal.valueOf(500));
            budget.setBalance(BigDecimal.valueOf(500));
    
            when(budgetService.getBudgetForMonth(year, Month.NOVEMBER)).thenReturn(budget);
    
            // Act & Assert
            mockMvc.perform(get("/api/budget/{year}/{month}", year, month)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.totalIncome").value(1000))
                    .andExpect(jsonPath("$.totalExpenses").value(500))
                    .andExpect(jsonPath("$.balance").value(500));
        }
    
        @Test
        public void getBudgetForMonth_InvalidMonth_ReturnsBadRequest() throws Exception {
            // Arrange
            int year = 2024;
            String month = "INVALID_MONTH";
    
            // Act & Assert
            mockMvc.perform(get("/api/budget/{year}/{month}", year, month)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }
    }

