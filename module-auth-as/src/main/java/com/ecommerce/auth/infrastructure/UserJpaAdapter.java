package com.ecommerce.auth.infrastructure;

import com.ecommerce.auth.domain.User;
import com.ecommerce.auth.domain.UserRepositoryPort;
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
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setEmail(user.getEmail());
        entity.setRole(user.getRole());
        entity.setRefreshToken(user.getRefreshToken()); // <--- Added mapping

        UserEntity savedEntity = springDataUserRepository.save(entity);

        User savedUser = new User();
        savedUser.setId(savedEntity.getId());
        savedUser.setUsername(savedEntity.getUsername());
        savedUser.setPassword(savedEntity.getPassword());
        savedUser.setEmail(savedEntity.getEmail());
        savedUser.setRole(savedEntity.getRole());
        savedUser.setRefreshToken(savedEntity.getRefreshToken()); // <--- Added mapping

        return savedUser;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return springDataUserRepository.findByUsername(username).map(entity -> {
            User user = new User();
            user.setId(entity.getId());
            user.setUsername(entity.getUsername());
            user.setPassword(entity.getPassword());
            user.setEmail(entity.getEmail());
            user.setRole(entity.getRole());
            user.setRefreshToken(entity.getRefreshToken()); // <--- Added mapping
            return user;
        });
    }
}