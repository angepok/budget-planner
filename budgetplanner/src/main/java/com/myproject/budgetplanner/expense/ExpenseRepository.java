package com.myproject.budgetplanner.expense;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myproject.budgetplanner.expenseType.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    /**
     * Check if an ExpenseType for a given expense(case-insensitive) exists.
     *
     * A query to the database to determine whether an ExpenseType record
     * with the specified expense category (ignoring case) exists or not.
     *
     * @param expenseType The expense category to check for existence.
     * @return {@code true} if an ExpenseType with the specified expense category
     *         (case-insensitive) exists, otherwise {@code false}.
     */
    //@Query("SELECT COUNT(et) > 0 FROM ExpenseType et WHERE LOWER(et.expenseCategory) = LOWER(:expenseCategory)")
    //what is my database name?? expense, table expense_table
    @Query("SELECT * FROM expense WHERE LOWER(expense_type) = LOWER(:expenseType)")
    boolean existsByExpenseTypeIgnoreCase(String expenseType);

    //List<Expense> findTotalExpensesByMonth(int year, Month month);

    Page<Expense> findByDateBetweenAndExpenseTypeOrderByCreationDateDesc(LocalDate startDate, LocalDate endDate, 
    String expenseType, Pageable pageable);
    
    Page<Expense> findByExpenseTypeOrderByCreationDateDesc(String expenseType, Pageable pageable);

    Page<Expense>  findByDateBetweenOrderByCreationDateDesc(LocalDate startDate, LocalDate endDate, Pageable page);

    @Query("SELECT e FROM Expense e WHERE YEAR(e.date) = :year AND MONTH(e.date) = :month")
    List<Expense> findByYearAndMonth(@Param("year") int year, @Param("month") int month);

    @Query("SELECT e FROM Expense e WHERE YEAR(e.date) = :year")
    List<Expense> findByYear(@Param("year") int year);
}







