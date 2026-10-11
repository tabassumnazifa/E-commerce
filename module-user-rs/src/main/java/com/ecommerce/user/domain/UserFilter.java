package com.ecommerce.user.domain;

// Pure Search Criteria - Used to filter users without knowing about the database
public record UserFilter(
        String username,
        String email,
        String firstName,
        String lastName
) {}