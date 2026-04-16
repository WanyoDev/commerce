package com.wanyoike.authenticationservice.service;

import com.wanyoike.authenticationservice.dto.UserDTO;
import com.wanyoike.authenticationservice.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> findAllUsers();
    void deleteUser(UUID id);
    UserDTO findUserByEmail(String email);
    UserDTO updateUser(String email, UserDTO userDTO);
}
