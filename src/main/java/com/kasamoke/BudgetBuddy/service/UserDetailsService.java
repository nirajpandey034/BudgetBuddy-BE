package com.kasamoke.BudgetBuddy.service;

import com.kasamoke.BudgetBuddy.model.UserModel;
import com.kasamoke.BudgetBuddy.model.UserPrinciple;
import com.kasamoke.BudgetBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user =userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        else {
            return new UserPrinciple(user);
        }
    }
}
