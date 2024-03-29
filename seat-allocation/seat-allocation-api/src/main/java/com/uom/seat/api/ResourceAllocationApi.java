package com.uom.seat.api;


import com.uom.seat.resourceAllocation.dto.ResourceAllocationRequest;
import com.uom.seat.resourceAllocation.dto.ResourceAllocationResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ResourceAllocationApi {
    Integer createResourceAllocation(String bearerToken, ResourceAllocationRequest  resourceAllocation);
    Integer createRelevantResourceAllocation(String authorization, Integer bookingRequestID, String conditionName);
    Page<ResourceAllocationResponse> getAllResourceAllocations(String bearerToken, Integer page, Integer size);
    Page<ResourceAllocationResponse> getAllResourceAllocationsByRequestersId(String authorization, Integer page, Integer size, Integer requesterUserId);



    Integer sendNotificationEmails(String authorization, Integer userId, Integer resourceManagerId, Integer status, LocalDate requiredDate, LocalTime startTime, LocalTime endTime, Integer resourceId);

    Integer rejectRelevantBookingRequest(String authorization, Integer bookingRequestID);

    List<ResourceAllocationResponse> getAllResourceAllocationsByResourceId(String authorization, Integer resourceId);

    Integer getAllResourceAllocationsByCurrentMonth(String authorization);

    LocalTime getTotalAllocationHoursUserWise(String authorization, Integer userId);

    List<ResourceAllocationResponse> getAllCurrentOngoingAllocations(String authorization);

    List<Integer> getAllCompanyIdOfTheResourceAllocation(String authorization);

    Integer getAllCurrentOngoingAllocationCount(String authorization);
}
