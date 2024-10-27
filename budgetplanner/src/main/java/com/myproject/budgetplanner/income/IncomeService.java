package com.myproject.budgetplanner.income;

import org.springframework.stereotype.Service;

import com.myproject.budgetplanner.expense.Expense;

import jakarta.persistence.EntityNotFoundException;

@Service
public class IncomeService {
    public final IncomeRepository incomeRepository;

    public IncomeService(IncomeRepository incomeRepository){
        this.incomeRepository = incomeRepository;
    }


    public Income getIncome(Long id){
        return incomeRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Income not found with id: " + id));
        
    }


    public Income createIncome(Income income){
        return incomeRepository.save(income);
    }

    //updateIncome
    public Income updateIncome(Long id, Income updatedIncome) {
        // Check if the expense exists
        Income existingIncome = incomeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Income not found with id: " + id));
        
        // Update fields
        //existingIncome.setName(updatedIncome.getName());
        //existingIncome.setExpenseType(updatedIncome.getExpenseType());
       // existingIncome.setAmount(updatedIncome.getAmount());
       // existingIncome.setDate(updatedIncome.getDate());
        
        return incomeRepository.save(existingIncome);
    }


    //deleteIncome
    public void deleteIncome(Income income) {
        incomeRepository.delete(income);
    }


}
