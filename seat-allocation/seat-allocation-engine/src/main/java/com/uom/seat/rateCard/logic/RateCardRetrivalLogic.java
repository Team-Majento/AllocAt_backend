package com.uom.seat.rateCard.logic;

import com.uom.seat.rateCard.dto.RateCardResponse;
import com.uom.seat.rateCard.service.RateCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RateCardRetrivalLogic {

    @Autowired
    private RateCardService rateCardService;

    public RateCardResponse getRateCard(String authorization, Integer rateCardId) {
             return rateCardService.getRateCard(rateCardId);
    }
    private void validateServiceParam(Integer id) {
        //TODO
        //ValidationUtil.validateNotEmpty(id, "The id is mandatory");
    }
}
