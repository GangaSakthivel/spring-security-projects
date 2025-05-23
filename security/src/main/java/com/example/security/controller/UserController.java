package com.example.security.controller;

import com.example.security.config.JWTUtil;
import com.example.security.dto.BaseResponseDTO;
import com.example.security.dto.UserRequestDTO;
import com.example.security.service.UserService;
import com.example.security.utils.ResponseMessages;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {


    private final JWTUtil jwtUtil;
    private final UserService userService;



    @Value("${role.admin}")
    private String roleAdmin;

    @Value("${role.user")
    private String roleUser;

    public UserController(JWTUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }


    @PostMapping(value = "/add-user", consumes = {"multipart/form-data"})
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BaseResponseDTO<Void>> registerUser(@Valid @ModelAttribute UserRequestDTO request) {
        try {
            userService.registerUser(request);
            return ResponseEntity.ok(new BaseResponseDTO<>(true, ResponseMessages.SUCCESS, null));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponseDTO<>(false, e.getMessage(), null));
        }
    }




}
