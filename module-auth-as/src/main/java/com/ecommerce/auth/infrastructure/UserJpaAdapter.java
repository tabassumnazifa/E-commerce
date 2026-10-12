package com.ecommerce.auth.infrastructure;

import com.ecommerce.auth.domain.User;
import com.ecommerce.auth.domain.out.UserRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserJpaAdapter implements UserRepositoryPort {

    private final SpringDataUserRepository springDataUserRepository;

    public UserJpaAdapter(SpringDataUserRepository springDataUserRepository) {
        this.springDataUserRepository = springDataUserRepository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = toEntity(user);
        UserEntity savedEntity = springDataUserRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return springDataUserRepository.findByUsername(username)
                .map(this::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return springDataUserRepository.findByEmail(email)
                .map(this::toDomain);
    }

    // Helper: Domain -> Entity
    private UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setEmail(user.getEmail());
        entity.setRole(user.getRole());
        entity.setRefreshToken(user.getRefreshToken());
        return entity;
    }

    // Helper: Entity -> Domain
    private User toDomain(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setPassword(entity.getPassword());
        user.setEmail(entity.getEmail());
        user.setRole(entity.getRole());
        user.setRefreshToken(entity.getRefreshToken());
        return user;
    }
}