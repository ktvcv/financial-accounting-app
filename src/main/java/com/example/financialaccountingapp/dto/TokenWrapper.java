package com.example.financialaccountingapp.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TokenWrapper {
    private final String token;
    private final long expirationInSeconds;
}
