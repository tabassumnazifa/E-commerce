package com.ecommerce.order.application.dto;

public record CreateOrderRequest(
        Long userId,
        Long productId,
        Integer quantity
) {}