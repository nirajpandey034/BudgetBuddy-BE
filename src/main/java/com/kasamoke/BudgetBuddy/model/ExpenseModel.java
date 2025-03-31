package com.kasamoke.BudgetBuddy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kasamoke.BudgetBuddy.enums.CategoryType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Document(collection = "expenses")
public class ExpenseModel {
    @Id
    private String id; // <-- Changed from UUID to String

    @NotNull(message = "User Id is mandatory")
    private UUID userId;

    @NotNull(message = "Expense categories are mandatory")
    private Map<CategoryType, Double> expenseCategories;

    @NotNull(message = "Expense timestamp is mandatory")
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "UTC")
    private Date expenseTimestamp;

    @NotNull(message = "Total income is mandatory")
    @PositiveOrZero(message = "Total income must be zero or a positive value")
    private Double totalIncome;

    public ExpenseModel() {
    }

    public ExpenseModel(String id, UUID userId, Map<CategoryType, Double> expenseCategories, Date expenseTimestamp, Double totalIncome) {
        this.id = id;
        this.userId = userId;
        this.expenseCategories = expenseCategories;
        this.expenseTimestamp = expenseTimestamp;
        this.totalIncome = totalIncome;
    }

    // Getters and Setters
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

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    @Override
    public String toString() {
        return "ExpenseModel{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", expenseCategories=" + expenseCategories +
                ", expenseTimestamp=" + expenseTimestamp +
                ", totalIncome=" + totalIncome +
                '}';
    }
}
