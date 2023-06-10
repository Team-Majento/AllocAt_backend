package com.uom.seat.condition.api.impl;

import com.uom.seat.api.ConditionApi;
import com.uom.seat.condition.dto.ConditionRequest;
import com.uom.seat.condition.dto.ConditionResponse;
import com.uom.seat.condition.logic.ConditionCreationLogic;
import com.uom.seat.condition.logic.ConditionRetrievalLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class ConditionApiImpl implements ConditionApi {

    @Autowired
    private ConditionCreationLogic conditionCreationLogic;

    @Autowired
    private ConditionRetrievalLogic conditionRetrievalLogic;

    @Override
    public Integer createCondition(String accessToken, ConditionRequest conditionRequest) {
        return conditionCreationLogic.createCondition(accessToken,conditionRequest);
    }


    @Override
    public List<ConditionResponse> getAllConditions(String authorization) {
        return conditionRetrievalLogic.getAllConditions();
    }
}
