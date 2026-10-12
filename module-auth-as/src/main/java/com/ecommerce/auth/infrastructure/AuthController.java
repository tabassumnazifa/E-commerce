package com.ecommerce.auth.infrastructure;

import com.ecommerce.auth.application.AuthService;
import com.ecommerce.auth.application.dto.AuthResponse; // <--- Added this import
import com.ecommerce.auth.application.dto.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        // Changed String to AuthResponse to match the Service
        AuthResponse result = authService.register(request);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}