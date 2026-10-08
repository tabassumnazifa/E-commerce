package com.ecommerce.auth.application;

import com.ecommerce.auth.domain.User;
import com.ecommerce.auth.domain.UserRepositoryPort;
import com.ecommerce.auth.infrastructure.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthUseCase {

    private final UserRepositoryPort userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // Constructor Injection (Added JwtService)
    public AuthService(UserRepositoryPort userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setEmail(request.email());
        user.setRole("USER");

        // 1. Save user to get the generated ID
        User savedUser = userRepository.save(user);

        // 2. Generate Access and Refresh tokens
        String accessToken = jwtService.generateAccessToken(savedUser);
        String refreshToken = jwtService.generateRefreshToken(savedUser);

        // 3. Save the refresh token in the database for future validation
        savedUser.setRefreshToken(refreshToken);
        userRepository.save(savedUser);

        return new AuthResponse("User registered successfully", savedUser.getUsername(), accessToken, refreshToken);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // 1. Generate new Access and Refresh tokens
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        // 2. Update the refresh token in the database
        user.setRefreshToken(refreshToken);
        userRepository.save(user);

        return new AuthResponse("Login successful", user.getUsername(), accessToken, refreshToken);
    }
}