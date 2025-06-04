package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.dto.BaseResponseDTO;
import com.example.OrderManagementSystem.dto.OrderCreateRequest;
import com.example.OrderManagementSystem.dto.OrderResponseDTO;
import com.example.OrderManagementSystem.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order-controller")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<BaseResponseDTO<OrderResponseDTO>> createOrder(@RequestBody OrderCreateRequest request) {
        try {
            OrderResponseDTO createdOrder = orderService.createOrder(request);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new BaseResponseDTO<>(true, "Order created successfully", createdOrder));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponseDTO<>(false, e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public ResponseEntity<BaseResponseDTO<OrderResponseDTO>> getOrderById(@PathVariable Long id) {
        try {
            OrderResponseDTO order = orderService.getOrderById(id);
            return ResponseEntity.ok(new BaseResponseDTO<>(true, "Order retrieved successfully", order));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponseDTO<>(false, e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<BaseResponseDTO<OrderResponseDTO>> updateOrder(@PathVariable Long id, @RequestBody OrderCreateRequest request) {
        try {
            OrderResponseDTO updatedOrder = orderService.updateOrder(id, request);
            return ResponseEntity.ok(new BaseResponseDTO<>(true, "Order updated successfully", updatedOrder));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponseDTO<>(false, e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<BaseResponseDTO<String>> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.ok(new BaseResponseDTO<>(true, "Order deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponseDTO<>(false, e.getMessage(), null));
        }
    }
}
