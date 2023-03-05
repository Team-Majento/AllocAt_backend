package com.uom.seat.resourceAllocation.logic;

import com.uom.seat.resourceAllocation.dto.ResourceAllocationResponse;
import com.uom.seat.resourceAllocation.service.ResourceAllocationService;
import com.uom.seat.resourceAllocation.validator.ResourceAllocationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ResourceAllocationRetrievalLogic {

    @Autowired
    private ResourceAllocationValidator resourceAllocationValidator;


    @Autowired
    private ResourceAllocationService resourceAllocationService;

    public Page<ResourceAllocationResponse> getAllResourceAllocations(String bearerToken, Integer page, Integer size) {
        return resourceAllocationService.getALlResourceAllocations(page,size);
    }
    public Page<ResourceAllocationResponse> getResourceAllocationByRequesterUserId(Integer requesterUserId, Integer page, Integer size) {
        return resourceAllocationService.getALlResourceAllocationsByRequstersUserId(requesterUserId,page,size);
    }


}
