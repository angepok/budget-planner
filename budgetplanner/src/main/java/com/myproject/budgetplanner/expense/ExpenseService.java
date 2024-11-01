package com.myproject.budgetplanner.expense;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;

import com.myproject.budgetplanner.expenseType.ExpenseTypeService;
import com.myproject.budgetplanner.expenseType.ExpenseTypeRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class ExpenseService {

    public final ExpenseRepository expenseRepository;
    public final ExpenseTypeService expenseTypeService;
    
    //instance of expense repo
    //@Autowired
    public ExpenseService(ExpenseRepository expenseRepository, ExpenseTypeService expenseTypeService, ExpenseTypeRepository expenseTypeRepository){
        this.expenseRepository = expenseRepository;
        this.expenseTypeService = expenseTypeService;
    }

    
    //getAllExpenses
    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll(Sort.by("creationDate").descending());
    }

    //getExpenseById 
    //Otional works better than throwing  an exception
    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    /*
    public Expense getExpenseById(Long id){
        return expenseRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Expense not found with id: " + id));
    }
    */

    //createExpense
    public Expense createExpense(@NotNull Expense expense) {
        return expenseRepository.save(expense);
    }
    
        /**
    * Updates an existing Expense record.
    *
    * This method fetches the existing expense by ID, updates the specified fields, 
     * and saves the changes to the database.
    *
    * @param id the ID of the expense to update
    * @param updatedExpense the expense data to update with
    * @return the updated Expense object
    * @throws EntityNotFoundException if the expense with the specified ID is not found
        */

    //updateExpense
    public Expense updateExpense(Long id, Expense updatedExpense) {
        // Check if the expense exists
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense not found with id: " + id));
        
        // Update fields
        existingExpense.setName(updatedExpense.getName());
        existingExpense.setExpenseType(updatedExpense.getExpenseType());
        existingExpense.setAmount(updatedExpense.getAmount());
        existingExpense.setDate(updatedExpense.getDate());
        
        // Update expense type if needed
       if (updatedExpense.getExpenseType() != null) {
        existingExpense.setExpenseType(updatedExpense.getExpenseType());
        }

        // Save the updated expense
        return expenseRepository.save(existingExpense);
    }

    
    //deleteExpense
    public void deleteExpense(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense not found with id: " + id));
        expenseRepository.delete(expense);
    }
        
    //necessaary for budget class
    
    //Get totalExpenses
    public BigDecimal getTotalAmount(List<Expense> expenses) {
    return expenses.stream()
            .map(Expense::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
}

    //Get expenses by Month
    public List<Expense> getExpensesByMonth(int year, Month month){
        return expenseRepository.findByYearAndMonth(year, month.getValue());
    }

    public List<Expense> getExpensesByYear(int year) {
        return expenseRepository.findByYear(year);
    }

    // Can chnage Page to List
    //Get Expenses by Year, Month, and Type
    public Page<Expense> getExpensesByYearMonthAndType(int year, Month month, String expenseType, Pageable page) {
    LocalDate startDate = LocalDate.of(year, month, 1);
    LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
    return expenseRepository.findByDateBetweenAndExpenseTypeOrderByCreationDateDesc(startDate, endDate, expenseType, page);
}

    //Get expenses by year and month
    public Page<Expense> getExpensesByYearMonth(int year, Month month, Pageable page) {
    LocalDate startDate = LocalDate.of(year, month, 1);
    LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
    return expenseRepository.findByDateBetweenOrderByCreationDateDesc(startDate, endDate, page);
}


    //get expenses by type
    public Page<Expense> getExpensesByType(String expenseType, Pageable page) {
        return expenseRepository.findByExpenseTypeOrderByCreationDateDesc(expenseType, page);
    }
    
 



}
