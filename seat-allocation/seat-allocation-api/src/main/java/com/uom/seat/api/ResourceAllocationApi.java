package com.uom.seat.api;


import com.uom.seat.resourceAllocation.dto.ResourceAllocationRequest;
import com.uom.seat.resourceAllocation.dto.ResourceAllocationResponse;

import java.util.List;

public interface ResourceAllocationApi {
    Integer createResourceAllocation(String bearerToken, ResourceAllocationRequest  resourceAllocation);

    List<ResourceAllocationResponse> getAllResourceAllocations(String bearerToken);

    ResourceAllocationResponse getResourceAllocationByRequesterUserId(String bearerToken,Integer requesterUserId);

}
