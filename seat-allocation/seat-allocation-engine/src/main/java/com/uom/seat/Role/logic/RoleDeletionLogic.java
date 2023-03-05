package com.uom.seat.Role.logic;


import com.uom.seat.resource.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleDeletionLogic {

    @Autowired
    private ResourceService resourceService;

    public Boolean deleteResource(String accessToken, Integer resourceId) {
        return  resourceService.deleteResource(resourceId);
    }
}
