package com.uom.seat.condition.logic;


import com.uom.seat.condition.dto.ConditionResponse;
import com.uom.seat.condition.repository.ConditionRepository;
import com.uom.seat.condition.service.ConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConditionRetrievalLogic {

    @Autowired
    private ConditionService conditionService;
    public List<ConditionResponse> getAllConditions() {
        return conditionService.getAllConditions();
    }
}
