package com.ecommerce.user.application.dto;

// Request DTO for creating or updating a user profile
public record CreateUserRequest(
        String username,
        String email,
        String firstName,
        String lastName,
        String phoneNumber,
        String address
) {}