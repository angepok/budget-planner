package com.myproject.budgetplanner.expenseType;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;

import com.myproject.budgetplanner.expense.Expense;

import jakarta.persistence.CascadeType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Please specify the type of expense")
    @Column(unique = true)
    private String expenseType;

    @OneToMany(mappedBy = "expenseType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Expense> expenses;

}
