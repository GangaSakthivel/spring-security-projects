package com.example.OrderManagementSystem.dto;

import java.math.BigDecimal;


public class OrderItemCreateDTO {
    private Long productId;
    private BigDecimal quantityKg;

    public OrderItemCreateDTO(Long productId, BigDecimal quantityKg) {
        this.productId = productId;
        this.quantityKg = quantityKg;
    }


    public OrderItemCreateDTO() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getQuantityKg() {
        return quantityKg;
    }

    public void setQuantityKg(BigDecimal quantityKg) {
        this.quantityKg = quantityKg;
    }


}
