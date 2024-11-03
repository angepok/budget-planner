package com.myproject.budgetplanner.expense;

import java.math.BigDecimal;
import java.time.Month;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {


    /**
     * Retrieves the total amount of all expenses.
     *
     * @return the total expense as a BigDecimal
     */
    @Query("SELECT SUM(e.amount) FROM Expense e")
    BigDecimal findTotalExpense();


    /**
     * Retrieves the total amount of expenses for a specific year and month.
     *
     * @param year the year for which to calculate total expenses
     * @param month the month for which to calculate total expenses
     * @return the total expense for the specified month and year as a BigDecimal
     */
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.year = :year AND e.month = :month")
    BigDecimal findTotalExpenseByMonth(int year, Month month);

    
    /**
     * Retrieves the total amount of expenses for a specific year.
     *
     * @param year the year for which to calculate total expenses
     * @return the total expense for the specified year as a BigDecimal
     */
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE YEAR(e.date) = :year")
    BigDecimal findTotalExpenseByYear(@Param("year") int year);

}
