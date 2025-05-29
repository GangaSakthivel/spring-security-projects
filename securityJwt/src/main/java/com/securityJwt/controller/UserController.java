package com.securityJwt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user-controller")
public class UserController {

    @GetMapping
    public String Hello(){
        return "Hello this is secured end point.";
    }

    @GetMapping("admin-data")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAuthenticated(){
        return "someone with the Admin token can access this";
    }



}
