package com.securityJwt.service;

import com.securityJwt.dto.RegisterRequest;
import com.securityJwt.model.Role;
import com.securityJwt.model.User;
import com.securityJwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void addUser(RegisterRequest request) { // Return void instead of AuthResponse
        Set<Role> userRoles = new HashSet<>();
        if (request.getRoles() != null && !request.getRoles().isEmpty()) {
            for (String roleName : request.getRoles()) {
                try {
                    userRoles.add(Role.valueOf(roleName.toUpperCase()));
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid role specified: " + roleName);
                }
            }
        } else {
            userRoles.add(Role.ROLE_USER);
        }

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(userRoles)
                .build();

        userRepository.save(user);

    }
}