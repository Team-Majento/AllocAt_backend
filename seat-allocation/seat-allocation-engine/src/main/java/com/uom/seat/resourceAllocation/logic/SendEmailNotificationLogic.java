package com.uom.seat.resourceAllocation.logic;

import com.uom.seat.resourceAllocation.service.ResourceAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendEmailNotificationLogic {
    @Autowired
    private ResourceAllocationService resourceAllocationService;
    public void sendEmail() {
        resourceAllocationService.sendEmail();
    }
}
