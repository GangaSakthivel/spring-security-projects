package com.example.OrderManagementSystem.model;


import com.example.OrderManagementSystem.enums.BirdSize;
import com.example.OrderManagementSystem.enums.ProductName;
import jakarta.persistence.*;

import java.math.BigDecimal;


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

    public Product(Long productId, ProductName productName, BirdSize birdSize, BigDecimal quantity, Long count, BigDecimal averageWeight) {
        this.productId = productId;
        this.productName = productName;
        this.birdSize = birdSize;
        this.quantity = quantity;
        this.count = count;
        this.averageWeight = averageWeight;
    }

    public Product() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public ProductName getProductName() {
        return productName;
    }

    public void setProductName(ProductName productName) {
        this.productName = productName;
    }

    public BirdSize getBirdSize() {
        return birdSize;
    }

    public void setBirdSize(BirdSize birdSize) {
        this.birdSize = birdSize;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public BigDecimal getAverageWeight() {
        return averageWeight;
    }

    public void setAverageWeight(BigDecimal averageWeight) {
        this.averageWeight = averageWeight;
    }
}

