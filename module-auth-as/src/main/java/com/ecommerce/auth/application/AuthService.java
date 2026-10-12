package com.ecommerce.auth.application;

import com.ecommerce.auth.application.dto.AuthResponse; // <--- FIXED
import com.ecommerce.auth.application.dto.LoginRequest;
import com.ecommerce.auth.application.dto.RegisterRequest;
import com.ecommerce.auth.domain.User;
import com.ecommerce.auth.domain.in.AuthUseCase;
import com.ecommerce.auth.domain.out.UserRepositoryPort;
import com.ecommerce.auth.infrastructure.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthUseCase {

    private final UserRepositoryPort userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepositoryPort userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByUsername(request.username()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User newUser = new User();
        newUser.setUsername(request.username());
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setRole("USER");

        User savedUser = userRepository.save(newUser);

        return new AuthResponse(
                "User registered successfully",
                savedUser.getUsername(),
                null,
                null
        );
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        return null;
    }
}