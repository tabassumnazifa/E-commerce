package com.ecommerce.order.application;

// It MUST be a 'record' to automatically generate the userId(), productId(), and quantity() methods
public record CreateOrderRequest(Long userId, Long productId, Integer quantity) {
}