package com.uom.seat.resourceAllocation.api.impl;

import com.uom.seat.api.ResourceAllocationApi;
import com.uom.seat.resourceAllocation.dto.ResourceAllocationRequest;
import com.uom.seat.resourceAllocation.dto.ResourceAllocationResponse;
import com.uom.seat.resourceAllocation.logic.ResourceAllocationCreationLogic;
import com.uom.seat.resourceAllocation.logic.ResourceAllocationRetrievalLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class ResourceAllocationApiImpl implements ResourceAllocationApi {

    @Autowired
    private ResourceAllocationCreationLogic resourceAllocationCreationLogic;

    @Autowired
    private ResourceAllocationRetrievalLogic resourceAllocationRetrievalLogic;

    @Override
    public Page<ResourceAllocationResponse> getAllResourceAllocations(String bearerToken, Integer page, Integer size) {
        return resourceAllocationRetrievalLogic.getAllResourceAllocations(bearerToken,page,size);
    }

    @Override
    public Page<ResourceAllocationResponse> getAllResourceAllocationsByRequestersId(String authorization, Integer page, Integer size, Integer requesterUserId) {
        return resourceAllocationRetrievalLogic.getResourceAllocationByRequesterUserId(requesterUserId,page,size);
    }


    @Override
    public Integer createResourceAllocation(String bearerToken, ResourceAllocationRequest resourceAllocation) {
        return resourceAllocationCreationLogic.createResourceAllocation(bearerToken,resourceAllocation);
    }



    @Override
    public Integer createReleventResourceAllocation(String authorization, Integer bookingRequestID) {
        return resourceAllocationCreationLogic.createReleventResourceAllocation(authorization,bookingRequestID);
    }

}
