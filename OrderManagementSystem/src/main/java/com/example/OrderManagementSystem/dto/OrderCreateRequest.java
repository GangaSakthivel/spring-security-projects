package com.example.OrderManagementSystem.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class OrderCreateRequest {
    private String saleOrderId;
    private LocalDateTime orderDateTime;
    private LocalDate deliveryDate;
    private String orderStatus;
    private Long userId;
    private List<OrderItemCreateDTO> orderItems;

    public OrderCreateRequest() {
    }

    public OrderCreateRequest(String saleOrderId, LocalDateTime orderDateTime, LocalDate deliveryDate, String orderStatus, Long userId, List<OrderItemCreateDTO> orderItems) {
        this.saleOrderId = saleOrderId;
        this.orderDateTime = orderDateTime;
        this.deliveryDate = deliveryDate;
        this.orderStatus = orderStatus;
        this.userId = userId;
        this.orderItems = orderItems;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderItemCreateDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemCreateDTO> orderItems) {
        this.orderItems = orderItems;
    }
}
