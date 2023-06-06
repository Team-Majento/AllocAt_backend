package com.uom.seat.resourceAllocation.logic;

import com.uom.seat.resourceAllocation.service.ResourceAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RejectRelevantBookingRequestLogic {

    @Autowired
    private ResourceAllocationService resourceAllocationService;


    public Integer rejectRelevantBookingRequest(Integer bookingRequestID) {
        return resourceAllocationService.rejectRelevantBookingRequest(bookingRequestID);
    }
}
