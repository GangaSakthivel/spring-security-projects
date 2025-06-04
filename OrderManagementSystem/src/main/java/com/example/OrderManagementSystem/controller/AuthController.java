package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.dto.AuthRequest;
import com.example.OrderManagementSystem.dto.AuthResponse;
import com.example.OrderManagementSystem.dto.BaseResponseDTO;
import com.example.OrderManagementSystem.dto.UserRequestDTO;
import com.example.OrderManagementSystem.service.AuthenticationService;
import com.example.OrderManagementSystem.utils.ResponseMessages;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<BaseResponseDTO<String>> register(@Valid @RequestBody UserRequestDTO request) {
        try {
            authenticationService.register(request);
            return ResponseEntity.ok(new BaseResponseDTO<>(true, ResponseMessages.SUCCESS, "User Registered."));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponseDTO<>(false, e.getMessage(), e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponseDTO<AuthResponse>> authenticate(@RequestBody AuthRequest authRequest) {
        try {
            AuthResponse response = authenticationService.authenticate(authRequest);
            return ResponseEntity.ok(new BaseResponseDTO<>(true, ResponseMessages.SUCCESS, response));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponseDTO<>(false, e.getMessage(), null));
        }
    }





}
