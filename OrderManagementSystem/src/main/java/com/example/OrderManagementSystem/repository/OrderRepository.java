package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
