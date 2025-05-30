package com.securityJwt.controller;

import com.securityJwt.dto.BaseResponseDTO;
import com.securityJwt.dto.RegisterRequest;
import com.securityJwt.service.AdminService;
import com.securityJwt.utils.ResponseMessages;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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


}
