package com.wanyoike.authenticationservice.controller;

import com.wanyoike.authenticationservice.dtos.AuthRequestDTO;
import com.wanyoike.authenticationservice.dtos.UserDTO;
import com.wanyoike.authenticationservice.service.UserDetailsServiceImpl;
import com.wanyoike.authenticationservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService userService;
    private final UserDetailsServiceImpl userDetailsService;

    public UserController(UserService userService, UserDetailsServiceImpl userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequestDTO request) {
        return new ResponseEntity<>(userDetailsService.authenticateUser(request), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .build();
        }
        return userDetailsService.validateToken(authHeader.substring(7))
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }

    @GetMapping("/admin/users")
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.FOUND);
    }

    @DeleteMapping("/admin/delete/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

    @GetMapping("/admin/user")
    public ResponseEntity<UserDTO> findUserByEmail(@RequestParam("email") String email) {
        return new ResponseEntity<>(userService.findUserByEmail(email), HttpStatus.FOUND);
    }

    @PutMapping("/user/{email}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("email") String email, @RequestBody UserDTO user) {
        return new ResponseEntity<>(userService.updateUser(email, user), HttpStatus.OK);
    }
}
