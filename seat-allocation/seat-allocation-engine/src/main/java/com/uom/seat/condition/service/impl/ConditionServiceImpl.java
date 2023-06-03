package com.uom.seat.condition.service.impl;


import com.uom.seat.condition.dto.ConditionRequest;
import com.uom.seat.condition.entity.ConditionEntity;
import com.uom.seat.condition.repository.ConditionRepository;
import com.uom.seat.condition.service.ConditionService;
import com.uom.seat.resource.dto.ResourceRequest;
import com.uom.seat.resource.entity.ResourceEntity;
import com.uom.seat.resourceAllocation.entity.ResourceAllocationEntity;
import com.uom.seat.resourceAllocation.repository.ResourceAllocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

@Service
public class ConditionServiceImpl implements ConditionService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ConditionRepository conditionRepository;



    @Override
    public Integer createCondition(ConditionRequest conditionRequest) {
        ConditionEntity conditionEntity=convertToConditionEntity(conditionRequest);
        return conditionRepository.save(conditionEntity).getId() ;

    }

    private ConditionEntity convertToConditionEntity(ConditionRequest conditionRequest) {

        ConditionEntity entity = null;
        entity = modelMapper.map(conditionRequest, ConditionEntity.class);

        return entity;
    }

    ///////////////////// SET UP CONDITION ///////////////////////////////
    @Override
    public void setConditionFkInResourceAllocationTable(ResourceAllocationEntity resourceAllocationEntity){
        januaryFirst(resourceAllocationEntity);
    }

    public Boolean januaryFirst(ResourceAllocationEntity resourceAllocationEntity) {

        Integer day = resourceAllocationEntity.getRequiredDate().getDayOfMonth();
        String month = String.valueOf(resourceAllocationEntity.getRequiredDate().getMonth());
        if (day == 1 && month == "JANUARY") {
            ConditionEntity condition = conditionRepository.findById(1).get();
            resourceAllocationEntity.setConditionEntity(condition);
            return true;
        }
        else {
            return  false;
        }
    }

    ////////////////////////////////////////////////////////////////////////




}