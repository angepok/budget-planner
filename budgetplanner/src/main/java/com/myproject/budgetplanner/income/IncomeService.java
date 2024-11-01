package com.myproject.budgetplanner.income;

import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

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

    public List<Income> getAllIncome(){
        return incomeRepository.findAll();  
    }


    public Income createIncome(Income income) throws IncomeException {
        if (income.getAmount().compareTo(BigDecimal.ZERO) < 0) {
        throw new IncomeException("Income amount cannot be negative.");
    }
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
    public void deleteIncome(Long id) {
        incomeRepository.deleteById(id);
    }

    //get total income
    public BigDecimal getTotalIncome() {
        List<Income> incomes = incomeRepository.findAll();
        return incomes.stream()
                .map(Income::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    //get total income per Month
    public List<Income> getIncomeByMonth (int year, Month month){
        return incomeRepository.findTotalIncomeByMonth(year, month);

    }

    //get total income per Yera
    public List<Income> getIncomeByYear (int year){
        return incomeRepository.findTotalIncomeByYear(year);

    }




}
