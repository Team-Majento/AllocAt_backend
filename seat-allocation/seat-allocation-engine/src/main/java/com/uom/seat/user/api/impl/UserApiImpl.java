package com.uom.seat.user.api.impl;

import com.uom.seat.api.UserApi;
import com.uom.seat.user.dto.UserRequest;
import com.uom.seat.user.dto.UserResponse;
import com.uom.seat.user.logic.UserCreationLogic;
import com.uom.seat.user.logic.UserDeletionLogic;
import com.uom.seat.user.logic.UserRetrievalLogic;
import com.uom.seat.user.logic.UserUpdateLogic;
import com.uom.seat.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class UserApiImpl implements UserApi {

    @Autowired
    private UserCreationLogic userCreationLogic;


    @Autowired
    private UserRetrievalLogic userRetrievalLogic;

    @Autowired
    private UserUpdateLogic userUpdateLogic;

    @Autowired
    private UserDeletionLogic userDeletionLogic;

    @Autowired
    private UserService userService;



    @Override
    public Integer createCompany(String bearerToken, UserRequest user) {
        return userCreationLogic.createUser(bearerToken,user);
    }

    @Override
    public UserResponse getUser(String bearerToken, Integer userId) {
     return userRetrievalLogic.getUser(bearerToken,userId);
    }

    @Override
    public UserResponse updateUser(Integer userId, UserRequest userRequest) {
              return userUpdateLogic.updateUser(userId,userRequest);
    }

    @Override
    public Page<UserResponse> getAllUsers(String authorization, Integer page, Integer size) {
        return userRetrievalLogic.getAllUsers(page,size);
    }

    @Override
    public Boolean deleteUser(String authorization, Integer userId) {
        return userDeletionLogic.deleteUser(authorization,userId);
    }

    @Override
    public Boolean userLogin(String userName, String password) {
        return userRetrievalLogic.userLogin(userName,password);
    }

    @Override
    public UserResponse getUserByUserName(String authorization, String username) {
        return userRetrievalLogic.getUserByUserName(username);

    }

    @Override
    public Integer getAllUsersCount() {
        return userService.getAllUsersCount();
    }

    @Override
    public Integer getAllSubordinatesCount(Integer managerEid) {
        return userService.getAllSubordinatesCount(managerEid);
    }


}
