package com.ecommerce.user.infrastructure;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

<<<<<<< HEAD
import java.time.LocalDate;

@Entity
@Table(name = "user_profiles")
=======
@Entity
@Table(name = "user_profiles") // Named differently to avoid conflict with the Auth module's 'users' table
>>>>>>> e90d4ee101b79c0c21cecb885549eae7ea9109cc
@Getter
@Setter
public class UserProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String email;
<<<<<<< HEAD
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
=======

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String address;
>>>>>>> e90d4ee101b79c0c21cecb885549eae7ea9109cc
}