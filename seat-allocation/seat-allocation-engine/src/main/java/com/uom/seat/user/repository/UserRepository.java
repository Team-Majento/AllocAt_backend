package com.uom.seat.user.repository;


import com.uom.seat.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
public interface UserRepository  extends JpaRepository<UserEntity,Integer> {

    public UserEntity findByXid(String xid);

    public UserEntity findByEmail(String email);

    @Query("select u from UserEntity u where u.userId = ?1")
    public UserEntity findByUserId(Integer userId);

    @Query("select u from UserEntity u where u.userName = ?1")
    public UserEntity findByUserName(String userName);

    @Query("select u.email from UserEntity u where u.userId= ?1")
    String findEmailByUserId(Integer userId);
}
