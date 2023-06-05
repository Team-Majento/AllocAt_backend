package com.uom.seat.api;


import com.uom.seat.resourceAllocation.dto.ResourceAllocationRequest;
import com.uom.seat.resourceAllocation.dto.ResourceAllocationResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ResourceAllocationApi {
    Integer createResourceAllocation(String bearerToken, ResourceAllocationRequest  resourceAllocation);
    Integer createReleventResourceAllocation(String authorization, Integer bookingRequestID);
    Page<ResourceAllocationResponse> getAllResourceAllocations(String bearerToken, Integer page, Integer size);
    Page<ResourceAllocationResponse> getAllResourceAllocationsByRequestersId(String authorization, Integer page, Integer size, Integer requesterUserId);

    void sendEmail();
}
