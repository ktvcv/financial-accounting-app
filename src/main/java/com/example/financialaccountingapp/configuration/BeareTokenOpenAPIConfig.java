package com.example.financialaccountingapp.configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(name = "bearerAuth",type = SecuritySchemeType.HTTP,
    scheme = "bearer", bearerFormat = "JWT", in = SecuritySchemeIn.HEADER
)
public class BeareTokenOpenAPIConfig {
}