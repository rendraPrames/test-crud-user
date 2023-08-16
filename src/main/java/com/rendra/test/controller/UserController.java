package com.rendra.test.controller;

import com.rendra.test.dto.SuccessResponse;
import com.rendra.test.entity.User;
import com.rendra.test.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<User>>> getAllUsers() {
        List<User> users = userService.getAllUsers().getData();
        return ResponseEntity.ok(new SuccessResponse<>(200, "Users fetched successfully", users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<User>> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id).getData();
        return ResponseEntity.ok(new SuccessResponse<>(200, "User fetched successfully", user));
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<User>> createUser(@Valid @RequestBody User user) {
        User savedUser = userService.createUser(user).getData();
        return ResponseEntity.ok(new SuccessResponse<>(200, "User created successfully", savedUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<User>> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user).getData();
        return ResponseEntity.ok(new SuccessResponse<>(200, "User updated successfully", updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new SuccessResponse<>(200, "User deleted successfully", null));
    }
}