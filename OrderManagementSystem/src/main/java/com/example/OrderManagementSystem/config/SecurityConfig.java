package com.example.OrderManagementSystem.config;

import com.example.OrderManagementSystem.repository.UserRepository;
import com.example.OrderManagementSystem.service.AppUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    public SecurityConfig(UserRepository userRepository, JwtUtils jwtUtils, CustomAccessDeniedHandler accessDeniedHandler) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthFilter) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/product-controller/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/product-controller/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/product-controller/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/product-controller/**").hasAnyRole("ADMIN", "CUSTOMER")

                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // Use the injected filter here
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) // <-- Use the injected filter
                .exceptionHandling(exception -> exception.accessDeniedHandler(accessDeniedHandler));

        return http.build();
    }

}