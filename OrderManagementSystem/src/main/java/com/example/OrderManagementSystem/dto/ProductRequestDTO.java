package com.example.OrderManagementSystem.dto;

import com.example.OrderManagementSystem.enums.BirdSize;
import com.example.OrderManagementSystem.enums.ProductName;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    @NotNull(message = "Product name is required")
    private ProductName productName;

    @NotNull(message = "Bird size is required")
    private BirdSize birdSize;

    @NotNull(message = "Quantity is required")
    private BigDecimal quantity;

    @NotNull(message = "Count is required")
    private Long count;
}
