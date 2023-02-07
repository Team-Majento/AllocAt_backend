package com.uom.seat.resourceAllocation.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ResourceAllocationRequest {

    private Integer resourceId;
    private Integer requesterUserId;
    private Integer requestersManagersUserId;
    private Integer companyId;
    private LocalDate requiredDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime actualEndTime;

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getRequesterUserId() {
        return requesterUserId;
    }

    public void setRequesterUserId(Integer requesterUserId) {
        this.requesterUserId = requesterUserId;
    }

    public Integer getRequestersManagersUserId() {
        return requestersManagersUserId;
    }

    public void setRequestersManagersUserId(Integer requestersManagersUserId) {
        this.requestersManagersUserId = requestersManagersUserId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public LocalDate getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(LocalDate requiredDate) {
        this.requiredDate = requiredDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime getActualEndTime() {
        return actualEndTime;
    }

    public void setActualEndTime(LocalTime actualEndTime) {
        this.actualEndTime = actualEndTime;
    }

    @Override
    public String toString() {
        return "ResourceAllocationRequest{" +
                "resourceId=" + resourceId +
                ", requesterUserId=" + requesterUserId +
                ", requestersManagersUserId=" + requestersManagersUserId +
                ", companyId=" + companyId +
                ", requiredDate=" + requiredDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", actualEndTime=" + actualEndTime +
                '}';
    }
}
