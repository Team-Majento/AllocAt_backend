package com.uom.seat.Role.repository;

import com.uom.seat.Role.entity.RoleEntity;
import com.uom.seat.resourceAllocation.entity.ResourceAllocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoleRepository  extends JpaRepository<RoleEntity,String>{


    @Query("select u from RoleEntity u where u.roleName = ?1")
    RoleEntity findRoleByRoleName(String roleName);

}
