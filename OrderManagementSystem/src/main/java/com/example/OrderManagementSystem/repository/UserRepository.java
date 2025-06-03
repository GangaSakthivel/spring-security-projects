package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
