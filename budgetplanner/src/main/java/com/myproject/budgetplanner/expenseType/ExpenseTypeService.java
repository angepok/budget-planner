package com.myproject.budgetplanner.expenseType;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class ExpenseTypeService {
    
    private final ExpenseTypeRepository expenseTypeRepository;

    //instance of expensetype repo
    public ExpenseTypeService(ExpenseTypeRepository expenseTypeRepository){
        this.expenseTypeRepository = expenseTypeRepository;
    }

    //getExpenseTypes //not necessary
    public List<ExpenseType> getAllExpenseTypes(){
        return expenseTypeRepository.findAll();
    }

    //getExpensebyId
    //This part of the code handles the case where no ExpenseType entity is found for the given ID. If the Optional is empty, it throws an EntityNotFoundException.
    public ExpenseType getExpenseTypeById(Long id) {
        return expenseTypeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    //create expensetype
    public ExpenseType createExpenseType(ExpenseType expenseType){
        return expenseTypeRepository.save(expenseType);
    }


    //delete expenseType
    void deleteExpenseType(Long id){
        expenseTypeRepository.deleteById(id);
    }


}
