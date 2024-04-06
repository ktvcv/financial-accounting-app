package com.example.financialaccountingapp.api;

import com.example.financialaccountingapp.dto.TokenWrapper;
import com.example.financialaccountingapp.dto.UserDTO;
import com.example.financialaccountingapp.model.User;
import com.example.financialaccountingapp.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.Servlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/v1/")
public class AuthControllerV1 {


    @Autowired
    private UserService userService;

    @PostMapping({"/register", "/registerUser"})
    public ResponseEntity<Void> register(@RequestBody UserDTO dto) {
        User entity = User.fromDto(dto);
        userService.registerNewUser(entity);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    //@RolesAllowed()
    @PostMapping("/login")
    public ResponseEntity<TokenWrapper> login(@RequestBody UserDTO dto) {
        User entity = User.fromDto(dto);
        userService.registerNewUser(entity);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @PostMapping("/get")
    public ResponseEntity<TokenWrapper> getAll(@RequestBody UserDTO dto, User user) {
        User entity = User.fromDto(dto);
        userService.registerNewUser(entity);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}
