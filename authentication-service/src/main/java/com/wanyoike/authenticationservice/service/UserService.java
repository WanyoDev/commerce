package com.wanyoike.authenticationservice.service;

import com.wanyoike.authenticationservice.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    User createUser(User user);
    List<User> findAllUsers();
    void deleteUser(UUID id);
    User findUserByEmail(String email);
    User updateUser(String email, User user);
}
