package com.myproject.budgetplanner.expenseType;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api/expenseType")
public class ExpenseTypeController {

    //constructor based dependency injection
    public final ExpenseTypeService expenseTypeService;

    @Autowired
    public ExpenseTypeController(ExpenseTypeService expenseTypeService){
        this.expenseTypeService = expenseTypeService;
    }

    @GetMapping
    public List<ExpenseType> getAllExpenseTypes(){
        return expenseTypeService.getAllExpenseTypes();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ExpenseType getExpenseTypeById(@PathVariable long id){
        return expenseTypeService.getExpenseTypeById(id);
    }

    @PostMapping(produces = "application/json")
    public ExpenseType createExpenseType(@RequestBody ExpenseType expenseType) throws ExpenseTypeException{
        return expenseTypeService.createExpenseType(expenseType);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteExpenseTypeById(@PathVariable(value = "id") long id){
        expenseTypeService.deleteExpenseType(id);
    }
}

