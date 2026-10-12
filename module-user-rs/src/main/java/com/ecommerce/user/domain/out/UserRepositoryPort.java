package com.ecommerce.user.domain.out;

import com.ecommerce.commons.pagination.PageRequest;
import com.ecommerce.commons.pagination.PageResult;
import com.ecommerce.user.domain.User;
import com.ecommerce.user.domain.UserFilter;

import java.util.Optional;

// Repository Port - Defines how the Domain interacts with the database
public interface UserRepositoryPort {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    PageResult<User> findAllWithFilter(UserFilter filter, PageRequest pageRequest);
}