package com.example.security.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name", nullable = false)
    private String userName;
    @Column(name = "user_email")
    private String email;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Role> roles = new HashSet<>(); //set does not allow duplicates

}
