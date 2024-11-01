package com.myproject.budgetplanner.income;

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



import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "income")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Please specify the name of income")
    private String name;

    private int year;
    private Month month;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=15, fraction=2)
    @NotNull(message = "Please specify an amount")
    private BigDecimal amount;
    
    public void setAmount(BigDecimal amount) {
     this.amount = amount;
    }

    @Column(name = "income_date")
    private LocalDate date;
    
    @NotNull(message = "Date cannot be empty")
    private LocalDate dateReceived;

    @CreationTimestamp
    private Timestamp creationDate;



}

