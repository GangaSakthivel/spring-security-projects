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
        return "Secured end point.";
    }

    @GetMapping("user-data")
    @PreAuthorize("hasRole('USER')")
    public String adminAuthenticated(){
        return "someone with the user token can access this";
    }





}
