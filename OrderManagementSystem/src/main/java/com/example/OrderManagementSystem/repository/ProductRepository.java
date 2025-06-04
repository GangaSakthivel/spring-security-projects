package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
