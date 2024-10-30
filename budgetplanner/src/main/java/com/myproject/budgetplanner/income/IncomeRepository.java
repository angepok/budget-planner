package com.myproject.budgetplanner.income;

import org.springframework.stereotype.Repository;

import java.time.Month;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository

public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findTotalIncomeByMonth(int year, Month month);

    List<Income> findTotalIncomeByYear(int year);


}
