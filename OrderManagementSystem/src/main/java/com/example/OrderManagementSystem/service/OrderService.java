package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.dto.OrderCreateRequest;
import com.example.OrderManagementSystem.dto.OrderItemCreateDTO;
import com.example.OrderManagementSystem.model.Order;
import com.example.OrderManagementSystem.model.OrderItem;
import com.example.OrderManagementSystem.model.Product;
import com.example.OrderManagementSystem.model.User;
import com.example.OrderManagementSystem.repository.OrderRepository;
import com.example.OrderManagementSystem.repository.ProductRepository;
import com.example.OrderManagementSystem.repository.UserRepository;
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
    public Order createOrder(OrderCreateRequest request) {
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

        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
    }

    @Transactional
    public Order updateOrder(Long id, OrderCreateRequest request) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));

        existingOrder.setSaleOrderId(request.getSaleOrderId());
        existingOrder.setOrderDateTime(request.getOrderDateTime());
        existingOrder.setDeliveryDate(request.getDeliveryDate());
        existingOrder.setOrderStatus(request.getOrderStatus());

        // Optionally update user if needed (or keep unchanged)
        if (!existingOrder.getUser().getId().equals(request.getUserId())) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + request.getUserId()));
            existingOrder.setUser(user);
        }

        // Update order items: clear existing and add new
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

        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
        orderRepository.delete(order);
    }
}
