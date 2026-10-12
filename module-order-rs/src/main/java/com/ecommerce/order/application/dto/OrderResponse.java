package com.ecommerce.order.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderResponse(
        Long id,
        Long userId,
        Long productId,
        Integer quantity,
        BigDecimal totalPrice,
        String status,
        LocalDateTime orderDate
) {}