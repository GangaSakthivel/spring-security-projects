package com.example.security.dto;

import com.example.security.model.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    @NotBlank
    private String userName;
    private String email;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String password;
    private Set<String> roles;

}
