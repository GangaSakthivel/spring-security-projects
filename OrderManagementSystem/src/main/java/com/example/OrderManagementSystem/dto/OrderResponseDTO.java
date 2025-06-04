package com.example.OrderManagementSystem.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDTO {
    private Long orderId;
    private String saleOrderId;
    private LocalDateTime orderDateTime;
    private LocalDate deliveryDate;
    private String orderStatus;
    private UserResponseDTO user;
    private List<OrderItemResponseDTO> orderItems;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public OrderResponseDTO(Long orderId, String saleOrderId, LocalDateTime orderDateTime, LocalDate deliveryDate, String orderStatus, UserResponseDTO user, List<OrderItemResponseDTO> orderItems, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.orderId = orderId;
        this.saleOrderId = saleOrderId;
        this.orderDateTime = orderDateTime;
        this.deliveryDate = deliveryDate;
        this.orderStatus = orderStatus;
        this.user = user;
        this.orderItems = orderItems;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public OrderResponseDTO() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getSaleOrderId() {
        return saleOrderId;
    }

    public void setSaleOrderId(String saleOrderId) {
        this.saleOrderId = saleOrderId;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }

    public List<OrderItemResponseDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemResponseDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

