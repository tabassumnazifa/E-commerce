package com.ecommerce.user.application.dto;

<<<<<<< HEAD
import java.time.LocalDate;

// Outgoing DTO: Now includes the creation date
=======
<<<<<<< HEAD
import java.time.LocalDate;

// Outgoing DTO: Now includes the creation date
=======
// Response DTO for returning user profile details to the client
>>>>>>> e90d4ee101b79c0c21cecb885549eae7ea9109cc
>>>>>>> 669c2b5519ada94729467790e6786f8cd9a73ece
public record UserResponse(
        Long id,
        String username,
        String email,
        String firstName,
        String lastName,
        String phoneNumber,
<<<<<<< HEAD
        String address,
        LocalDate createdAt // <--- ADDED
=======
<<<<<<< HEAD
        String address,
        LocalDate createdAt // <--- ADDED
=======
        String address
>>>>>>> e90d4ee101b79c0c21cecb885549eae7ea9109cc
>>>>>>> 669c2b5519ada94729467790e6786f8cd9a73ece
) {}