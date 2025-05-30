package com.securityJwt.controller;

import com.securityJwt.dto.BaseResponseDTO;
import com.securityJwt.dto.RegisterRequest;
import com.securityJwt.model.User;
import com.securityJwt.service.AdminService;
import com.securityJwt.utils.ResponseMessages;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin-controller")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String getAccess(){
        return "Admin can access this end point.";
    }

    @PostMapping("/add-user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BaseResponseDTO<String>> addUser(@Valid @RequestBody RegisterRequest request) {
        try {
            adminService.addUser(request);
            return ResponseEntity.ok(new BaseResponseDTO<>(true, ResponseMessages.SUCCESS, "User registered successfully."));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponseDTO<>(false, e.getMessage(), null));
        }
    }

    @GetMapping("/all-users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BaseResponseDTO<List<User>>> getAllUsers() {
        try {
            List<User> users = adminService.getAllUsers();
            return ResponseEntity.ok(new BaseResponseDTO<>(true, ResponseMessages.SUCCESS, users));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponseDTO<>(false, e.getMessage(), null));
        }
    }

    @GetMapping("/user-{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BaseResponseDTO<User>> getUserById(@PathVariable Integer id) {
        try {
            Optional<User> user = adminService.getUserById(id);
            if (user.isPresent()) {
                return ResponseEntity.ok(new BaseResponseDTO<>(true, ResponseMessages.SUCCESS, user.get()));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new BaseResponseDTO<>(false, ResponseMessages.NOT_FOUND, null));
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponseDTO<>(false, e.getMessage(), null));
        }
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BaseResponseDTO<String>> updateUser(@PathVariable Integer id, @Valid @RequestBody RegisterRequest request) {
        try {
            adminService.updateUser(id, request);
            return ResponseEntity.ok(new BaseResponseDTO<>(true, ResponseMessages.SUCCESS, "User updated successfully."));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponseDTO<>(false, e.getMessage(), null));
        }
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BaseResponseDTO<String>> deleteUser(@PathVariable Integer id) {
        try {
            adminService.deleteUser(id);
            return ResponseEntity.ok(new BaseResponseDTO<>(true, ResponseMessages.SUCCESS, "User deleted successfully."));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponseDTO<>(false, e.getMessage(), null));
        }
    }

}
