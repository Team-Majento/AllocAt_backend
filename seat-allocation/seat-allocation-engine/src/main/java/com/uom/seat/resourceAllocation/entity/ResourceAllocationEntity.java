package com.uom.seat.resourceAllocation.entity;


import com.uom.seat.bookingRequest.entity.BookingRequestEntity;
import com.uom.seat.condition.entity.ConditionEntity;
import com.uom.seat.resourceAllocatedCost.entity.ResourceAllocatedCostEntity;

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


    @Column(name = "actual_end_time", nullable = true)
    private LocalTime actualEndTime;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "resourceAllocationEntity")
    private ResourceAllocatedCostEntity resourceAllocatedCostEntity;

    @ManyToOne(cascade = CascadeType.ALL,targetEntity = ConditionEntity.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "condition_id_FK",referencedColumnName = "id")
    private ConditionEntity conditionEntity;


    @OneToOne(cascade = CascadeType.ALL,targetEntity = BookingRequestEntity.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_request_id_FK",referencedColumnName = "id")
    private BookingRequestEntity bookingRequestEntity;

    public BookingRequestEntity getBookingRequestEntity() {
        return bookingRequestEntity;
    }

    public void setBookingRequestEntity(BookingRequestEntity bookingRequestEntity) {
        this.bookingRequestEntity = bookingRequestEntity;
    }

    public ResourceAllocatedCostEntity getResourceAllocatedCostEntity() {
        return resourceAllocatedCostEntity;
    }

    public void setResourceAllocatedCostEntity(ResourceAllocatedCostEntity resourceAllocatedCostEntity) {
        this.resourceAllocatedCostEntity = resourceAllocatedCostEntity;
    }

    public ConditionEntity getConditionEntity() {
        return conditionEntity;
    }

    public void setConditionEntity(ConditionEntity conditionEntity) {
        this.conditionEntity = conditionEntity;
    }

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
