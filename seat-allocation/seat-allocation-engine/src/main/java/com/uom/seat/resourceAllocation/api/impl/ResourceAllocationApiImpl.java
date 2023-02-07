package com.uom.seat.resourceAllocation.api.impl;

import com.uom.seat.api.ResourceAllocationApi;
import com.uom.seat.resourceAllocation.dto.ResourceAllocationRequest;
import com.uom.seat.resourceAllocation.dto.ResourceAllocationResponse;
import com.uom.seat.resourceAllocation.logic.GetAllResourceAllocationsLogic;
import com.uom.seat.resourceAllocation.logic.GetResourceAllocationByRequesterUserIdLogic;
import com.uom.seat.resourceAllocation.logic.ResourceAllocationCreationLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class ResourceAllocationApiImpl implements ResourceAllocationApi {

    @Autowired
    private ResourceAllocationCreationLogic resourceAllocationCreationLogic;


    @Autowired
    private GetAllResourceAllocationsLogic getAllResourceAllocationsLogic;

    @Autowired
    private GetResourceAllocationByRequesterUserIdLogic getResourceAllocationByRequesterUserIdLogic;

    @Override
    public Integer createResourceAllocation(String bearerToken, ResourceAllocationRequest resourceAllocation) {
        return resourceAllocationCreationLogic.createResourceAllocation(bearerToken,resourceAllocation);
    }


    @Override
    public List<ResourceAllocationResponse> getAllResourceAllocations(String bearerToken) {
        return getAllResourceAllocationsLogic.getAllResourceAllocations(bearerToken);
    }

    @Override
    public ResourceAllocationResponse getResourceAllocationByRequesterUserId(String bearerToken, Integer requesterUserId) {
        return getResourceAllocationByRequesterUserIdLogic.getResourceAllocationByRequesterUserId(bearerToken,requesterUserId);
    }

}
