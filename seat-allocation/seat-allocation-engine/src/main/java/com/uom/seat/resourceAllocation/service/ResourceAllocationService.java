package com.uom.seat.resourceAllocation.service;

import com.uom.seat.resourceAllocation.dto.ResourceAllocationRequest;
import com.uom.seat.resourceAllocation.dto.ResourceAllocationResponse;

import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ResourceAllocationService {
    Integer createResourceAllocation(ResourceAllocationRequest resourceAllocation);


    Integer createRelevantResourceAllocation(Integer bookingRequestID, String conditionName);

    Page<ResourceAllocationResponse> getALlResourceAllocationsByRequstersUserId(Integer requesterUserId, Integer page, Integer size);
    Page<ResourceAllocationResponse> getALlResourceAllocations(Integer page,Integer size);


    Integer sendNotificationEmail(Integer userId, Integer resourceManagerId, Integer status, LocalDate requiredDate, LocalTime startTime, LocalTime endTime, Integer resourceId);

    Integer rejectRelevantBookingRequest(Integer bookingRequestID);

    List<ResourceAllocationResponse> getAllResourceAllocationsByResourceId(Integer resourceId);

    Integer getAllResourceAllocationsByCurrentMonth();

    LocalTime getTotalAllocationHoursUserWise(Integer userId);

    List<ResourceAllocationResponse> getAllCurrentOngoingAllocations();
}
