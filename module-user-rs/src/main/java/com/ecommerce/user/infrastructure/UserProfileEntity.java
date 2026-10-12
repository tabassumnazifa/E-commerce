package com.ecommerce.user.infrastructure;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "user_profiles")
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

    @Column(name = "created_at")
    private LocalDate createdAt;

    // Automatically sets the date to today when the entity is first saved to the database
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
    }
}