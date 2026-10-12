package com.ecommerce.user.domain;

<<<<<<< HEAD
import java.time.LocalDate;

// Pure Search Criteria - Now includes Date Range filtering
=======
// Pure Search Criteria - Used to filter users without knowing about the database
>>>>>>> e90d4ee101b79c0c21cecb885549eae7ea9109cc
public record UserFilter(
        String username,
        String email,
        String firstName,
<<<<<<< HEAD
        String lastName,
        LocalDate startDate, // <--- ADDED: Start of the date range
        LocalDate endDate    // <--- ADDED: End of the date range
=======
        String lastName
>>>>>>> e90d4ee101b79c0c21cecb885549eae7ea9109cc
) {}