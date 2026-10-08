package com.ecommerce.product.application;

import java.math.BigDecimal;

// Response DTO for returning product details to the client
public record ProductResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        String category,
        Integer stockQuantity
) {}