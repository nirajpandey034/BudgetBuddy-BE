package com.kasamoke.BudgetBuddy.repository;

import com.kasamoke.BudgetBuddy.model.ExpenseModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ExpenseRepository extends MongoRepository<ExpenseModel, UUID> {
}
