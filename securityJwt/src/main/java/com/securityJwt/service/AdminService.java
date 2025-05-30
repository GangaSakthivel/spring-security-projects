package com.securityJwt.service;

import com.securityJwt.dto.RegisterRequest;
import com.securityJwt.model.Role;
import com.securityJwt.model.User;
import com.securityJwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public void updateUser(Integer id, RegisterRequest request) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();
            // Update user properties from the request
            userToUpdate.setFirstName(request.getFirstName());
            userToUpdate.setLastName(request.getLastName());
            userToUpdate.setEmail(request.getEmail());
            // Note: Be cautious about updating passwords here, might need a separate flow
            // Update roles if provided
            if (request.getRoles() != null) {
                // Logic to update roles (similar to addUser)
            }
            userRepository.save(userToUpdate);
        } else {
            throw new RuntimeException("User not found with ID: " + id);
        }
    }

    public void deleteUser(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found with ID: " + id);
        }
    }
}