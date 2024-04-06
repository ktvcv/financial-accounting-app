package com.example.financialaccountingapp.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class AuthRequestDto {
    private String username;
    private String password;
}
