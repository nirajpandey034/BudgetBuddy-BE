package com.kasamoke.BudgetBuddy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/create")
    public ResponseEntity<?> createUser() {
        return ResponseEntity.status(200).body("Hi from create User");
    }

}
