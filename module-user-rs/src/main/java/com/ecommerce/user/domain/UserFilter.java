package com.ecommerce.user.domain;

import java.time.LocalDate;

// Pure Search Criteria - Now includes Date Range filtering
public record UserFilter(
        String username,
        String email,
        String firstName,
        String lastName,
        LocalDate startDate, // <--- ADDED: Start of the date range
        LocalDate endDate    // <--- ADDED: End of the date range
) {}