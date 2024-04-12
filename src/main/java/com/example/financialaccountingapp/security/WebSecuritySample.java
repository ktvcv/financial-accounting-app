package com.example.financialaccountingapp.security;

import com.example.financialaccountingapp.model.User;

public class WebSecuritySample {

    public boolean isUserIsValid(User user){

        return user.isEnabled();
    }
}
