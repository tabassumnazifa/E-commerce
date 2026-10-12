package com.ecommerce.user.application;

import com.ecommerce.commons.pagination.PageRequest;
import com.ecommerce.commons.pagination.PageResult;
import com.ecommerce.user.application.dto.CreateUserRequest;
import com.ecommerce.user.application.dto.UserResponse;
import com.ecommerce.user.domain.User;
import com.ecommerce.user.domain.UserFilter;
import com.ecommerce.user.domain.UserRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserUseCase {

    private final UserRepositoryPort userRepository;

    public UserService(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setPhoneNumber(request.phoneNumber());
        user.setAddress(request.address());
        // Note: We don't set createdAt here. The Infrastructure layer (@PrePersist) handles it automatically!

        User savedUser = userRepository.save(user);
        return toResponse(savedUser);
    }

    @Override
    public Optional<UserResponse> getUserById(Long id) {
        return userRepository.findById(id).map(this::toResponse);
    }

    @Override
    public PageResult<UserResponse> searchUsers(UserFilter filter, PageRequest pageRequest) {
        PageResult<User> userPage = userRepository.findAllWithFilter(filter, pageRequest);

        List<UserResponse> responseList = userPage.content().stream()
                .map(this::toResponse)
                .toList();

        return new PageResult<>(
                responseList,
                userPage.totalElements(),
                userPage.totalPages(),
                userPage.currentPage()
        );
    }

    // Helper: Convert Domain to Response DTO
    private UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getCreatedAt() // <--- ADDED
        );
    }
}