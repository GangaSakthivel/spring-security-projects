package com.example.security.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;


public class UserRequestDTO {

    @NotBlank
    private String userName;
    private String email;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String password;
    private Set<String> roles;

    public UserRequestDTO() {
    }

    public UserRequestDTO(String userName, String email, String phoneNumber, String password, Set<String> roles) {
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}


