package com.uom.seat.Role.logic;


import com.uom.seat.resource.dto.ResourceRequest;
import com.uom.seat.resource.dto.ResourceResponse;
import com.uom.seat.resource.service.ResourceService;
import com.uom.seat.resource.validator.ResourceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleUpdateLogic {


    @Autowired
    private ResourceService service;


    @Autowired
    private ResourceValidator resourceValidator;

    public ResourceResponse updateResource(Integer resourceId, ResourceRequest resource) {
        // 1. find resource by id
        // 2. validate resource request
        // 3. update resource


        resourceValidator.validateResource(resource);

        return service.updateResource(resourceId,resource);
    }
}
