package com.ecommerce.user.infrastructure;

import com.ecommerce.user.domain.*;
import com.ecommerce.commons.pagination.PageRequest;
import com.ecommerce.commons.pagination.PageResult;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserProfileJpaAdapter implements UserRepositoryPort {

    private final SpringDataUserProfileRepository springDataUserRepository;

    public UserProfileJpaAdapter(SpringDataUserProfileRepository springDataUserRepository) {
        this.springDataUserRepository = springDataUserRepository;
    }

    @Override
    public User save(User user) {
        UserProfileEntity entity = toEntity(user);
        UserProfileEntity savedEntity = springDataUserRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<User> findById(Long id) {
        return springDataUserRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
<<<<<<< HEAD
=======
        // Using Specification to find by username without modifying the JPA interface
>>>>>>> e90d4ee101b79c0c21cecb885549eae7ea9109cc
        Specification<UserProfileEntity> spec = (root, query, cb) -> cb.equal(root.get("username"), username);
        return springDataUserRepository.findOne(spec).map(this::toDomain);
    }

    @Override
    public PageResult<User> findAllWithFilter(UserFilter filter, PageRequest pageRequest) {
<<<<<<< HEAD
        org.springframework.data.domain.Pageable pageable =
                org.springframework.data.domain.PageRequest.of(pageRequest.page(), pageRequest.size());

        Specification<UserProfileEntity> spec = createSpecification(filter);
        Page<UserProfileEntity> page = springDataUserRepository.findAll(spec, pageable);

=======
        // 1. Convert pure PageRequest to Spring Data Pageable (fully qualified to avoid collision)
        org.springframework.data.domain.Pageable pageable =
                org.springframework.data.domain.PageRequest.of(pageRequest.page(), pageRequest.size());

        // 2. Convert pure UserFilter to JPA Specification
        Specification<UserProfileEntity> spec = createSpecification(filter);

        // 3. Execute the query
        Page<UserProfileEntity> page = springDataUserRepository.findAll(spec, pageable);

        // 4. Convert Page<UserEntity> to common PageResult<User>
>>>>>>> e90d4ee101b79c0c21cecb885549eae7ea9109cc
        List<User> users = page.getContent().stream()
                .map(this::toDomain)
                .toList();

        return new PageResult<>(
                users,
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber()
        );
    }

    // Helper: Convert Domain to Entity
    private UserProfileEntity toEntity(User user) {
        UserProfileEntity entity = new UserProfileEntity();
        entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        entity.setEmail(user.getEmail());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setPhoneNumber(user.getPhoneNumber());
        entity.setAddress(user.getAddress());
<<<<<<< HEAD
        entity.setCreatedAt(user.getCreatedAt()); // <--- ADDED
=======
>>>>>>> e90d4ee101b79c0c21cecb885549eae7ea9109cc
        return entity;
    }

    // Helper: Convert Entity to Domain
    private User toDomain(UserProfileEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setEmail(entity.getEmail());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setPhoneNumber(entity.getPhoneNumber());
        user.setAddress(entity.getAddress());
<<<<<<< HEAD
        user.setCreatedAt(entity.getCreatedAt()); // <--- ADDED
=======
>>>>>>> e90d4ee101b79c0c21cecb885549eae7ea9109cc
        return user;
    }

    // Helper: Create JPA Specification from pure UserFilter
    private Specification<UserProfileEntity> createSpecification(UserFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.username() != null && !filter.username().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("username")), "%" + filter.username().toLowerCase() + "%"));
            }
<<<<<<< HEAD
            if (filter.email() != null && !filter.email().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("email")), "%" + filter.email().toLowerCase() + "%"));
            }
            if (filter.firstName() != null && !filter.firstName().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("firstName")), "%" + filter.firstName().toLowerCase() + "%"));
            }
=======

            if (filter.email() != null && !filter.email().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("email")), "%" + filter.email().toLowerCase() + "%"));
            }

            if (filter.firstName() != null && !filter.firstName().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("firstName")), "%" + filter.firstName().toLowerCase() + "%"));
            }

>>>>>>> e90d4ee101b79c0c21cecb885549eae7ea9109cc
            if (filter.lastName() != null && !filter.lastName().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("lastName")), "%" + filter.lastName().toLowerCase() + "%"));
            }

<<<<<<< HEAD
            // --- NEW DATE RANGE LOGIC ---
            if (filter.startDate() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("createdAt"), filter.startDate()));
            }
            if (filter.endDate() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("createdAt"), filter.endDate()));
            }

=======
>>>>>>> e90d4ee101b79c0c21cecb885549eae7ea9109cc
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}