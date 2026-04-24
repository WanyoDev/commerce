package com.wanyoike.authenticationservice.service;

import com.wanyoike.authenticationservice.dtos.UserDTO;
import com.wanyoike.authenticationservice.model.User;
import com.wanyoike.authenticationservice.model.UserPrincipal;
import com.wanyoike.authenticationservice.repository.UserRepository;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public UserDetailsServiceImpl(UserRepository userRepository,
                                  JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public String authenticateUser(UserDTO user) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authentication.getName());
            //safer as authenticated user is stored in the security context than using the raw input users.getEmail()
        }

        throw new RuntimeException("Authentication failed!");
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
