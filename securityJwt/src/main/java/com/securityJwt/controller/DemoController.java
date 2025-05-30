package com.securityJwt.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/shared")
public class DemoController {


    @GetMapping("/user-admin")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    //@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    //@Secured({"ROLE_USER", "ROLE_ADMIN"})
    public String authorities(){
        return "Both authorities can access";
    }

}
