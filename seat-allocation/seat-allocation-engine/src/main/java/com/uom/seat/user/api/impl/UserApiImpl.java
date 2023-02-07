package com.uom.seat.user.api.impl;

import com.uom.seat.api.UserApi;
import com.uom.seat.user.dto.UserRequest;
import com.uom.seat.user.dto.UserResponse;
import com.uom.seat.user.logic.UserCreationLogic;
import com.uom.seat.user.logic.UserRetrievalLogic;
import com.uom.seat.user.logic.UserUpdateLogic;
import org.springframework.beans.factory.annotation.Autowired;
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
}
