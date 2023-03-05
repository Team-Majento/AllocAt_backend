package com.uom.seat.user.repository;


import com.uom.seat.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
public interface UserRepository  extends JpaRepository<UserEntity,Integer> {

    public UserEntity findByXid(String xid);

    public UserEntity findByEmail(String email);

    public UserEntity findByUserId(Integer userId);

    public UserEntity findByUserName(String userName);
}
