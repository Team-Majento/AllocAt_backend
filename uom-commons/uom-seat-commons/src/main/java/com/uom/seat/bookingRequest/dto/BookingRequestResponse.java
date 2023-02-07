package com.uom.seat.bookingRequest.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class BookingRequestResponse implements Serializable {

    private static final long serialVersionUID = -330739914741822751L;

    private Integer id;
    private Integer resourceId;
    private Integer requesterUserId;
    private Integer requestersManagersUserId;
    private Integer companyId;
    private LocalDate requiredDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String status ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookingRequestResponse{" +
                "id=" + id +
                ", resourceId=" + resourceId +
                ", requesterUserId=" + requesterUserId +
                ", requestersManagersUserId=" + requestersManagersUserId +
                ", companyId=" + companyId +
                ", requiredDate=" + requiredDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                '}';
    }
}
