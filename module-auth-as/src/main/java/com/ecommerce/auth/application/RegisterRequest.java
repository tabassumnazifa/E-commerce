package com.ecommerce.auth.application;

public record RegisterRequest(String username, String password, String email) {}