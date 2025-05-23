package com.example.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.security.model.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
