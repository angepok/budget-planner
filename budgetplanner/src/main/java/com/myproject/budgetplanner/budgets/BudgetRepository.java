package com.myproject.budgetplanner.budgets;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//import java.util.List;

//import com.myproject.budgetplanner.income.Income;


@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long>{
    // is function necessary
    //@Query("SELECT i FROM Income i WHERE FUNCTION YEAR(i.date) = :year AND MONTH(i.date) = :month")
    //List<Income> findByYearAndMonth(@Param("year") int year, @Param("month") int month);

   // @Query("SELECT i FROM Income i WHERE FUNCTION YEAR(i.date) = :year")
    //List<Income> findByYear(@Param("year") int year);
}
