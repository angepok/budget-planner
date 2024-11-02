package com.myproject.budgetplanner.income;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/income")
public class IncomeController {

    public final IncomeService incomeService;

    @Autowired
    public IncomeController(IncomeService incomeService){
        this.incomeService = incomeService;
    }

    @GetMapping
    public List<Income> getAllIncome() {
        return incomeService.getAllIncome();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Income> getIncomeById(@PathVariable Long id) {
        Income income = incomeService.getIncome(id);
        if (income == null) {
            throw new IncomeNotFoundException("Income not found with id: " + id);
        }
        return ResponseEntity.ok(income);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<Income> createIncome(@RequestBody Income income) throws IncomeException {
        Income createdIncome = incomeService.createIncome(income);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdIncome);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Income> updateIncome(@PathVariable Long id, @RequestBody Income updatedIncome) {
        Income income = incomeService.updateIncome(id, updatedIncome);
        return ResponseEntity.ok(income);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long id) {
        Income income = incomeService.getIncome(id);
        if (income == null) {
            throw new IncomeNotFoundException("Income not found with id: " + id);
        }
        incomeService.deleteIncome(id);
        return ResponseEntity.noContent().build(); // Returns 204 No Content
    }
}

    /* 
    @GetMapping
    public List<Income> getAllIncome(){
        return incomeService.getAllIncome();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public  Income getIncomeById(@PathVariable long id) throws Exception{
        Income income = incomeService.getIncome(id);
        if (income == null) {
            throw new Exception("Income not found with id: " + id); // Custom exception
        }
        return income;  // Return the found income
        //return incomeService.getIncome(id);
    }

    @PostMapping(produces = "application/json")
    public Income createIncome(@RequestBody Income income) throws IncomeException{
        return incomeService.createIncome(income);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public Income updateIncome(@PathVariable Long id, @RequestBody Income updatedIncome)
    {
        return incomeService.updateIncome(id, updatedIncome);
    } 


    @DeleteMapping(value = "/{id}")
    public void deleteIncome(@PathVariable Long id){
        incomeService.deleteIncome(id);
        
    }
    */

