package com.example.financialaccountingapp.dto;

import com.example.financialaccountingapp.model.User;
import com.example.financialaccountingapp.model.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
public class UserDTO {

    private Long id;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private UserRole role;
    private String firstName;
    private String lastName;
    private boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UserDTO fromModel(final User userModel){
        return new UserDTO()
            .setId(userModel.getId())
            .setEnabled(userModel.isEnabled())
            .setRole(userModel.getRole())
            .setUsername(userModel.getUsername())
            .setFirstName(userModel.getFirstName())
            .setLastName(userModel.getLastName())
            .setCreatedAt(userModel.getCreatedAt())
            .setUpdatedAt(userModel.getUpdatedAt());
    }
}
