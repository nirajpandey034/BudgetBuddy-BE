package com.kasamoke.BudgetBuddy.service;

import com.kasamoke.BudgetBuddy.model.ExpenseModel;
import com.kasamoke.BudgetBuddy.repository.ExpenseRepository;
import com.kasamoke.BudgetBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.*;

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
                .set("totalIncome", expense.getTotalIncome()) // <-- Added totalIncome here
                .set("deficit", expense.getDeficit());

        // Upsert: update if exists, insert if not
        mongoTemplate.upsert(query, update, ExpenseModel.class);
        return expense;
    }

    // ✅ Get Expenses by UserId, Month, and Year
    public List<ExpenseModel> getExpensesByMonth(UUID userId, int month, int year) {
        // Create start date for the month
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1); // Months are zero-based in Java
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();

        // Create end date for the month
        calendar.add(Calendar.MONTH, 1);
        Date endDate = calendar.getTime();

        // Query to filter expenses by userId and timestamp range
        Query query = new Query(Criteria.where("userId").is(userId)
                .and("expenseTimestamp").gte(startDate).lt(endDate))
                .with(Sort.by(Sort.Direction.DESC, "expenseTimesamp")); // Sort by timestamp descending

        return mongoTemplate.find(query, ExpenseModel.class);
    }


}
