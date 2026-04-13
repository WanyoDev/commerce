package com.wanyoike.authenticationservice.service;

import com.wanyoike.authenticationservice.exceptions.UserEmailNotFoundException;
import com.wanyoike.authenticationservice.model.User;
import com.wanyoike.authenticationservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findUserByEmail(String email) {
        User userExists = userRepository.findByEmail(email);
        if (userExists == null)
            throw new UserEmailNotFoundException("User email not found " + email);
        else
        return userExists;

    }
}
