package com.ecommerce.user.application.dto;

import java.time.LocalDate;

// Outgoing DTO: Now includes the creation date
public record UserResponse(
        Long id,
        String username,
        String email,
        String firstName,
        String lastName,
        String phoneNumber,
        String address,
        LocalDate createdAt // <--- ADDED
) {}