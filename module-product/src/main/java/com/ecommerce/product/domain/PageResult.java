package com.ecommerce.product.domain;

import java.util.List;

// Pure Pagination Result - Holds the data and metadata for a paginated response
public record PageResult<T>(
        List<T> content,
        long totalElements,
        int totalPages,
        int currentPage
) {}