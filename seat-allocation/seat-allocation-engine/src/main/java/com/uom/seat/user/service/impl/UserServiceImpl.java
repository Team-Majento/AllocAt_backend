package com.uom.seat.user.service.impl;


import com.uom.seat.user.dto.UserRequest;
import com.uom.seat.user.dto.UserResponse;
import com.uom.seat.user.entity.UserEntity;
import com.uom.seat.user.repository.UserRepository;
import com.uom.seat.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Integer createUser(UserRequest user) {
        return  userRepository.save(convertToUserEntity(user)).getId();
    }

    @Override
    public UserResponse getUser(Integer userId) {
        return convertToUserResponse(userRepository.findById(userId).get());
    }

    private UserResponse convertToUserResponse(UserEntity userEntity) {
        UserResponse dto = null;
        dto = modelMapper.map(userEntity, UserResponse.class);

        return dto;

    }

    private UserEntity convertToUserEntity(UserRequest user) {
        UserEntity entity=null;
        entity = modelMapper.map(user, UserEntity.class);
        entity.setXid(UUID.randomUUID().toString());

        return entity;


    }

    @Override
    public UserResponse updateUser(Integer userId, UserRequest userRequest) {
        UserEntity entity = userRepository.saveAndFlush(updateUserEntity(userRepository.findByUserId(userId), userRequest));
        return convertToUserResponse(entity);
    }

    private UserEntity updateUserEntity(UserEntity entity, UserRequest userRequest) {

        entity.setUserId(entity.getUserId());
        entity.setFirstName(userRequest.getFirstName());
        entity.setMiddleName(userRequest.getMiddleName());
        entity.setLastName(userRequest.getLastName());
        entity.setUserName(userRequest.getUserName());
        entity.setPassword(userRequest.getPassword());
        entity.setEmail(userRequest.getEmail());
        entity.setContactNo(userRequest.getContactNo());
        entity.setAddress(userRequest.getAddress());
        entity.setGender(userRequest.getGender());
        entity.setImageURL(userRequest.getImageURL());
        entity.setUserType(userRequest.getUserType());
        entity.setManagersEID(userRequest.getManagersEID());

        return entity;
    }
}
