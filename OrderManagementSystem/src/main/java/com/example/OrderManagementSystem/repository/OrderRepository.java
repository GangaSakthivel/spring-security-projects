package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findBySaleOrderId(String saleOrderId);
    boolean existsBySaleOrderId(String saleOrderId);
}
