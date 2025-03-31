package com.kasamoke.BudgetBuddy.model;

import com.kasamoke.BudgetBuddy.enums.CategoryType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "categories")
public class CategoryModel {

    @Id
    private UUID id;

    @NotBlank(message = "Category type is mandatory")
    private CategoryType title;

    public CategoryModel() {
    }

    public CategoryModel(UUID id, CategoryType title) {
        this.id = id;
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CategoryType getTitle() {
        return title;
    }

    public void setTitle(CategoryType title) {
        this.title = title;
    }
}
