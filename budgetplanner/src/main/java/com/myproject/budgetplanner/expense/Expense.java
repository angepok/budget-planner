package com.myproject.budgetplanner.expense;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

/*
 * Represents an expense in the budget planner.
 * This class contains details about a specific expense, including its name, amount, date, and associated year and month.
 */

@Entity
@Table(name = "expense")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {

    /**
     * The unique identifier for the expense.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /*
     * The name of the expense.
     * This field must not be null or empty.
     */
    @NotNull(message = "Please specify the name of expense")
    @NotEmpty(message = "Expense name cannot be empty")
    private String name;

    /**
     * The year and month when the expense was incurred.
     */
    private int year;
    private Month month;

    /*
     * the amount field must contain a non-null BigDecimal * value greater than 0.0,
     * formatted with up to 15 integer digits and exactly 2 decimal places if
     * needed.* NotNull Specifies that amount cannot be null.
     */
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 15, fraction = 2)
    @NotNull(message = "Please specify an amount")
    private BigDecimal amount;

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /*
     * The date when the expense occurred.
     * This field cannot be null.
     */
    @NotNull(message = "Date cannot be empty!")
    private LocalDate date;

    /*
     * The timestamp for when the expense record was created.
     * This field is automatically generated.
     */
    @CreationTimestamp
    private Timestamp creationDate;

}
