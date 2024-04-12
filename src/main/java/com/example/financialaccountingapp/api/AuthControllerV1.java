package com.example.financialaccountingapp.api;

import com.example.financialaccountingapp.dto.LoginRequest;
import com.example.financialaccountingapp.dto.TokenWrapper;
import com.example.financialaccountingapp.dto.UserDTO;
import com.example.financialaccountingapp.model.User;
import com.example.financialaccountingapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("api/auth/v1")
public class AuthControllerV1 {


    @Autowired
    private UserService userService;

    @SecurityRequirement(name = "BeareTokenOpenAPIConfig")
    @Operation(
        summary = "Register new user",
        description = "Register new user with unique email",
        method = "register",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Request body for registering user", required = true,
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = UserDTO.class))),
        responses = {
            @ApiResponse(responseCode = "200",
                description = "New user was registered successfully",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "500",
                description = "New user was not registered successfully, some error occurred",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class))),
        }
    )
    @PostMapping({"/register", "/registerUser"})
    public ResponseEntity<Void> register(@RequestBody UserDTO dto) {
        User entity = User.fromDto(dto);
        userService.registerNewUser(entity);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    //@RolesAllowed()
    @PostMapping("/login")
    public ResponseEntity<TokenWrapper> login(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(userService.login(loginRequest), HttpStatusCode.valueOf(200));
    }
}
