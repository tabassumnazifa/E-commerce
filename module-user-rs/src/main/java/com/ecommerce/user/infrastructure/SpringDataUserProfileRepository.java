package com.ecommerce.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

// Raw JPA Repository - Handles database operations with dynamic search support
public interface SpringDataUserProfileRepository extends JpaRepository<UserProfileEntity, Long>,
        JpaSpecificationExecutor<UserProfileEntity> {
    // JpaRepository provides basic CRUD operations (save, findById, etc.)
    // JpaSpecificationExecutor enables dynamic filtering with Specifications
}