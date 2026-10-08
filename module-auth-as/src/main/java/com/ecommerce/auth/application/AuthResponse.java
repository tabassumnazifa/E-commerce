package com.ecommerce.auth.application;

public record AuthResponse(String message, String username, String accessToken, String refreshToken) {
}