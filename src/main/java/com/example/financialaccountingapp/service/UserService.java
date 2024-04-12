package com.example.financialaccountingapp.service;

import com.example.financialaccountingapp.dto.LoginRequest;
import com.example.financialaccountingapp.dto.TokenWrapper;
import com.example.financialaccountingapp.model.User;
import com.example.financialaccountingapp.model.UserRole;
import com.example.financialaccountingapp.repository.AuthRepository;
import com.example.financialaccountingapp.security.JWTService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
@Log4j2
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;
    private final Clock clock;

    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;

    public UserService(PasswordEncoder passwordEncoder,
                       AuthRepository authRepository,
                       Clock clock, JWTService jwtService, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.authRepository = authRepository;
        this.clock = clock;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public void registerNewUser(final User user) {
        boolean userIsNew = authRepository.isUserIsNew(user.getUsername());
        if (!userIsNew) {
            user
                .setRole(UserRole.USER)
                .setPassword(passwordEncoder.encode(user.getPassword()))
                .setCreatedAt(LocalDateTime.now(clock))
                .setUpdatedAt(LocalDateTime.now(clock))
                .setRole(UserRole.USER)
                .setEnabled(true);
            authRepository.saveUser(user);
            log.info("Registered new user: " + user.getUsername());
        } else {
            throw new RuntimeException("There is already user with email: " + user.getUsername());
        }
    }

    public TokenWrapper login(final LoginRequest loginRequest) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );
        User user = authRepository.fetchUserByUserName(loginRequest.getUsername()).orElseThrow();
        return new TokenWrapper(jwtService.generateToken(user), jwtService.getExpirationSeconds());
    }

}