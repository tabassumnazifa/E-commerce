package com.ecommerce.auth.domain;

import lombok.Getter;
import lombok.Setter;

// Pure Domain Model - No database or Spring annotations!
@Getter
@Setter
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;
    private String refreshToken;
}