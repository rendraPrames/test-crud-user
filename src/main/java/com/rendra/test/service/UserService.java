package com.rendra.test.service;

import com.rendra.test.dto.SuccessResponse;
import com.rendra.test.entity.User;
import jakarta.transaction.Transactional;

import java.util.List;

public interface UserService {
    SuccessResponse<List<User>> getAllUsers();

    SuccessResponse<User> getUserById(Long id);

    @Transactional
    SuccessResponse<User> createUser(User user);

    @Transactional
    SuccessResponse<User> updateUser(Long id, User user);

    @Transactional
    SuccessResponse<Void> deleteUser(Long id);
}
