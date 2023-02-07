package com.uom.seat.resourceAllocation.service;

import com.uom.seat.resourceAllocation.dto.ResourceAllocationRequest;
import com.uom.seat.resourceAllocation.dto.ResourceAllocationResponse;

import java.util.List;

public interface ResourceAllocationService {
    Integer createResourceAllocation(ResourceAllocationRequest resourceAllocation);
    List<ResourceAllocationResponse> getALlResourceAllocations();
    ResourceAllocationResponse getResourceAllocationByRequesterUserId(Integer requesterUserId);
}
