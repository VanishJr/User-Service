package com.user_service.service.impl;

import com.user_service.dto.UserRequest;
import com.user_service.model.User;

public interface UserService {
    User createUser(UserRequest userRequest);

    User getUserById(Long userId);

    User updateUser(Long userId, UserRequest userRequest);

    void deleteUser(Long userId);
}
