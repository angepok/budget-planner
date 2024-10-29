package com.myproject.budgetplanner.budgets;


import java.math.BigDecimal;


public class Budget {

    private String name;
    private BigDecimal balance;
    private BigDecimal totalIncome;
    private BigDecimal totalExpenses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(BigDecimal totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    private Budget(){

    }

    private Budget(String name, BigDecimal balance, BigDecimal totalIncome, BigDecimal totalExpenses){
        this.name = name;
        this.balance = balance;
        this.totalExpenses = totalExpenses;
        this.totalIncome = totalIncome;

    }

    public Budget(BigDecimal totalIncome2, BigDecimal totalExpenses2, BigDecimal balance2) {
        //TODO Auto-generated constructor stub
    }

}
