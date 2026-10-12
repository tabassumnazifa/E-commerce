package com.ecommerce.auth.domain.in;

import com.ecommerce.auth.application.dto.AuthResponse; // <--- FIXED
import com.ecommerce.auth.application.dto.LoginRequest;
import com.ecommerce.auth.application.dto.RegisterRequest;

public interface AuthUseCase {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}