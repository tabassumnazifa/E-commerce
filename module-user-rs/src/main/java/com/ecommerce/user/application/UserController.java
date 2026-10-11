package com.ecommerce.user.application;

import com.ecommerce.commons.pagination.PageRequest;
import com.ecommerce.commons.pagination.PageResult;
import com.ecommerce.user.application.dto.CreateUserRequest;
import com.ecommerce.user.application.dto.UserResponse;
import com.ecommerce.user.domain.UserFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Module", description = "User profile management APIs with search and pagination")
public class UserController {

    private final UserUseCase userUseCase;

    // Constructor Injection
    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @PostMapping
    @Operation(summary = "Create a new user profile")
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {
        UserResponse response = userUseCase.createUser(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user profile by ID")
    public ResponseEntity<UserResponse> getUserById(
            @Parameter(description = "User Profile ID") @PathVariable Long id) {
        Optional<UserResponse> response = userUseCase.getUserById(id);
        return response.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Search user profiles with filters and pagination")
    public ResponseEntity<PageResult<UserResponse>> searchUsers(
            @Parameter(description = "Username (partial match)")
            @RequestParam(value = "username", required = false) String username,

            @Parameter(description = "Email (partial match)")
            @RequestParam(value = "email", required = false) String email,

            @Parameter(description = "First Name (partial match)")
            @RequestParam(value = "firstName", required = false) String firstName,

            @Parameter(description = "Last Name (partial match)")
            @RequestParam(value = "lastName", required = false) String lastName,

            @Parameter(description = "Page number (0-indexed)")
            @RequestParam(value = "page", defaultValue = "0") int page,

            @Parameter(description = "Items per page")
            @RequestParam(value = "size", defaultValue = "10") int size) {

        // 1. Build the filter from query parameters
        UserFilter filter = new UserFilter(username, email, firstName, lastName);

        // 2. Build the page request using the shared common class
        PageRequest pageRequest = new PageRequest(page, size);

        // 3. Execute search
        PageResult<UserResponse> result = userUseCase.searchUsers(filter, pageRequest);

        return ResponseEntity.ok(result);
    }
}