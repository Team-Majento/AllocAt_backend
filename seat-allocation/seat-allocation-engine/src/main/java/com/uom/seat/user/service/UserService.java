package com.uom.seat.user.service;

import com.uom.seat.user.dto.UserRequest;
import com.uom.seat.user.dto.UserResponse;
import org.springframework.data.domain.Page;

public interface UserService {
    Integer createUser(UserRequest user);

    UserResponse getUser(Integer userId);

    public UserResponse updateUser(Integer userId, UserRequest userRequest);

    Page<UserResponse> getAllUsers(Integer page, Integer size);

    Boolean deleteUser(Integer userId);

    Boolean userLogin(String userName, String password);

    UserResponse getUserByUserName(String username);

    void initRolesAndUser();

    Integer getAllUsersCount();

    Integer getAllSubordinatesCount(Integer managerEid);

}
