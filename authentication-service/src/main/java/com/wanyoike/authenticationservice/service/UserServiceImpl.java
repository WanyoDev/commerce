package com.wanyoike.authenticationservice.service;

import com.wanyoike.authenticationservice.dto.UserDTO;
import com.wanyoike.authenticationservice.dto.UserMapper;
import com.wanyoike.authenticationservice.exceptions.UserEmailNotFoundException;
import com.wanyoike.authenticationservice.model.User;
import com.wanyoike.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDTO)));

    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return userMapper.listToDto(allUsers);
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        User userExists = userRepository.findByEmail(email);
        if (userExists == null)
            throw new UserEmailNotFoundException("User email not found " + email);
        else
            return userMapper.toDto(userExists);

    }

    @Override
    public UserDTO updateUser(String email, UserDTO userDTO) {
        User userExists = userRepository.findByEmail(email);
        if (userExists == null) {
            throw new UserEmailNotFoundException("User email not found " + email);
        } else {
            userExists.setFirstName(userDTO.getFirstName());
            userExists.setLastName(userDTO.getLastName());
            userExists.setEmail(userDTO.getEmail());
        }

      return userMapper.toDto(userRepository.save(userExists));
    }


}
