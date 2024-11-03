package com.myproject.budgetplanner.income;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Month;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    @Query("SELECT SUM(i.amount) FROM Income i")
    BigDecimal findTotalIncome();

    @Query("SELECT SUM(i.amount) FROM Income i WHERE i.year = :year AND i.month = :month")
    BigDecimal findTotalIncomeByMonth(int year, Month month);

    @Query("SELECT SUM(i.amount) FROM Income i WHERE YEAR(i.date) = :year")
    BigDecimal findTotalIncomeByYear(@Param("year") int year);

}
