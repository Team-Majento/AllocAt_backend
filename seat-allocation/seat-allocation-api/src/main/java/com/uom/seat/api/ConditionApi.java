package com.uom.seat.api;

import com.uom.seat.condition.dto.ConditionRequest;
import com.uom.seat.resource.dto.ResourceRequest;

public interface ConditionApi {

    public Integer createCondition(String accessToken, ConditionRequest conditionRequest);

}
