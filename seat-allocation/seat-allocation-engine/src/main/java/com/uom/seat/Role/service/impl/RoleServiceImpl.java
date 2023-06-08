package com.uom.seat.Role.service.impl;

import com.uom.seat.Role.entity.RoleEntity;
import com.uom.seat.Role.repository.RoleRepository;
import com.uom.seat.Role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl  implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleEntity createNewRole(RoleEntity role) {
        return roleRepository.save(role);
    }
}
