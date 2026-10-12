package com.ecommerce.order.domain.in;

import com.ecommerce.order.application.dto.CreateOrderRequest;
import com.ecommerce.order.application.dto.OrderResponse;
import java.util.Optional;

public interface OrderUseCase {
    OrderResponse createOrder(CreateOrderRequest request); // <--- Added this!
    Optional<OrderResponse> getOrderById(Long id);
}