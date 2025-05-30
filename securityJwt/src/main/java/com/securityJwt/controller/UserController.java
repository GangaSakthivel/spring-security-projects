package com.securityJwt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user-controller")
public class UserController {

    @GetMapping //any user with the valid token
    public String Hello(){
        return "Secured end point.";
    }

    //Access to this endpoint is restricted based on the role of the authenticated user.

    @GetMapping("user-data") //only user with the user name USER can access this end point
    @PreAuthorize("hasRole('USER')")
    public String adminAuthenticated(){
        return "someone with the user token can access this";
    }


}
