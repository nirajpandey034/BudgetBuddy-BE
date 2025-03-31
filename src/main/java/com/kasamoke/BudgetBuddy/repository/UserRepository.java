package com.kasamoke.BudgetBuddy.repository;

import com.kasamoke.BudgetBuddy.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface UserRepository extends MongoRepository<UserModel, UUID> {

    boolean existsById(UUID id);
    boolean existsByEmail(String email);

    UserModel findByEmail(String email);
}
