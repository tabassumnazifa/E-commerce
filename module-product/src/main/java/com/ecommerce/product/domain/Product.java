package com.ecommerce.product.domain;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

// Pure Domain Model - No database or Spring annotations!
@Getter
@Setter
public class Product {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private Integer stockQuantity;
}