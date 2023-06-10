package com.uom.seat.condition.service;

import com.uom.seat.condition.dto.ConditionRequest;

import com.uom.seat.resourceAllocation.entity.ResourceAllocationEntity;

public interface ConditionService {
    Integer createCondition(ConditionRequest conditionRequest);

//    void setConditionFkInResourceAllocationTable(ResourceAllocationEntity resourceAllocationEntity);


}