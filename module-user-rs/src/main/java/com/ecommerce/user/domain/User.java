package com.ecommerce.user.domain;

import lombok.Getter;
import lombok.Setter;

<<<<<<< HEAD
import java.time.LocalDate;

=======
// Pure Domain Model - Represents a User Profile (No database or Spring annotations!)
>>>>>>> e90d4ee101b79c0c21cecb885549eae7ea9109cc
@Getter
@Setter
public class User {
    private Long id;
<<<<<<< HEAD
    private String username;
=======
    private String username;      // Links to the Auth module's user
>>>>>>> e90d4ee101b79c0c21cecb885549eae7ea9109cc
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
<<<<<<< HEAD
    private LocalDate createdAt; // <--- ADDED: To track when the profile was created
=======
>>>>>>> e90d4ee101b79c0c21cecb885549eae7ea9109cc
}