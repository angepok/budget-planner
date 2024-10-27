package com.myproject.budgetplanner.expense;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import com.myproject.budgetplanner.expense.ExpenseRepository;
import com.myproject.budgetplanner.expense.Expense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.StreamSupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
//@Transactional
public class ExpenseService {

    public final ExpenseRepository expenseRepository;

    //instance of expense repo
    public ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }

    /* 
    //getAllExpenses
    public List<Expense> getAllExpenses(){
        Sort.by("creationDate").descending();
        return expenseRepository.findAll();
    }

    //getExpenseById
    public Expense getExpenseById(Long id){
        return expenseRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Expense not found with id: " + id));
    }
    //createExpense
    public Expense createExpense(Expense entity) {
        return expenseRepository.save(entity);
    }
    

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
    
        // Save the updated expense
        return expenseRepository.save(existingExpense);
    }
    

    //deleteExpense
    public void deleteById(Long id) {
        Expense expenseToBeDeleted = findById(id);
        expenseRepository.delete(expenseToBeDeleted);
    }
        */
    
    //necessaary for budget class
    
    //Get totalExpenses
    public BigDecimal getTotalAmount(Iterable<Expense> expenses) {
    return StreamSupport
            .stream(expenses.spliterator(), false)
            .map(Expense::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
}

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
