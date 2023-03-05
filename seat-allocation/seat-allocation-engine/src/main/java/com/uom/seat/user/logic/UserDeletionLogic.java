package com.uom.seat.user.logic;


import com.uom.seat.resource.service.ResourceService;
import com.uom.seat.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDeletionLogic {

    @Autowired
    private UserService userService;

    public Boolean deleteUser(String accessToken, Integer userId) {
        return  userService.deleteUser(userId);
    }
}
