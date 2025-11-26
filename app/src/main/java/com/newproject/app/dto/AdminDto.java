package com.newproject.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AdminDto {

    @NotBlank(message = "username is required")
    private String username;

    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "mobileNumber is required")
    private String mobileNumber;
}
