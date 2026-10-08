package com.ecommerce.product.infrastructure;

import com.ecommerce.product.domain.*;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductJpaAdapter implements ProductRepositoryPort {

    private final SpringDataProductRepository springDataProductRepository;

    public ProductJpaAdapter(SpringDataProductRepository springDataProductRepository) {
        this.springDataProductRepository = springDataProductRepository;
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = toEntity(product);
        ProductEntity savedEntity = springDataProductRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return springDataProductRepository.findById(id).map(this::toDomain);
    }

    @Override
    public PageResult<Product> findAllWithFilter(ProductFilter filter, PageRequest pageRequest) {
        // 1. Convert pure PageRequest to Spring Data Pageable (use fully qualified name to avoid collision)
        org.springframework.data.domain.Pageable pageable =
                org.springframework.data.domain.PageRequest.of(pageRequest.page(), pageRequest.size());

        // 2. Convert pure ProductFilter to JPA Specification
        Specification<ProductEntity> spec = createSpecification(filter);

        // 3. Execute the query
        Page<ProductEntity> page = springDataProductRepository.findAll(spec, pageable);

        // 4. Convert Page<ProductEntity> to PageResult<Product>
        List<Product> products = page.getContent().stream()
                .map(this::toDomain)
                .toList();

        return new PageResult<>(
                products,
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber()
        );
    }

    // Helper: Convert Domain to Entity
    private ProductEntity toEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setCategory(product.getCategory());
        entity.setStockQuantity(product.getStockQuantity());
        return entity;
    }

    // Helper: Convert Entity to Domain
    private Product toDomain(ProductEntity entity) {
        Product product = new Product();
        product.setId(entity.getId());
        product.setName(entity.getName());
        product.setDescription(entity.getDescription());
        product.setPrice(entity.getPrice());
        product.setCategory(entity.getCategory());
        product.setStockQuantity(entity.getStockQuantity());
        return product;
    }

    // Helper: Create JPA Specification from pure ProductFilter
    private Specification<ProductEntity> createSpecification(ProductFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.name() != null && !filter.name().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + filter.name().toLowerCase() + "%"));
            }

            if (filter.category() != null && !filter.category().isEmpty()) {
                predicates.add(cb.equal(cb.lower(root.get("category")), filter.category().toLowerCase()));
            }

            if (filter.minPrice() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), filter.minPrice()));
            }

            if (filter.maxPrice() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), filter.maxPrice()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}