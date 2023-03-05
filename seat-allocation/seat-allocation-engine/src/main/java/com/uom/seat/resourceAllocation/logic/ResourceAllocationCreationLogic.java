package com.uom.seat.resourceAllocation.logic;


import com.uom.seat.resourceAllocation.dto.ResourceAllocationRequest;
import com.uom.seat.resourceAllocation.service.ResourceAllocationService;
import com.uom.seat.resourceAllocation.validator.ResourceAllocationValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResourceAllocationCreationLogic {

    private static final Logger logger = Logger.getLogger(ResourceAllocationCreationLogic.class);
    @Autowired
    private ResourceAllocationValidator resourceAllocationValidator;


    @Autowired
    private ResourceAllocationService resourceAllocationService;

    public Integer createResourceAllocation(String bearerToken, ResourceAllocationRequest resourceAllocation) {

        // 1. validate resourceAllocation request
        // 2. create resorceAllocation

        resourceAllocationValidator.validateResourceAllocation(resourceAllocation);
        logger.info("The resourceAllocation is validated.");

        Integer id = resourceAllocationService.createResourceAllocation(resourceAllocation);
        logger.info("The resourceAllocation is created.");

        return id;

    }

    public Integer createReleventResourceAllocation(String authorization, Integer bookingRequestID) {

        Integer id = resourceAllocationService.createReleventResourceAllocation(bookingRequestID);
        logger.info("The resourceAllocation is created.");

        return id;
    }


}
