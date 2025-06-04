package com.example.OrderManagementSystem.dto;

import com.example.OrderManagementSystem.enums.BirdSize;
import com.example.OrderManagementSystem.enums.ProductName;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequestDTO {

    @NotNull(message = "Product name is required")
    private ProductName productName;

    @NotNull(message = "Bird size is required")
    private BirdSize birdSize;

    @NotNull(message = "Quantity is required")
    private BigDecimal quantity;

    @NotNull(message = "Count is required")
    private Long count;

    public ProductRequestDTO() {
    }

    public ProductRequestDTO(ProductName productName, BirdSize birdSize, BigDecimal quantity, Long count) {
        this.productName = productName;
        this.birdSize = birdSize;
        this.quantity = quantity;
        this.count = count;
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
}
