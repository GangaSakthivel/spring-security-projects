package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
