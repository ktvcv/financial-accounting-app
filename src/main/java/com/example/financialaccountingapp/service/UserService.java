package com.example.financialaccountingapp.service;

import com.example.financialaccountingapp.model.User;
import com.example.financialaccountingapp.model.UserRole;
import com.example.financialaccountingapp.repository.AuthRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;
    private final Clock clock;

    public UserService(PasswordEncoder passwordEncoder,
                       AuthRepository authRepository,
                       Clock clock) {
        this.passwordEncoder = passwordEncoder;
        this.authRepository = authRepository;
        this.clock = clock;
    }

    public void registerNewUser(final User user) {
        if (authRepository.isUserIsNew(user.getUsername())) {
            user
                .setRole(UserRole.USER)
                .setPassword(passwordEncoder.encode(user.getPassword()))
                .setCreatedAt(LocalDateTime.now(clock));
            authRepository.saveUser(user);
        } else {
            throw new RuntimeException("There is already user with email: " + user.getUsername());
        }
    }

    public void login(final User user) {
        if (authRepository.isUserIsNew(user.getUsername())) {
            user
                .setRole(UserRole.USER)
                .setPassword(passwordEncoder.encode(user.getPassword()))
                .setCreatedAt(LocalDateTime.now(clock));
            authRepository.saveUser(user);
        } else {
            throw new RuntimeException("There is already user with email: " + user.getUsername());
        }
    }

}
