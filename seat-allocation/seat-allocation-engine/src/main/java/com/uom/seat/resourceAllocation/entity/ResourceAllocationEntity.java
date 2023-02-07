package com.uom.seat.resourceAllocation.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="resource_allocation")
public class ResourceAllocationEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "resource_id", nullable = false)
    private Integer resourceId;

    @Column(name = "company_id", nullable = true)
    private Integer companyId;

    @Column(name = "requester_user_id", nullable = false)
    private Integer requesterUserId;

    @Column(name = "requester_manager_user_id", nullable = false)
    private Integer requestersManagersUserId;

    @Column(name = "required_date", nullable = false)
    private LocalDate requiredDate;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "actual_end_time", nullable = false)
    private LocalTime actualEndTime;

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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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
}
