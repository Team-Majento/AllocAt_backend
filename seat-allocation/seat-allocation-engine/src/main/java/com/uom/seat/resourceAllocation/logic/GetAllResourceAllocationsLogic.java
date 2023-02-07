package com.uom.seat.resourceAllocation.logic;

import com.uom.seat.resourceAllocation.dto.ResourceAllocationResponse;
import com.uom.seat.resourceAllocation.service.ResourceAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllResourceAllocationsLogic {
    @Autowired
    private ResourceAllocationService resourceAllocationService;

    public List<ResourceAllocationResponse> getAllResourceAllocations(String bearerToken) {
        return resourceAllocationService.getALlResourceAllocations();
    }
}
