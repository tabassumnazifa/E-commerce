package com.ecommerce.user.application.dto;

// Response DTO for returning user profile details to the client
public record UserResponse(
        Long id,
        String username,
        String email,
        String firstName,
        String lastName,
        String phoneNumber,
        String address
) {}