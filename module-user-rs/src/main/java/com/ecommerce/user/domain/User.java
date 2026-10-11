package com.ecommerce.user.domain;

import lombok.Getter;
import lombok.Setter;

// Pure Domain Model - Represents a User Profile (No database or Spring annotations!)
@Getter
@Setter
public class User {
    private Long id;
    private String username;      // Links to the Auth module's user
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
}