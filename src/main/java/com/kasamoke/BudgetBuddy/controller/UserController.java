package com.kasamoke.BudgetBuddy.controller;

import com.kasamoke.BudgetBuddy.model.UserDTO;
import com.kasamoke.BudgetBuddy.model.UserModel;
import com.kasamoke.BudgetBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping({"/register", "/register/"})
    public ResponseEntity<?> createUser(@RequestBody UserModel userModel) {
        try{
            Optional<UserDTO> user = userService.createUser(userModel);
            if(user.isPresent()){
                return ResponseEntity.status(200).body(user);
            }
            return ResponseEntity.status(400).body("Error Occurred");
        } catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping({"/login", "/login/"})
    public ResponseEntity<?> loginUser(@RequestBody UserModel user) throws Exception{
        try {
            Map<String, Object> response = userService.loginUser(user);
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
