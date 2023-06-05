package com.uom.seat.resourceAllocation.service;

import com.uom.seat.resourceAllocation.dto.ResourceAllocationRequest;
import com.uom.seat.resourceAllocation.dto.ResourceAllocationResponse;

import com.uom.seat.resourceAllocation.entity.ResourceAllocationEntity;

import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ResourceAllocationService {
    Integer createResourceAllocation(ResourceAllocationRequest resourceAllocation);


    Integer createReleventResourceAllocation(Integer bookingRequestID);

    Page<ResourceAllocationResponse> getALlResourceAllocationsByRequstersUserId(Integer requesterUserId, Integer page, Integer size);
    Page<ResourceAllocationResponse> getALlResourceAllocations(Integer page,Integer size);

//    void sendEmail();


//    Integer sendNotificationEmail(Integer userId, Integer resourceManagerId, Integer status);
//
//    Integer sendNotificationEmail(Integer userId, Integer resourceManagerId, Integer status, Integer requiredDate, Integer startTime, Integer endTime);

    Integer sendNotificationEmail(Integer userId, Integer resourceManagerId, Integer status, LocalDate requiredDate, LocalTime startTime, LocalTime endTime, Integer resourceId);
}
