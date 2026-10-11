package com.ecommerce.user.application;

import com.ecommerce.commons.pagination.PageRequest;
import com.ecommerce.commons.pagination.PageResult;
import com.ecommerce.user.application.dto.CreateUserRequest;
import com.ecommerce.user.application.dto.UserResponse;
import com.ecommerce.user.domain.UserFilter;

import java.util.Optional;

// Application Use Case Port - Defines the business operations for User Profiles
public interface UserUseCase {
    UserResponse createUser(CreateUserRequest request);
    Optional<UserResponse> getUserById(Long id);
    PageResult<UserResponse> searchUsers(UserFilter filter, PageRequest pageRequest);
}