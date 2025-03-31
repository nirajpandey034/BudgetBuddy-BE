package com.kasamoke.BudgetBuddy.service;

import com.kasamoke.BudgetBuddy.model.ExpenseModel;
import com.kasamoke.BudgetBuddy.repository.ExpenseRepository;
import com.kasamoke.BudgetBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public ExpenseModel addExpense(ExpenseModel expense) {
        Query query = new Query(Criteria.where("userId").is(expense.getUserId())
                .and("expenseTimestamp").is(expense.getExpenseTimestamp()));

        Update update = new Update()
                .set("expenseCategories", expense.getExpenseCategories())
                .set("expenseTimestamp", expense.getExpenseTimestamp())
                .set("totalIncome", expense.getTotalIncome()); // <-- Added totalIncome here

        // Upsert: update if exists, insert if not
        mongoTemplate.upsert(query, update, ExpenseModel.class);
        return expense;
    }


}
