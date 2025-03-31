package com.kasamoke.BudgetBuddy.service;

import com.kasamoke.BudgetBuddy.model.UserDTO;
import com.kasamoke.BudgetBuddy.model.UserModel;
import com.kasamoke.BudgetBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Optional<UserDTO> createUser(UserModel userModel) {
        if(userRepository.existsByEmail(userModel.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        userModel.setPassword(encoder.encode(userModel.getPassword()));
        UserModel newUser = userRepository.save(userModel);
        UserModel user = userRepository.save(newUser);
        UserDTO userDTO = new UserDTO(user.getId(), user.getEmail(), user.getName());
        return Optional.of(userDTO);

    }

    public Map<String, Object> loginUser(UserModel user) throws Exception {
        Map<String, Object> obj = new HashMap<>();
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(user.getEmail());
                UUID id = userRepository.findByEmail(user.getEmail()).getId();
                obj.put("token", token);
                obj.put("id", id);
            } else {
                throw new UsernameNotFoundException("Invalid email");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return obj;
    }
}
