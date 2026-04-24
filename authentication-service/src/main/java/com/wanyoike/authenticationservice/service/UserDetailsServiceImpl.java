package com.wanyoike.authenticationservice.service;

import com.wanyoike.authenticationservice.model.User;
import com.wanyoike.authenticationservice.model.UserPrincipal;
import com.wanyoike.authenticationservice.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User existingUser = userRepository.findByEmail(userEmail);
        if (existingUser == null) {
            throw new UsernameNotFoundException("User Email " + userEmail + " not found");
        }
        return new UserPrincipal(existingUser);
    }
}
