package com.uom.seat.server.rest.controller;


import com.uom.seat.Role.entity.RoleEntity;
import com.uom.seat.Role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.log4j.Logger;


@RestController
//@RequestMapping("/roles")
public class RoleController {

    private static final Logger logger=Logger.getLogger(RoleController.class);
    @Autowired
    private RoleService roleService;

    @PostMapping("/createNewRole")
    public RoleEntity createNewRole(@RequestBody RoleEntity role){
         return  roleService.createNewRole(role);
    }




}
