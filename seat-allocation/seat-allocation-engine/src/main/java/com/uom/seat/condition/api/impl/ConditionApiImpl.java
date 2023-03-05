package com.uom.seat.condition.api.impl;

import com.uom.seat.api.ConditionApi;
import com.uom.seat.condition.dto.ConditionRequest;
import com.uom.seat.condition.logic.ConditionCreationLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class ConditionApiImpl implements ConditionApi {

    @Autowired
    private ConditionCreationLogic conditionCreationLogic;

    @Override
    public Integer createCondition(String accessToken, ConditionRequest conditionRequest) {
        return conditionCreationLogic.createCondition(accessToken,conditionRequest);
    }


}
