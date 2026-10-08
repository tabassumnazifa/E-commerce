package com.ecommerce.commons.pagination;

// Pure Pagination Request - Reusable across all modules
public record PageRequest(int page, int size) {

    // Compact constructor for basic validation and default values
    public PageRequest {
        if (page < 0) page = 0;
        if (size <= 0) size = 10; // Default to 10 items per page if invalid
    }
}