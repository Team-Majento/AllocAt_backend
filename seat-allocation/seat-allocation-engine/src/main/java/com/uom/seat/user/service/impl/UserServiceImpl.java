package com.uom.seat.user.service.impl;


import com.uom.seat.resource.entity.ResourceEntity;
import com.uom.seat.user.dto.UserRequest;
import com.uom.seat.user.dto.UserResponse;
import com.uom.seat.user.entity.UserEntity;
import com.uom.seat.user.repository.UserRepository;
import com.uom.seat.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        return convertToUserResponse(userRepository.findByUserId(userId));
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
    @Override
    public Page<UserResponse> getAllUsers(Integer page, Integer size) {
        PageRequest pageable= PageRequest.of(page,size);
        Page<UserEntity> pageEntities=userRepository.findAll(pageable);

        List<UserEntity> entityList= pageEntities.getContent();
        List<UserResponse> dtoList = new ArrayList<>();

        entityList.forEach(entity -> dtoList.add(convertToUserResponse(entity)));

        return new PageImpl<UserResponse>(dtoList,pageable,pageEntities.getTotalElements());
    }


    @Override
    public Boolean deleteUser(Integer userId) {
        UserEntity entity = userRepository.saveAndFlush(deleteUser(userRepository.findById(userId).get()));
        return  !entity.getActiveStatus();
    }

    @Override
    public Boolean userLogin(String userName, String password) {
        UserEntity entity = userRepository.findByUserName(userName);
        if(entity==null){return false;}
        else if(password.equals(entity.getPassword())){
            return true;
        }
        else return false;
    }

    private UserEntity deleteUser(UserEntity userEntity) {
        userEntity.setActiveStatus(false);
        return  userEntity;
    }

    @Override
    public UserResponse getUserByUserName(String username) {
        return convertToUserResponse(userRepository.findByUserName(username));
    }
}
