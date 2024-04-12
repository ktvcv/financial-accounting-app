package com.example.financialaccountingapp.security;

import com.example.financialaccountingapp.errors.AuthException;
import com.example.financialaccountingapp.model.User;
import com.example.financialaccountingapp.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceimpl implements UserDetailsService {

    @Autowired
    private AuthRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.fetchUserByUserName(username);

        if (user.isEmpty()){
            throw new AuthException("User with username not found " + username);
        }

        return user.get();
    }
}
