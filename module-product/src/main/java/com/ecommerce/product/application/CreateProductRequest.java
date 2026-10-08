package com.ecommerce.product.application;

import java.math.BigDecimal;

// Request DTO for creating a new product
public record CreateProductRequest(
        String name,
        String description,
        BigDecimal price,
        String category,
        Integer stockQuantity
) {}