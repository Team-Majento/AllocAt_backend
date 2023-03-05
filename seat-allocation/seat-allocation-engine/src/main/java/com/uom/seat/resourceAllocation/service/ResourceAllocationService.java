package com.uom.seat.resourceAllocation.service;

import com.uom.seat.resourceAllocation.dto.ResourceAllocationRequest;
import com.uom.seat.resourceAllocation.dto.ResourceAllocationResponse;

import com.uom.seat.resourceAllocation.entity.ResourceAllocationEntity;

import org.springframework.data.domain.Page;

import java.util.List;

public interface ResourceAllocationService {
    Integer createResourceAllocation(ResourceAllocationRequest resourceAllocation);


    Integer createReleventResourceAllocation(Integer bookingRequestID);

    Page<ResourceAllocationResponse> getALlResourceAllocationsByRequstersUserId(Integer requesterUserId, Integer page, Integer size);
    Page<ResourceAllocationResponse> getALlResourceAllocations(Integer page,Integer size);


}
