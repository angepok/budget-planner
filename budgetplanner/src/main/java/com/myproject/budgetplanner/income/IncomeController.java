package com.myproject.budgetplanner.income;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.budgetplanner.expense.Expense;
import com.myproject.budgetplanner.expense.ExpenseException;
import com.myproject.budgetplanner.expense.ExpenseService;

@RestController
public class IncomeController {

    public final IncomeService incomeService;

    public IncomeController(IncomeService incomeService){
        this.incomeService = incomeService;
    }

    @GetMapping
    public List<Income> getAllIncome(){
        return incomeService.findAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public  Income getIncomeById(@PathVariable long id){
        return incomeService.findById(id);
    }

    @PostMapping(produces = "application/json")
    public Income createIncome(@RequestBody Income income) throws IncomeException{
        return incomeService.createIncome(income);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteIncome(Income income){
        incomeService.deleteIncome(income);
    }
}
