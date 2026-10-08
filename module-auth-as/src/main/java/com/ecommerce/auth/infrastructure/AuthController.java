package com.ecommerce.auth.infrastructure;

import com.ecommerce.auth.application.AuthResponse;
import com.ecommerce.auth.application.AuthUseCase;
import com.ecommerce.auth.application.LoginRequest;
import com.ecommerce.auth.application.RegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth Module", description = "Authentication and Registration APIs")
public class AuthController {

    private final AuthUseCase authUseCase;

    // Constructor Injection (Depends only on the Application Port)
    public AuthController(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authUseCase.register(request);
    }

    @PostMapping("/login")
    @Operation(summary = "Login an existing user")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authUseCase.login(request);
    }
}