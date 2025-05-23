package com.example.security.controller;

import com.example.security.config.JWTUtil;
import com.example.security.dto.BaseResponseDTO;
import com.example.security.dto.UserRequestDTO;
import com.example.security.model.User;
import com.example.security.service.UserService;
import com.example.security.utils.ResponseMessages;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<BaseResponseDTO<Void>> addUser(@Valid @ModelAttribute UserRequestDTO request) {
        try {
            userService.addUser(request);
            return ResponseEntity.ok(new BaseResponseDTO<>(true, ResponseMessages.SUCCESS, null));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponseDTO<>(false, e.getMessage(), null));
        }
    }


    @GetMapping("/getAllUsers")
    @PreAuthorize("hasRole('ADMIN')")  // restrict access to admins only
    public ResponseEntity<BaseResponseDTO<List<User>>> getAllUser() {
        try {
            List<User> users = userService.getAllUsers();  // make sure you have this method in service
            return ResponseEntity.ok(new BaseResponseDTO<>(true, ResponseMessages.SUCCESS, users));
        } catch (Exception e) {
            // Log error (optional)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponseDTO<>(false, "Failed to fetch users", null));
        }
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BaseResponseDTO<User>> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(new BaseResponseDTO<>(true, ResponseMessages.SUCCESS, user));
    }

    @PutMapping(value = "/user/{id}", consumes = {"multipart/form-data"})
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BaseResponseDTO<Void>> updateUser(
            @PathVariable Long id,
            @Valid @ModelAttribute UserRequestDTO request) {
        userService.updateUser(id, request);
        return ResponseEntity.ok(new BaseResponseDTO<>(true, ResponseMessages.UPDATE_SUCCESS_MSG, null));
    }

    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BaseResponseDTO<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new BaseResponseDTO<>(true, ResponseMessages.SUCCESS, null));
    }


}
