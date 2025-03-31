package com.kasamoke.BudgetBuddy.service;

import com.kasamoke.BudgetBuddy.model.CategoryModel;
import com.kasamoke.BudgetBuddy.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<CategoryModel> getCategories() {
        try{
            return categoryRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
