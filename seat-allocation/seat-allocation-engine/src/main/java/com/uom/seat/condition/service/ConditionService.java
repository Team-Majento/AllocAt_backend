package com.uom.seat.condition.service;

import com.uom.seat.condition.dto.ConditionRequest;

import com.uom.seat.condition.dto.ConditionResponse;
import com.uom.seat.resourceAllocation.entity.ResourceAllocationEntity;

import java.util.List;

public interface ConditionService {
    Integer createCondition(ConditionRequest conditionRequest);

    void setConditionFkInResourceAllocationTable(ResourceAllocationEntity resourceAllocationEntity, String conditionName);


    List<ConditionResponse> getAllConditions();
}