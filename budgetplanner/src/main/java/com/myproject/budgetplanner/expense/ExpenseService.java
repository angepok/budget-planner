package com.myproject.budgetplanner.expense;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

@Service
public class ExpenseService {

    public final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    // getAllExpenses
    public List<Expense> getAllExpensesList() {
        return expenseRepository.findAll();
    }

    // getExpenseById
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense not found with id: " + id));
    }

    /**
    * Creates a new expense record.
    *
    * @param expense the expense data to be saved
    * @return the saved Expense object
    * @throws ExpenseException if the expense amount is negative or invalid
    */
    // createExpense
    public Expense createExpense(@NotNull Expense expense) throws ExpenseException {
        return expenseRepository.save(expense);
    }

    /*
     * Updates an existing Expense record.
     * fetches the existing expense by ID, updates the specified fields,
     * and saves the changes to the database.
     *
     * @param id the ID of the expense to update
     * 
     * @param updatedExpense the expense data to update with
     * 
     * @return the updated Expense object
     * 
     * @throws EntityNotFoundException if the expense with the specified ID is not
     * found
     */

    // updateExpense
    public Expense updateExpense(Long id, Expense updatedExpense) {
        // Check if the expense exists
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense not found with id: " + id));

        // Update fields
        existingExpense.setName(updatedExpense.getName());
        existingExpense.setAmount(updatedExpense.getAmount());
        existingExpense.setDate(updatedExpense.getDate());
        existingExpense.setCreationDate(updatedExpense.getCreationDate());

        // Save the updated expense
        return expenseRepository.save(existingExpense);
    }

    // deleteExpense
    public void deleteExpense(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new EntityNotFoundException("Expense not found with id: " + id);
        }
        expenseRepository.deleteById(id);
    }

    // Get totalExpenses
    public BigDecimal getTotalExpense() {
        return expenseRepository.findTotalExpense();

    }

    // get total expenses by Month
    public BigDecimal getExpensesByMonth(int year, Month month) {
        return expenseRepository.findTotalExpenseByMonth(year, month);
    }

    // get total expenses by year
    public BigDecimal findExpensesByYear(int year) {
        return expenseRepository.findTotalExpenseByYear(year);
    }

}
