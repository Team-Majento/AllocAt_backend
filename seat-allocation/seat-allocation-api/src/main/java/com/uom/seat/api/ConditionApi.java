package com.uom.seat.api;

import com.uom.seat.condition.dto.ConditionRequest;
import com.uom.seat.condition.dto.ConditionResponse;
import com.uom.seat.resource.dto.ResourceRequest;

import java.util.List;

public interface ConditionApi {

    public Integer createCondition(String accessToken, ConditionRequest conditionRequest);

    List<ConditionResponse> getAllConditions(String authorization);
}
