package com.kasamoke.BudgetBuddy.controller;

import com.kasamoke.BudgetBuddy.model.ExpenseModel;
import com.kasamoke.BudgetBuddy.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    // ✅ Get expenses by month and year
    @GetMapping({"/list", "/list"})
    public ResponseEntity<?> getExpensesByMonth(
            @RequestParam UUID userId,
            @RequestParam int month,
            @RequestParam int year) {

        try {
            List<ExpenseModel> expenses = expenseService.getExpensesByMonth(userId, month, year);
            if (expenses.isEmpty()) {
                return ResponseEntity.status(404).body("No expenses found for the given month and year.");
            }
            return ResponseEntity.ok(expenses);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
