package com.example.OrderManagementSystem.model;

import com.example.OrderManagementSystem.enums.ProductName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Enumerated(EnumType.STRING)
    private ProductName productName;

    private String birdSize;

    private BigDecimal quantity;

    private Long count;

    private BigDecimal averageWeight;

}
