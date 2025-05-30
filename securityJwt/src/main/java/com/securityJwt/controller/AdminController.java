package com.securityJwt.controller;

import com.securityJwt.dto.AuthResponse;
import com.securityJwt.dto.RegisterRequest;
import com.securityJwt.service.AdminService;
import com.securityJwt.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<AuthResponse> addUser(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = adminService.addUser(request);
        return ResponseEntity.ok(response);
    }


}
