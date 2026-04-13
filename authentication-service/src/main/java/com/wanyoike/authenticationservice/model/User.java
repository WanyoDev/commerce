package com.wanyoike.authenticationservice.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
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

    @NotNull(message = "Enter first name")
    @Column(unique = true,  nullable = false,name = "first_name")
    private String firstName;

    @NotNull(message = "Enter last name")
    @Column(unique = true,  nullable = false,name = "last_name")
    private String lastName;

    @Email(message = "Enter a valid email")
    @NotBlank(message = "Enter valid email")
    @Column(unique = true, nullable = false, name = "email")
    private String email;

    @NotBlank(message = "Password must be at least 8 characters")
    @Column(unique = true, nullable = false, name = "password")
    @Size(min = 8, max = 20)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "role")
    private Role role;

}
