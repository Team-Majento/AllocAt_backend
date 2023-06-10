package com.uom.seat.condition.service.impl;


import com.uom.seat.condition.dto.ConditionRequest;
import com.uom.seat.condition.dto.ConditionResponse;
import com.uom.seat.condition.entity.ConditionEntity;
import com.uom.seat.condition.repository.ConditionRepository;
import com.uom.seat.condition.service.ConditionService;
import com.uom.seat.resourceAllocation.entity.ResourceAllocationEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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




    ///////////////////// SET UP CONDITION ///////////////////////////////
    @Override
    public void setConditionFkInResourceAllocationTable(ResourceAllocationEntity resourceAllocationEntity, String conditionName){
           ConditionEntity condition = conditionRepository.findConditionByName(conditionName);
           resourceAllocationEntity.setConditionEntity(condition);
    }

//    public Boolean januaryFirst(ResourceAllocationEntity resourceAllocationEntity) {
//
//        Integer day = resourceAllocationEntity.getRequiredDate().getDayOfMonth();
//        String month = String.valueOf(resourceAllocationEntity.getRequiredDate().getMonth());
//        if (day == 1 && month == "JANUARY") {
//            ConditionEntity condition = conditionRepository.findById(1).get();
//            resourceAllocationEntity.setConditionEntity(condition);
//            return true;
//        }
//        else {
//            return  false;
//        }
//    }

    ////////////////////////////////////////////////////////////////////////


    @Override
    public List<ConditionResponse> getAllConditions() {
        List<ConditionResponse> dtoList = new ArrayList<ConditionResponse>();
       List<ConditionEntity> conditionEntities= conditionRepository.findAll();
        conditionEntities.forEach(entity -> dtoList.add(convertToConditionResponse(entity)));
        return dtoList;
    }

    private ConditionEntity convertToConditionEntity(ConditionRequest conditionRequest) {

        ConditionEntity entity = null;
        entity = modelMapper.map(conditionRequest, ConditionEntity.class);

        return entity;
    }
    private ConditionResponse convertToConditionResponse(ConditionEntity conditionEntity) {

        ConditionResponse dto = null;
        dto = modelMapper.map(conditionEntity, ConditionResponse.class);
        return dto;

    }

}