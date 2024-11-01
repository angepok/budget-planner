package com.myproject.budgetplanner.expense;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expense_seq_generator")
    //MAY USE A SEQUENCE GENRATOR, if so no UUID, or change to UUID
    @SequenceGenerator(name = "expense_seq_generator", sequenceName = "EXPENSE_SEQ", allocationSize = 1)
    private long id;
    //need to insert wrong data entered annotation
    //is notnulll annotation needed too

    @NotEmpty(message = "Please specify the name of expense")
    private String name;

    @NotEmpty(message = "Please specify the type of expense")
    private String expenseType;

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }
    
    /*
     * With these validations, the amount field must contain a non-null BigDecimal value greater than 0.0, 
     * formatted with up to 15 integer digits and exactly 2 decimal places if needed.
     NotNull Specifies that amount cannot be null.
     */

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=15, fraction=2)
    @NotNull(message = "Please specify an amount")
    private BigDecimal amount;
    //private double amount;

    @NotNull(message = "Date cannot be empty!")
    private LocalDate date;

    @CreationTimestamp
    private Timestamp creationDate;


}
