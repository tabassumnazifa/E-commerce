package com.ecommerce.product.application;

import com.ecommerce.product.domain.*;
import com.ecommerce.commons.pagination.PageRequest;
import com.ecommerce.commons.pagination.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductUseCase {

    private final ProductRepositoryPort productRepository;

    // Constructor Injection
    public ProductService(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse createProduct(CreateProductRequest request) {
        // 1. Convert Request DTO to Domain
        Product product = new Product();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setCategory(request.category());
        product.setStockQuantity(request.stockQuantity());

        // 2. Save to database via Repository Port
        Product savedProduct = productRepository.save(product);

        // 3. Convert Domain to Response DTO
        return toResponse(savedProduct);
    }

    @Override
    public Optional<ProductResponse> getProductById(Long id) {
        return productRepository.findById(id).map(this::toResponse);
    }

    @Override
    public PageResult<ProductResponse> searchProducts(ProductFilter filter, PageRequest pageRequest) {
        // 1. Fetch paginated and filtered products from database
        PageResult<Product> productPage = productRepository.findAllWithFilter(filter, pageRequest);

        // 2. Convert each Product to ProductResponse
        List<ProductResponse> responseList = productPage.content().stream()
                .map(this::toResponse)
                .toList();

        // 3. Return new PageResult with ProductResponse objects
        return new PageResult<>(
                responseList,
                productPage.totalElements(),
                productPage.totalPages(),
                productPage.currentPage()
        );
    }

    // Helper: Convert Domain to Response DTO
    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory(),
                product.getStockQuantity()
        );
    }
}