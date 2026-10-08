package com.ecommerce.order.application;

import com.ecommerce.order.domain.Order;
import com.ecommerce.order.domain.OrderRepositoryPort;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service // <--- This annotation is crucial! It tells Spring this is a bean.
public class CreateOrderService implements CreateOrderUseCase {

    private final OrderRepositoryPort orderRepository;

    // Constructor Injection
    public CreateOrderService(OrderRepositoryPort orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order execute(CreateOrderRequest request) {
        // 1. Business Logic
        Order order = new Order();
        order.setUserId(request.userId());
        order.setProductId(request.productId());
        order.setQuantity(request.quantity());

        // Dummy pricing logic: $100 per item
        order.setTotalPrice(BigDecimal.valueOf(request.quantity() * 100));
        order.setStatus("PENDING");
        order.setOrderDate(LocalDateTime.now());

        // 2. Save via the Domain Port
        return orderRepository.save(order);
    }
}