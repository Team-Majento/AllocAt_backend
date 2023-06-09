package com.uom.seat.user.service.impl;

import com.uom.seat.Role.entity.RoleEntity;
import com.uom.seat.Role.repository.RoleRepository;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Integer createUser(UserRequest user) {
        user.setPassword(getEncodedPassword(user.getPassword()));
        UserEntity userEntity=convertToUserEntity(user);
        return  userRepository.save(userEntity).getId();
    }

    @Override
    public UserResponse getUser(Integer userId) {
        return convertToUserResponse(userRepository.findByUserId(userId));
    }

    public UserResponse convertToUserResponse(UserEntity userEntity) {
        UserResponse dto = null;
        dto = modelMapper.map(userEntity, UserResponse.class);

        return dto;

    }

    public UserEntity convertToUserEntity(UserRequest user) {
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
        entity.setPassword(getEncodedPassword(userRequest.getPassword()));
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

    public void initRolesAndUser() {

        if (userRepository.count() == 0) {
            UserEntity testUser1 = new UserEntity();

            testUser1.setFirstName("Zerk");
            testUser1.setXid("bdvjbdgjsbh");
            testUser1.setMiddleName("Yeates");
            testUser1.setLastName("Cléopatre");
            testUser1.setGender('M');
            testUser1.setImageURL("imageURL_zerk");
            testUser1.setManagersEID(1);
            testUser1.setPassword(getEncodedPassword("abcd"));
            testUser1.setUserId(1);
            testUser1.setUserType(1);
            testUser1.setUserName("denuwan");
            testUser1.setActiveStatus(true);
            testUser1.setAddress("938 Thackeray Circle");
            testUser1.setContactNo("+94 71 413 1233");
            testUser1.setEmail("denuwan@gmail.com");
            userRepository.save(testUser1);




            UserEntity testUser2 = new UserEntity();
            testUser2.setFirstName("Garret");
            testUser2.setXid("bdvjbdgjsbhksnks");
            testUser2.setMiddleName("Heakey");
            testUser2.setLastName("Magdalène");
            testUser2.setGender('f');
            testUser2.setImageURL("imageURL_garret");
            testUser2.setManagersEID(1);
            testUser2.setPassword(getEncodedPassword("xyz"));
            testUser2.setUserId(2);
            testUser2.setUserName("Ravindu");
            testUser2.setUserType(2);
            testUser2.setActiveStatus(true);
            testUser2.setAddress("231 Thackeray Circle");
            testUser2.setContactNo("+94 74 092 6083");
            testUser2.setEmail("ravindu@gmail.com");
            userRepository.save(testUser2);




            UserEntity testUser3 = new UserEntity();
            testUser3.setFirstName("Nishath");
            testUser3.setXid("bdvjbdgjsbhksnk");
            testUser3.setMiddleName("ahamed");
            testUser3.setLastName("Magdalène");
            testUser3.setGender('M');
            testUser3.setImageURL("imageURL_nishahth");
            testUser3.setManagersEID(1);
            testUser3.setPassword(getEncodedPassword("def"));
            testUser3.setUserId(3);
            testUser3.setUserName("Nishath");
            testUser3.setUserType(3);
            testUser3.setActiveStatus(true);
            testUser3.setAddress("231 Thackeray Circle");
            testUser3.setContactNo("+94 74 092 6044");
            testUser3.setEmail("nishath@gmail.com");
            userRepository.save(testUser3);

        }


    }

    @Override
    public Integer getAllUsersCount() {
        return Math.toIntExact(userRepository.count());

    }

    @Override
    public Integer getAllSubordinatesCount(Integer managerEid) {
        return userRepository.getAllSubordinatesCount(managerEid);
    }


    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
   }






}
