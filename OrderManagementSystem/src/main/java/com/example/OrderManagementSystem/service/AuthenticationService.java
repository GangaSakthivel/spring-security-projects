package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.config.JwtUtils;
import com.example.OrderManagementSystem.dto.AuthRequest;
import com.example.OrderManagementSystem.dto.AuthResponse;
import com.example.OrderManagementSystem.dto.UserRequestDTO;
import com.example.OrderManagementSystem.enums.Role;
import com.example.OrderManagementSystem.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.OrderManagementSystem.model.User; // Import your actual User entity

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthenticationService(PasswordEncoder passwordEncoder, JwtUtils jwtUtils, AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }


    public void register(UserRequestDTO request) {
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
            userRoles.add(Role.CUSTOMER);
        }

        User newUser = new User();
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setEmail(request.getEmail());
        newUser.setPhoneNumber(request.getPhoneNumber());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRoles(userRoles);

        userRepository.save(newUser);
    }


    public AuthResponse authenticate(AuthRequest authRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getPhoneNumber(),
                        authRequest.getPassword()
                )
        );

        User userEntity = userRepository.findByPhoneNumber(authRequest.getPhoneNumber())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid phone number or password."));

        AppUserDetails appUserDetails = new AppUserDetails(userEntity);

        String jwtToken = jwtUtils.generateToken(appUserDetails);

        return new AuthResponse(jwtToken);
    }
}
