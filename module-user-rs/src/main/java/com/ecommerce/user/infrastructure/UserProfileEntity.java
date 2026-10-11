package com.ecommerce.user.infrastructure;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_profiles") // Named differently to avoid conflict with the Auth module's 'users' table
@Getter
@Setter
public class UserProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String address;
}