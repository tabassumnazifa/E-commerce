package com.ecommerce.product.domain;

// Pure Pagination Request - Defines which page to fetch and how many items per page
public record PageRequest(int page, int size) {

    // Compact constructor for basic validation and default values
    public PageRequest {
        if (page < 0) page = 0;
        if (size <= 0) size = 10; // Default to 10 items per page if invalid
    }
}