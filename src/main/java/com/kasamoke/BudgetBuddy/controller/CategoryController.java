package com.kasamoke.BudgetBuddy.controller;

import com.kasamoke.BudgetBuddy.model.CategoryModel;
import com.kasamoke.BudgetBuddy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping({"", "/"})
    public ResponseEntity<?> getCategories() {
        try{
            List<CategoryModel> categories =  categoryService.getCategories();
            return ResponseEntity.status(200).body(categories);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
