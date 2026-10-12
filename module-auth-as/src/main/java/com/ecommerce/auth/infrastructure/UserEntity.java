package com.ecommerce.auth.infrastructure;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users") // Maps to the 'users' table in PostgreSQL
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password; // Stores the hashed password

    @Column(unique = true, nullable = false)
    private String email;

    private String role;

    private String refreshToken;
}