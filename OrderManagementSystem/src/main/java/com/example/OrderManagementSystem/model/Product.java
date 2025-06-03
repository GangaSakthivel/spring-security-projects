package com.example.OrderManagementSystem.model;


import com.example.OrderManagementSystem.enums.BirdSize;
import com.example.OrderManagementSystem.enums.ProductName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Enumerated(EnumType.STRING)
    private ProductName productName;

    @Enumerated(EnumType.STRING)
    private BirdSize birdSize;

    private BigDecimal quantity; //weight

    private Long count; //in numbers

    private BigDecimal averageWeight;


}

