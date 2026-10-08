package com.ecommerce.product.domain;

import com.ecommerce.commons.pagination.PageRequest;
import com.ecommerce.commons.pagination.PageResult;

import java.util.Optional;

// Repository Port - Defines how the Domain interacts with the database
public interface ProductRepositoryPort {
    Product save(Product product);
    Optional<Product> findById(Long id);
    PageResult<Product> findAllWithFilter(ProductFilter filter, PageRequest pageRequest);
}