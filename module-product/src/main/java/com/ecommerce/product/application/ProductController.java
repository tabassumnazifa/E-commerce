package com.ecommerce.product.application;

import com.ecommerce.product.domain.PageRequest;
import com.ecommerce.product.domain.PageResult;
import com.ecommerce.product.domain.ProductFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product Module", description = "Product management APIs with search and pagination")
public class ProductController {

    private final ProductUseCase productUseCase;

    // Constructor Injection
    public ProductController(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    @PostMapping
    @Operation(summary = "Create a new product")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody CreateProductRequest request) {
        ProductResponse response = productUseCase.createProduct(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID")
    public ResponseEntity<ProductResponse> getProductById(
            @Parameter(description = "Product ID") @PathVariable Long id) {
        Optional<ProductResponse> response = productUseCase.getProductById(id);
        return response.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Search products with filters and pagination")
    public ResponseEntity<PageResult<ProductResponse>> searchProducts(
            @Parameter(description = "Product name (partial match)")
            @RequestParam(value = "name", required = false) String name,

            @Parameter(description = "Product category (exact match)")
            @RequestParam(value = "category", required = false) String category,

            @Parameter(description = "Minimum price")
            @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,

            @Parameter(description = "Maximum price")
            @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice,

            @Parameter(description = "Page number (0-indexed)")
            @RequestParam(value = "page", defaultValue = "0") int page,

            @Parameter(description = "Items per page")
            @RequestParam(value = "size", defaultValue = "10") int size) {

        // 1. Build the filter from query parameters
        ProductFilter filter = new ProductFilter(name, category, minPrice, maxPrice);

        // 2. Build the page request
        PageRequest pageRequest = new PageRequest(page, size);

        // 3. Execute search
        PageResult<ProductResponse> result = productUseCase.searchProducts(filter, pageRequest);

        return ResponseEntity.ok(result);
    }
}