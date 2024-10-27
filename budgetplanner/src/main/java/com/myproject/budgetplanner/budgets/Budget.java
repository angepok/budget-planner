package com.myproject.budgetplanner.budgets;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "budgets")

public class Budget {

    private String name;
    private BigDecimal amount;
    private UUID id;

    private Budget(){

    }

    private Budget(String description, BigDecimal amount, UUID id){
        this.name = name;
        this.amount = amount;
        this.id = id;

    }


}
