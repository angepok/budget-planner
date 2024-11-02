package com.myproject.budgetplanner.expenseType;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ExpenseTypeRepository extends JpaRepository<ExpenseType, Long>{
    boolean existsByExpenseTypeIgnoreCase(String expenseType);
    Optional<ExpenseType> findByNameIgnoreCase(String name);

}
