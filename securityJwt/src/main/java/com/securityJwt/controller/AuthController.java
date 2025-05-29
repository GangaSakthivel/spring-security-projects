package com.securityJwt.controller;

import com.securityJwt.dto.AuthRequest;
import com.securityJwt.dto.AuthResponse;
import com.securityJwt.dto.RegisterRequest;
import com.securityJwt.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
//@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        AuthResponse response = authenticationService.register(request);
        return ResponseEntity.ok(response);
    }

//
//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest){
//        authenticationService.register(registerRequest); // Assuming this returns void or throws an exception on failure
//        return ResponseEntity.ok("Registration successful");
//    }


    @PostMapping("/authenticate") // Corrected endpoint path
    public ResponseEntity<String> register(@RequestBody AuthRequest authRequest){ // Method name remains 'register'
        authenticationService.authenticate(authRequest);
        return ResponseEntity.ok("Registration successful"); // Corrected return type and value
    }


}
