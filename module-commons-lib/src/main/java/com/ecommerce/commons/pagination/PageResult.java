package com.ecommerce.commons.pagination;

import java.util.List;

// Pure Pagination Result - Reusable across all modules
public record PageResult<T>(
        List<T> content,      // The actual list of items on this page
        long totalElements,   // Total number of items across all pages
        int totalPages,       // Total number of pages available
        int currentPage       // The current page number (0-indexed)
) {}