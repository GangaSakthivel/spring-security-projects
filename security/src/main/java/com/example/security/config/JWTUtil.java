package com.example.security.config;

import com.example.security.model.Role;
import com.example.security.model.User;
import com.example.security.repository.UserRepository;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component

public class JWTUtil {

    private final UserRepository userRepository;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;


    public JWTUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateToken(String phoneNumber) {
        Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);
        Set<Role> roles = user.get().getRoles();

        // add roles to the token
        return Jwts.builder()
                .setSubject(phoneNumber)
                .claim("roles", roles.stream()
                        .map(role -> role.getName())
                        .collect(Collectors.joining(",")))
                .setIssuedAt(new Date(new Date().getTime() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String extractUserName(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJwt(token)
                .getBody()
                .getSubject(); //username
    }

    public Set<String> extractRoles(String token){
        String rolesString = Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJwt(token)
                .getBody()
                .get("roles", String.class);

        return Set.of(rolesString);
    }

    public boolean tokenValidation(String token){
        try{
            Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJwt(token);
            return true;
        }catch (JwtException | IllegalArgumentException e){
            return false;
        }

    }

}
