package com.newproject.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "userName is required")
    private String username;

    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "email is required")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "mobileNumber is required")
    private String mobileNumber;
}
