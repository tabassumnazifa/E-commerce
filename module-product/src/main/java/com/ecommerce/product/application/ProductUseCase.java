package com.ecommerce.product.application;

import com.ecommerce.commons.pagination.PageRequest;
import com.ecommerce.commons.pagination.PageResult;
import com.ecommerce.product.domain.ProductFilter;

import java.util.Optional;

// Application Use Case Port - Defines the business operations for Products
public interface ProductUseCase {
    ProductResponse createProduct(CreateProductRequest request);
    Optional<ProductResponse> getProductById(Long id);
    PageResult<ProductResponse> searchProducts(ProductFilter filter, PageRequest pageRequest);
}