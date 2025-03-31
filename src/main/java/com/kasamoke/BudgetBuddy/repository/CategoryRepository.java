package com.kasamoke.BudgetBuddy.repository;


import com.kasamoke.BudgetBuddy.model.CategoryModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends MongoRepository<CategoryModel, UUID> {
    Optional<CategoryModel> findByTitle(String title);
}
