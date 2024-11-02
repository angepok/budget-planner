package com.myproject.budgetplanner.expense;


import java.math.BigDecimal;
import java.time.Month;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {


    
     /* Check if an ExpenseType for a given expense(case-insensitive) exists.
     param expenseType The expense category to check for existence.
     return {@code true} if an ExpenseType with the specified expense category
     *         (case-insensitive) exists, otherwise {@code false}.
     */
    

    @Query("SELECT SUM(e.amount) FROM Expense e")
    BigDecimal findTotalExpense();

    // @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.year = :year AND e.month = :month")
    // BigDecimal findTotalExpenseByMonth(int year, Month month);

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE YEAR(e.date) = :year")
    BigDecimal findTotalExpenseByYear(@Param("year") int year);

    //@Query("SELECT e FROM Expense e WHERE YEAR(e.date) = :year AND MONTH(e.date) = :month")
    //List<Expense> findByYearAndMonth(@Param("year") int year, @Param("month") int month);

    //@Query("SELECT e FROM Expense e WHERE YEAR(e.date) = :year")
    //List<Expense> findByYear(@Param("year") int year);


    //Page<Expense> findByDateBetweenAndExpenseTypeOrderByCreationDateDesc(LocalDate startDate, LocalDate endDate, 
    //String expenseType, Pageable pageable);
    
    //Page<Expense> findByExpenseTypeOrderByCreationDateDesc(String expenseType, Pageable pageable);

    //Page<Expense>  findByDateBetweenOrderByCreationDateDesc(LocalDate startDate, LocalDate endDate, Pageable page);

    
}








