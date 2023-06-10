package com.uom.seat.resourceAllocation.api.impl;

import com.uom.seat.api.ResourceAllocationApi;
import com.uom.seat.resourceAllocation.dto.ResourceAllocationRequest;
import com.uom.seat.resourceAllocation.dto.ResourceAllocationResponse;
import com.uom.seat.resourceAllocation.logic.RejectRelevantBookingRequestLogic;
import com.uom.seat.resourceAllocation.logic.ResourceAllocationCreationLogic;
import com.uom.seat.resourceAllocation.logic.ResourceAllocationRetrievalLogic;
import com.uom.seat.resourceAllocation.logic.SendEmailNotificationLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class ResourceAllocationApiImpl implements ResourceAllocationApi {

    @Autowired
    private RejectRelevantBookingRequestLogic rejectRelevantBookingRequestLogic;

    @Autowired
    private SendEmailNotificationLogic sendEmailNotificationLogic;
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
    public Integer createRelevantResourceAllocation(String authorization, Integer bookingRequestID, String conditionName) {
        return resourceAllocationCreationLogic.createRelevantResourceAllocation(authorization,bookingRequestID,conditionName);
    }


    @Override
    public Integer sendNotificationEmails(String authorization, Integer userId, Integer resourceManagerId, Integer status, LocalDate requiredDate, LocalTime startTime, LocalTime endTime, Integer resourceId) {
        return sendEmailNotificationLogic.sendNotificationEmail(authorization,userId,resourceManagerId,status,requiredDate,startTime,endTime,resourceId);
    }

    @Override
    public Integer rejectRelevantBookingRequest(String authorization, Integer bookingRequestID) {
        return rejectRelevantBookingRequestLogic.rejectRelevantBookingRequest(bookingRequestID);
    }
}
