package com.kasamoke.BudgetBuddy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "users")
public class UserModel {

    @Id
    private UUID id;
    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name should not be longer than 100 characters")
    private String name;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password should have at least 6 characters")
    private String password;
    @NotBlank(message = "Profession is mandatory")
    @Size(max = 100, message = "Name should not be longer than 100 characters")
    private String profession;

    public UserModel() {
        this.id = UUID.randomUUID();
    }

    public UserModel(UUID id, String name, String email, String password, String profession) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
        this.profession = profession;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
