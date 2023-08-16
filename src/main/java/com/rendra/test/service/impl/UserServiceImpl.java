package com.rendra.test.service.impl;

import com.rendra.test.dto.SuccessResponse;
import com.rendra.test.entity.User;
import com.rendra.test.exception.ResourceAlreadyExistsException;
import com.rendra.test.exception.ResourceNotFoundException;
import com.rendra.test.repository.UserRepository;
import com.rendra.test.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private static final String NOT_FOUND_MESSAGE = "User Not Found!";

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public SuccessResponse<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return new SuccessResponse<>(200, "Users fetched successfully", users);
    }

    @Override
    public SuccessResponse<User> getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_MESSAGE));
        return new SuccessResponse<>(200, "User fetched successfully", user);
    }

    @Override
    @Transactional
    public SuccessResponse<User> createUser(User user) {
        validateUser(user);
        validateUsernameNotExists(user.getUsername());

        User savedUser = userRepository.save(user);
        return new SuccessResponse<>(200, "User created successfully", savedUser);
    }

    @Override
    @Transactional
    public SuccessResponse<User> updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_MESSAGE));

        validateUser(user);

        if (!user.getUsername().equals(existingUser.getUsername())) {
            validateUsernameNotExists(user.getUsername());
        }

        updateUserFields(existingUser, user);

        User updatedUser = userRepository.save(existingUser);
        return new SuccessResponse<>(200, "User updated successfully", updatedUser);
    }


    @Override
    @Transactional
    public SuccessResponse<Void> deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_MESSAGE));

        userRepository.delete(user);
        return new SuccessResponse<>(200, "User deleted successfully", null);
    }

    private void validateUser(User user) {
        if (user == null || user.getFullname() == null || user.getUsername() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("User data cannot be null or have missing fields.");
        }
    }

    private void validateUsernameNotExists(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new ResourceAlreadyExistsException("Username already used");
        }
    }

    private void updateUserFields(User existingUser, User newUser) {
        existingUser.setFullname(newUser.getFullname());
        existingUser.setUsername(newUser.getUsername());
        existingUser.setPassword(newUser.getPassword());
        existingUser.setStatus(newUser.getStatus());
    }
}