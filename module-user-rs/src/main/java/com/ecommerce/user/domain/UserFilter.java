package com.ecommerce.user.domain;

<<<<<<< HEAD
import java.time.LocalDate;

// Pure Search Criteria - Now includes Date Range filtering
=======
<<<<<<< HEAD
import java.time.LocalDate;

// Pure Search Criteria - Now includes Date Range filtering
=======
// Pure Search Criteria - Used to filter users without knowing about the database
>>>>>>> e90d4ee101b79c0c21cecb885549eae7ea9109cc
>>>>>>> 669c2b5519ada94729467790e6786f8cd9a73ece
public record UserFilter(
        String username,
        String email,
        String firstName,
<<<<<<< HEAD
        String lastName,
        LocalDate startDate, // <--- ADDED: Start of the date range
        LocalDate endDate    // <--- ADDED: End of the date range
=======
<<<<<<< HEAD
        String lastName,
        LocalDate startDate, // <--- ADDED: Start of the date range
        LocalDate endDate    // <--- ADDED: End of the date range
=======
        String lastName
>>>>>>> e90d4ee101b79c0c21cecb885549eae7ea9109cc
>>>>>>> 669c2b5519ada94729467790e6786f8cd9a73ece
) {}