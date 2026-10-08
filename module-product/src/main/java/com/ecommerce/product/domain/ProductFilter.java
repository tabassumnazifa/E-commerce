package com.ecommerce.product.domain;

import java.math.BigDecimal;

// Pure Search Criteria - Used to filter products without knowing about the database
public record ProductFilter(
        String name,
        String category,
        BigDecimal minPrice,
        BigDecimal maxPrice
) {}