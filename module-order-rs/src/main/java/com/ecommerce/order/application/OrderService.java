package com.ecommerce.order.application;

import com.ecommerce.order.application.dto.CreateOrderRequest;
import com.ecommerce.order.application.dto.OrderResponse;
import com.ecommerce.order.domain.Order;
import com.ecommerce.order.domain.in.OrderUseCase;
import com.ecommerce.order.domain.out.OrderRepositoryPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderService implements OrderUseCase {

    private final OrderRepositoryPort orderRepository;

    public OrderService(OrderRepositoryPort orderRepository) {
        this.orderRepository = orderRepository;
    }

    // 1. Create Order (Your existing logic, now returning a DTO)
    public OrderResponse createOrder(CreateOrderRequest request) {
        Order order = new Order();
        order.setUserId(request.userId());
        order.setProductId(request.productId());
        order.setQuantity(request.quantity());

        // Dummy pricing logic: $100 per item
        order.setTotalPrice(BigDecimal.valueOf(request.quantity() * 100));
        order.setStatus("PENDING");
        order.setOrderDate(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);
        return toResponse(savedOrder);
    }

    // 2. Get Order by ID (The new Read capability)
    @Override
    public Optional<OrderResponse> getOrderById(Long id) {
        return orderRepository.findById(id).map(this::toResponse);
    }

    // Helper: Convert Domain to Response DTO
    private OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getUserId(),
                order.getProductId(),
                order.getQuantity(),
                order.getTotalPrice(),
                order.getStatus(),
                order.getOrderDate()
        );
    }
}