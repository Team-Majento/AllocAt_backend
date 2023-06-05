package com.uom.seat.api;


import com.uom.seat.resourceAllocation.dto.ResourceAllocationRequest;
import com.uom.seat.resourceAllocation.dto.ResourceAllocationResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ResourceAllocationApi {
    Integer createResourceAllocation(String bearerToken, ResourceAllocationRequest  resourceAllocation);
    Integer createReleventResourceAllocation(String authorization, Integer bookingRequestID);
    Page<ResourceAllocationResponse> getAllResourceAllocations(String bearerToken, Integer page, Integer size);
    Page<ResourceAllocationResponse> getAllResourceAllocationsByRequestersId(String authorization, Integer page, Integer size, Integer requesterUserId);


    //////////////////////////////////
//    void sendEmail();
//
//    Integer sendNotificationEmails(String authorization, Integer userId, Integer resourceManagerId, Integer status);
//
//
//    Integer sendNotificationEmails(String authorization, Integer userId, Integer resourceManagerId, Integer status, Integer requiredDate, Integer startTime, Integer endTime);

    Integer sendNotificationEmails(String authorization, Integer userId, Integer resourceManagerId, Integer status, LocalDate requiredDate, LocalTime startTime, LocalTime endTime, Integer resourceId);
}
