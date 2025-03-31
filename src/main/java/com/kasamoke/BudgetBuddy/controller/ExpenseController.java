package com.kasamoke.BudgetBuddy.controller;

import com.kasamoke.BudgetBuddy.model.ExpenseModel;
import com.kasamoke.BudgetBuddy.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @PostMapping({"/add", "/add/"})
    public ResponseEntity<?> creteExpense(@RequestBody ExpenseModel expense) {
        try{
            System.out.println(expense);
            Optional<ExpenseModel> expenseModel = Optional.ofNullable(expenseService.addExpense(expense));
            if(expenseModel.isPresent()){
                return ResponseEntity.status(200).body("Expense Added");
            }
            else{
                return ResponseEntity.status(400).body("Something went wrong, Please try again");
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
