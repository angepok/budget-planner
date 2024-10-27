package com.myproject.expense;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;


@Entity
@Data   
@AllArgsConstructor
@NoArgsConstructor



public class ExpenseType {

    @Id
    @GeneratedValue
    private long id;

    @NotEmpty(message = "Please specify the type of expense")
    @Column(unique = true)
    private String expenseCategory;

    


}
