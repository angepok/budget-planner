package com.myproject.budgetplanner.expenseType;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import com.myproject.budgetplanner.expenseType.*;



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
    //This part of the code handles the case where no ExpenseType entity is found for the given ID. 
    //If the Optional is empty, it throws an EntityNotFoundException
    public ExpenseType getExpenseTypeById(Long id) {
        return expenseTypeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    //create expensetype
    /**
     * Saves an ExpenseType entity.
     *
     * This method saves the provided ExpenseType entity to the database after performing
     * a uniqueness check based on the expense category. If an ExpenseType with the same
     * expense category (case-insensitive) already exists, an exception is thrown.
     *
     * param entity The ExpenseType entity to be saved.
     * return The saved ExpenseType entity.
     * throws ExpenseTypeAlreadyExistsException If an ExpenseType already exists in the database.
     */
    public ExpenseType createExpenseType(ExpenseType expenseType) throws ExpenseTypeException {
        if (expenseTypeRepository.existsByExpenseTypeIgnoreCase(expenseType.getExpenseType())){
        throw new ExpenseTypeException("Expense type with name " + expenseType.getExpenseType() 
        + "already exists.");
    }
        return expenseTypeRepository.save(expenseType);
    }


    //delete expenseType
    void deleteExpenseType(Long id){
        expenseTypeRepository.deleteById(id);
    }

    /**
     * Initializes the application's default data.
     *
     * This method is automatically executed during application startup due to the
     * presence of the {@link PostConstruct} annotation. It checks if there are any
     * existing ExpenseType records in the database. If no records are found, it
     * creates and saves a default ExpenseType with the name "Home".
     
    @PostConstruct
    public void init() {
        List <ExpenseType> allExpenses = expenseTypeRepository.findAll();
        if (((Collection<?>) allExpenses).isEmpty()) {
            ExpenseType defaultExpenseType = new ExpenseType(null, "Home");
            expenseTypeRepository.save(defaultExpenseType);
        }
    }
        */

}
