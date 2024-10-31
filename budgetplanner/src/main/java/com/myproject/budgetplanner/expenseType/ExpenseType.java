package com.myproject.budgetplanner.expenseType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;


@Entity
@Data   
@Table(name = "expensetype")
@AllArgsConstructor
@NoArgsConstructor



public class ExpenseType {

    @Id
    @GeneratedValue
    private long id;

    @NotEmpty(message = "Please specify the type of expense")
    @Column(unique = true)
    private String expenseType;

    
    //do i need getters and setters

}
