package com.uom.seat.resource.logic;


import com.uom.seat.resource.dto.ResourceRequest;
import com.uom.seat.resource.service.ResourceService;
import com.uom.seat.resource.validator.ResourceValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResourceCreationLogic {

    private static final Logger logger = Logger.getLogger(ResourceCreationLogic.class);

@Autowired
private ResourceValidator resourceValidator;

@Autowired
private ResourceService resourceService;

    public Integer createResource(String accessToken, ResourceRequest resource, Integer companyId) {

        // 1. validate company request
        // 2. create company

        resourceValidator.validateResource(resource);
        logger.info("The company is validated.");

        Integer id = resourceService.createResource(resource,companyId);
        logger.info("The organization is created.");

        return id;
    }



}
