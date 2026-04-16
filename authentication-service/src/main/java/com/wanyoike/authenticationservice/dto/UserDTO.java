package com.wanyoike.authenticationservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private UUID id;

    @NotNull(message = "Enter first name")
    private String firstName;

    @NotNull(message = "Enter last name")
    private String lastName;

    @NotBlank(message = "Enter a valid email address")
    @Email(message = "Enter a valid email address")
    private String email;

    private String role;
}
