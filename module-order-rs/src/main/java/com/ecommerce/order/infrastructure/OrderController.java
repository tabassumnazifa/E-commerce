package com.ecommerce.order.infrastructure;

import com.ecommerce.order.application.OrderService; // Make sure this matches your service name
import com.ecommerce.order.application.dto.CreateOrderRequest;
import com.ecommerce.order.application.dto.OrderResponse;
import com.ecommerce.order.domain.in.OrderUseCase; // <--- Make sure this matches the interface
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderUseCase orderUseCase; // <--- Changed from CreateOrderUseCase

    public OrderController(OrderUseCase orderUseCase) { // <--- Changed here too
        this.orderUseCase = orderUseCase;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        // Call the service method
        OrderResponse response = orderUseCase.createOrder(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}