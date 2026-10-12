package com.ecommerce.auth.application.dto;

public record AuthResponse(String message, String username, String accessToken, String refreshToken) {
}