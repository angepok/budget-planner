package com.myproject.budgetplanner.expense;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.myproject.budgetplanner.expenseType.ExpenseType;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;


@Entity
@Table(name ="expense")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //need to insert wrong data entered annotation
    

    @NotNull(message = "Please specify the name of expense")
    @NotEmpty(message = "Expense name cannot be empty")
    private String name;

    
    @NotNull(message = "Expense type cannot be null")
    @ManyToOne
    @JoinColumn(name = "expense_type_id", nullable = false)
    private ExpenseType expenseType;

    
    /*
     * With these validations, the amount field must contain a non-null BigDecimal value greater than 0.0, 
     * formatted with up to 15 integer digits and exactly 2 decimal places if needed.
     NotNull Specifies that amount cannot be null.
     */

     
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=15, fraction=2)
    @NotNull(message = "Please specify an amount")
    private BigDecimal amount;
    

    @NotNull(message = "Date cannot be empty!")
    private LocalDate date;

    @CreationTimestamp
    private Timestamp creationDate;

    public String getExpenseTypeName() {
        return expenseType != null ? expenseType.getExpenseType() : null; // Assuming ExpenseType has a getExpenseType() method
    }


}

