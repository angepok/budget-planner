package com.myproject.budgetplanner.budgets;


import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "budget")

public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private BigDecimal balance;
    private BigDecimal totalIncome;
    private BigDecimal totalExpenses;

    //Default constructor
    public Budget(){

    }

    // all argument constructor
    public Budget(String name, BigDecimal totalIncome, BigDecimal totalExpenses,  BigDecimal balance){
        this.name = name;
        this.totalExpenses = totalExpenses;
        this.totalIncome = totalIncome;
        this.balance = totalIncome.subtract(totalExpenses);

    }

    // Getters and setters for ID
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // getname and setname
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // getbalance and set balance
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    // get and set income
    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;

        // Update balance whenever income or expenses change
        updateBalance(); 
    }

    //get and set total expenses
    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(BigDecimal totalExpenses) {
        this.totalExpenses = totalExpenses;
        // Update balance whenever income or expenses change
        updateBalance(); 
    }

    //Update balance based on income and expenses
    private void updateBalance() {
        this.balance = totalIncome.subtract(totalExpenses);
    }

    
}
