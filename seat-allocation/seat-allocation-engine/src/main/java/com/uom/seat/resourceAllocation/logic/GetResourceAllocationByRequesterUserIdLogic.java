package com.uom.seat.resourceAllocation.logic;

import com.uom.seat.resourceAllocation.dto.ResourceAllocationResponse;
import com.uom.seat.resourceAllocation.service.ResourceAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetResourceAllocationByRequesterUserIdLogic {

    @Autowired
    private ResourceAllocationService resourceAllocationService;

    public ResourceAllocationResponse getResourceAllocationByRequesterUserId(String bearerToken, Integer requesterUserId) {
        return resourceAllocationService.getResourceAllocationByRequesterUserId(requesterUserId);
    }
}
