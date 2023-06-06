package com.uom.seat.resourceAllocation.logic;

import com.uom.seat.resourceAllocation.service.ResourceAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class SendEmailNotificationLogic {
    @Autowired
    private ResourceAllocationService resourceAllocationService;


    public Integer sendNotificationEmail(String authorization, Integer userId, Integer resourceManagerId, Integer status, LocalDate requiredDate, LocalTime startTime, LocalTime endTime, Integer resourceId) {
         return  resourceAllocationService.sendNotificationEmail(userId,resourceManagerId,status,requiredDate,startTime,endTime,resourceId);
    }
}
