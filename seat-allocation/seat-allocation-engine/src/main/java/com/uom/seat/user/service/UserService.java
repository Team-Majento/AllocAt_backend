package com.uom.seat.user.service;

import com.uom.seat.user.dto.UserRequest;
import com.uom.seat.user.dto.UserResponse;

public interface UserService {
    Integer createUser(UserRequest user);

    UserResponse getUser(Integer userId);

    public UserResponse updateUser(Integer userId, UserRequest userRequest);

}
