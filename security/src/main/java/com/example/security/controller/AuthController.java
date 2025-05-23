package com.example.security.controller;

import com.example.security.config.JWTUtil;
import com.example.security.dto.BaseResponseDTO;
import com.example.security.dto.LoginRequestDTO;
import com.example.security.dto.UserRequestDTO;
import com.example.security.service.UserService;
import com.example.security.utils.ResponseMessages;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {


    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthController(JWTUtil jwtUtil, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }


    @PostMapping(value = "/register-user", consumes = {"multipart/form-data"})
    public ResponseEntity<BaseResponseDTO<Void>> registerUser(@Valid @ModelAttribute UserRequestDTO request) {
        userService.registerUser(request);
        return ResponseEntity.ok(new BaseResponseDTO<>(true, ResponseMessages.SUCCESS, null));
    }


    @PostMapping("/login")
    public ResponseEntity<BaseResponseDTO<String>> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getPhoneNumber(),
                            loginRequestDTO.getPassword()
                    )
            );
            String token = jwtUtil.generateToken(loginRequestDTO.getPhoneNumber());

            BaseResponseDTO<String> response = new BaseResponseDTO<>(
                    true,
                    ResponseMessages.SUCCESS,
                    token
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            BaseResponseDTO<String> errorResponse = new BaseResponseDTO<>(
                    false,
                    ResponseMessages.UNAUTHORIZED.getMessage(),
                    null
            );

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

}
