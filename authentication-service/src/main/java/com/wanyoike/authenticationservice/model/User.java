package com.wanyoike.authenticationservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Enter first name")
    @Column(nullable = false, name = "first_name")
    private String firstName;

    @NotBlank(message = "Enter last name")
    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Email(message = "Enter a valid email")
    @NotBlank(message = "Enter valid email")
    @Column(unique = true, nullable = false, name = "email")
    private String email;

    @NotBlank(message = "Enter password, at least 8 characters long")
    @Column(nullable = false, name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

}
