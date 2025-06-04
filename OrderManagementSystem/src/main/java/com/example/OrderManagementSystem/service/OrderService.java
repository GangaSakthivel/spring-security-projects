package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.dto.*;
import com.example.OrderManagementSystem.model.*;
import com.example.OrderManagementSystem.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public OrderResponseDTO createOrder(OrderCreateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + request.getUserId()));

        Order order = new Order();
        order.setSaleOrderId(request.getSaleOrderId());
        order.setOrderDateTime(request.getOrderDateTime());
        order.setDeliveryDate(request.getDeliveryDate());
        order.setOrderStatus(request.getOrderStatus());
        order.setUser(user);

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemCreateDTO itemDTO : request.getOrderItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + itemDTO.getProductId()));

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantityKg(itemDTO.getQuantityKg());

            orderItems.add(item);
        }

        order.setOrderItems(orderItems);

        Order savedOrder = orderRepository.save(order);
        return mapToOrderResponse(savedOrder);
    }

    public OrderResponseDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
        return mapToOrderResponse(order);
    }

    @Transactional
    public OrderResponseDTO updateOrder(Long id, OrderCreateRequest request) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));

        existingOrder.setSaleOrderId(request.getSaleOrderId());
        existingOrder.setOrderDateTime(request.getOrderDateTime());
        existingOrder.setDeliveryDate(request.getDeliveryDate());
        existingOrder.setOrderStatus(request.getOrderStatus());

        if (!existingOrder.getUser().getId().equals(request.getUserId())) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + request.getUserId()));
            existingOrder.setUser(user);
        }

        // Clear existing items and add new ones
        existingOrder.getOrderItems().clear();

        List<OrderItem> updatedItems = new ArrayList<>();
        for (OrderItemCreateDTO itemDTO : request.getOrderItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + itemDTO.getProductId()));

            OrderItem item = new OrderItem();
            item.setOrder(existingOrder);
            item.setProduct(product);
            item.setQuantityKg(itemDTO.getQuantityKg());

            updatedItems.add(item);
        }
        existingOrder.getOrderItems().addAll(updatedItems);

        Order savedOrder = orderRepository.save(existingOrder);
        return mapToOrderResponse(savedOrder);
    }

    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
        orderRepository.delete(order);
    }

    private OrderResponseDTO mapToOrderResponse(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();

        dto.setOrderId(order.getOrderId());
        dto.setSaleOrderId(order.getSaleOrderId());
        dto.setOrderDateTime(order.getOrderDateTime());
        dto.setDeliveryDate(order.getDeliveryDate());
        dto.setOrderStatus(order.getOrderStatus());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setUpdatedAt(order.getUpdatedAt());

        User user = order.getUser();
        UserResponseDTO userDto = new UserResponseDTO();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhoneNumber(user.getPhoneNumber());
        dto.setUser(userDto);

        List<OrderItemResponseDTO> itemsDto = order.getOrderItems().stream().map(item -> {
            OrderItemResponseDTO itemDto = new OrderItemResponseDTO();
            itemDto.setProductId(item.getProduct().getProductId());
            itemDto.setProductName(item.getProduct().getProductName());
            itemDto.setQuantityKg(item.getQuantityKg());
            return itemDto;
        }).toList();

        dto.setOrderItems(itemsDto);

        return dto;
    }
}
