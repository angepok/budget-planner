package com.myproject.budgetplanner.expense;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Expense {

    @Id
    @GeneratedValue
    //MAY USE A SEQUENCE GENRATOR, if so no UUID, or change to UUID
    @SequenceGenerator(name = "expense_seq_generator", sequenceName = "EXPENSE_SEQ", allocationSize = 1)
    private long id;
    //need to insert wrong data entered annotation
    //is notnulll annoation neede too

    @NotEmpty(message = "Please specify the name of expense")
    private String name;

    @NotEmpty(message = "Please specify the type of expense")
    private String expensetype;

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
