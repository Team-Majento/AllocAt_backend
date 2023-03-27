package com.uom.seat.api;


import com.uom.seat.user.dto.UserRequest;
import com.uom.seat.user.dto.UserResponse;
import org.springframework.data.domain.Page;

public interface UserApi {
    Integer createCompany(String bearerToken, UserRequest user);

    UserResponse getUser(String bearerToken, Integer userId);

    public UserResponse updateUser(Integer userId, UserRequest userRequest);
    Page<UserResponse> getAllUsers(String authorization, Integer page, Integer size);

    Boolean deleteUser(String authorization, Integer userId);

    Boolean userLogin(String userName, String password);

    UserResponse getUserByUserName(String authorization, String username);
}
