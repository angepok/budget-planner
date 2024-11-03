package com.myproject.budgetplanner.budgets;

import com.myproject.budgetplanner.expense.Expense;
import com.myproject.budgetplanner.income.Income;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "budget")
@AllArgsConstructor
@NoArgsConstructor
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    // Initialized with default value of 0.0 to avoid NullPointerException
    private BigDecimal balance = BigDecimal.ZERO;
    private BigDecimal totalIncome = BigDecimal.ZERO;
    private BigDecimal totalExpenses = BigDecimal.ZERO;

    
    

    // all argument constructor
    public Budget(String name) {
        this.name = name;
        //this.totalExpenses = totalExpenses != null ? totalExpenses : BigDecimal.ZERO; // Handle null input
        //this.totalIncome = totalIncome != null ? totalIncome : BigDecimal.ZERO; // Handle null input
        //this.balance = totalIncome.subtract(totalExpenses);
    }

    // set income
    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome != null ? totalIncome : BigDecimal.ZERO; // Avoids null

        // Update balance whenever income or expenses change
        updateBalance();
    }

    // set total expenses
    public void setTotalExpenses(BigDecimal totalExpenses) {
        this.totalExpenses = totalExpenses != null ? totalExpenses : BigDecimal.ZERO; // Avoids null
        // Update balance whenever income or expenses change
        updateBalance();
    }

    // Update balance based on income and expenses
    private void updateBalance() {
        this.balance = totalIncome.subtract(totalExpenses);
    }
}
