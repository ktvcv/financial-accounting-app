package com.example.financialaccountingapp.security;

import com.example.financialaccountingapp.errors.AuthException;
import com.example.financialaccountingapp.model.User;
import com.example.financialaccountingapp.repository.AuthRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceimpl implements UserDetailsService {

    private AuthRepository repository;


    public UserDetailsServiceimpl(AuthRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.fetchUserByUserName(username);

        if (user == null){
            throw new AuthException("User with username not found " + username);
        }

        return user;
    }
}
