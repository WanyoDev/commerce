package com.wanyoike.authenticationservice.controller;

import com.wanyoike.authenticationservice.model.User;
import com.wanyoike.authenticationservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

    @GetMapping("/user")
    public ResponseEntity<User> findUserByEmail(@RequestParam("email") String email) {
        return new ResponseEntity<>(userService.findUserByEmail(email), HttpStatus.FOUND);
    }

    @PutMapping("/user/{email}")
    public ResponseEntity<User> updateUser(@PathVariable("email") String email, @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(email, user), HttpStatus.OK);
    }
}
