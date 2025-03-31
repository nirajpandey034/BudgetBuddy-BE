package com.kasamoke.BudgetBuddy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kasamoke.BudgetBuddy.enums.CategoryType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Document(collection = "expenses")
public class ExpenseModel {
    @Id
    private String id; // <-- Change from UUID to String

    @NotNull(message = "User Id is mandatory")
    private UUID userId;

    @NotNull(message = "Expense categories are mandatory")
    private Map<CategoryType, Double> expenseCategories;

    @NotNull(message = "Expense timestamp is mandatory")
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "UTC")
    private Date expenseTimestamp;

    public ExpenseModel() {
    }

    public ExpenseModel(String id, UUID userId, Map<CategoryType, Double> expenseCategories, Date expenseTimestamp) {
        this.id = id;
        this.userId = userId;
        this.expenseCategories = expenseCategories;
        this.expenseTimestamp = expenseTimestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Map<CategoryType, Double> getExpenseCategories() {
        return expenseCategories;
    }

    public void setExpenseCategories(Map<CategoryType, Double> expenseCategories) {
        this.expenseCategories = expenseCategories;
    }

    public Date getExpenseTimestamp() {
        return expenseTimestamp;
    }

    public void setExpenseTimestamp(Date expenseTimestamp) {
        this.expenseTimestamp = expenseTimestamp;
    }

    @Override
    public String toString() {
        return "ExpenseModel{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", expenseCategories=" + expenseCategories +
                ", expenseTimestamp=" + expenseTimestamp +
                '}';
    }
}
