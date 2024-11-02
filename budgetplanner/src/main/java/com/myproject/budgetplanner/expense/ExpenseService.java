package com.myproject.budgetplanner.expense;


import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;
import java.util.Optional;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

@Service
public class ExpenseService {

    public final ExpenseRepository expenseRepository;
    
    //instance of expense repo
    //@Autowired
    public ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
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
    
        /*
    * Updates an existing Expense record.
    *fetches the existing expense by ID, updates the specified fields, 
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
        existingExpense.setAmount(updatedExpense.getAmount());
        existingExpense.setDate(updatedExpense.getDate());
    

        // Save the updated expense
        return expenseRepository.save(existingExpense);
    }

    
    //deleteExpense
    public void deleteExpense(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense not found with id: " + id));
        expenseRepository.delete(expense);
    }
        

    
    //Get totalExpenses
    public BigDecimal getTotalExpense() { 
        return expenseRepository.findTotalExpense(); 

     }

    // // get total expenses by Month
    // public BigDecimal getExpensesByMonth(int year, Month month){
    //     return expenseRepository.findTotalExpenseByMonth(year, month);
    // }

    // get total expenses by year
    public BigDecimal findExpensesByYear(int year){
        return expenseRepository.findTotalExpenseByYear(year);
    } 

    //Get list of expenses by Month
    //public List<Expense> getListExpensesByMonth(int year, Month month){
     //return expenseRepository.findByYearAndMonth(year, month.getValue());
    //}

    //get list of expense by year
    //public List<Expense> getExpensesByYear(int year) {
    //   return expenseRepository.findByYear(year);
    //}

    /* 
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
  */  
}



