package com.myproject.budgetplanner.budgets;


import java.time.Month;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myproject.budgetplanner.expense.Expense;
import com.myproject.budgetplanner.income.Income;




@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long>{
    
    // is function necessary
    //@Query("SELECT i FROM Income i WHERE FUNCTION YEAR(i.date) = :year AND EXTRACT(MONTH FROM i.date) = :month")
    //List<Income> findByYearAndMonth(@Param("year") int year, @Param("month") Month month);

    //@Query("SELECT i FROM Income i WHERE FUNCTION YEAR(i.date) = :year")
    //List<Income> findByYear(@Param("year") int year);

    //@Query("SELECT e FROM Expense e WHERE FUNCTION YEAR(e.date) = :year AND EXTRACT(MONTH FROM e.date) = :month")
    //List<Expense> findExpenseByYearAndMonth(@Param("year") int year, @Param("month") Month month);

    //@Query("SELECT e FROM Expense e WHERE FUNCTION YEAR(e.date) = :year")
    //List<Expense> findExpenseByYear(@Param("year") int year);
    
}


