package com.kasamoke.BudgetBuddy.config;

import com.kasamoke.BudgetBuddy.enums.CategoryType;
import com.kasamoke.BudgetBuddy.model.CategoryModel;
import com.kasamoke.BudgetBuddy.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.UUID;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedCategories(CategoryRepository categoryRepository) {
        return args -> {
            Arrays.stream(CategoryType.values()).forEach(categoryType -> {
                if(categoryRepository.findByTitle(categoryType.name()).isEmpty()) {
                    CategoryModel category = new CategoryModel(UUID.randomUUID(), categoryType);
                    categoryRepository.save(category);
                }
            });
        };
    }
}
