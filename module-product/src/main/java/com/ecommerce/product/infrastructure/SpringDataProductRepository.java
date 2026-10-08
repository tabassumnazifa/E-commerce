package com.ecommerce.product.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

// Raw JPA Repository - Handles database operations with dynamic search support
public interface SpringDataProductRepository extends JpaRepository<ProductEntity, Long>,
        JpaSpecificationExecutor<ProductEntity> {
    // JpaRepository provides basic CRUD operations (save, findById, etc.)
    // JpaSpecificationExecutor enables dynamic filtering with Specifications
}