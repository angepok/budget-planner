package com.myproject.budgetplanner.expense;

import java.math.BigDecimal;
import java.time.Month;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT SUM(e.amount) FROM Expense e")
    BigDecimal findTotalExpense();

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.year = :year AND e.month = :month")
    BigDecimal findTotalExpenseByMonth(int year, Month month);

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE YEAR(e.date) = :year")
    BigDecimal findTotalExpenseByYear(@Param("year") int year);

}
