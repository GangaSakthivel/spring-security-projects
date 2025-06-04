package com.example.OrderManagementSystem.dto;


import com.example.OrderManagementSystem.enums.ProductName;

import java.math.BigDecimal;

public class OrderItemResponseDTO {
    private Long productId;
    private ProductName productName;
    private BigDecimal quantityKg;

    public OrderItemResponseDTO() {
    }

    public OrderItemResponseDTO(Long productId, ProductName productName, BigDecimal quantityKg) {
        this.productId = productId;
        this.productName = productName;
        this.quantityKg = quantityKg;
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

    public BigDecimal getQuantityKg() {
        return quantityKg;
    }

    public void setQuantityKg(BigDecimal quantityKg) {
        this.quantityKg = quantityKg;
    }
}
