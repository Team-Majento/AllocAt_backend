package com.uom.seat.api;


import com.uom.seat.user.dto.UserRequest;
import com.uom.seat.user.dto.UserResponse;

public interface UserApi {
    Integer createCompany(String bearerToken, UserRequest user);

    UserResponse getUser(String bearerToken, Integer userId);

    public UserResponse updateUser(Integer userId, UserRequest userRequest);
}
