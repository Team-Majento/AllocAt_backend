package com.uom.seat.condition.logic;


import com.uom.seat.condition.dto.ConditionRequest;
import com.uom.seat.condition.service.ConditionService;
import com.uom.seat.condition.validator.ConditionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConditionCreationLogic {
    @Autowired
    private ConditionValidator conditionValidator;

    @Autowired
    private ConditionService conditionService;


    public Integer createCondition(String accessToken, ConditionRequest conditionRequest) {
        // 1. validate company request
        // 2. create company

        conditionValidator.validateCondition(conditionRequest);

        Integer id = conditionService.createCondition(conditionRequest);

        return id;
    }
}
