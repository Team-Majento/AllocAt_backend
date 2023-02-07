package com.uom.seat.user.logic;


import com.uom.seat.user.dto.UserRequest;
import com.uom.seat.user.dto.UserResponse;
import com.uom.seat.user.service.UserService;
import com.uom.seat.user.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserUpdateLogic {

    @Autowired
    private UserService service;

    @Autowired
    private UserValidator userValidator;



    public UserResponse updateUser(Integer userId, UserRequest userRequest) {
        // 1. find user by userId
        // 2. validate user request
        // 3. update user


        userValidator.validateUser(userRequest);

        return service.updateUser(userId, userRequest);
    }


}
