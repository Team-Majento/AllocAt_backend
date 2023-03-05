package com.uom.seat.rateCard.logic;

import com.uom.seat.rateCard.dto.RateCardRequest;
import com.uom.seat.rateCard.dto.RateCardResponse;
import com.uom.seat.rateCard.service.RateCardService;
import com.uom.seat.rateCard.validator.RateCardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RateCardUpdateLogic {
    @Autowired
    private RateCardService service;

    @Autowired
    private RateCardValidator rateCardValidator;



    public RateCardResponse updateRateCard(Integer id, RateCardRequest rateCard) {
        // 1. find rateCard by id
        // 2. validate rateCard request
        // 3. update rateCard


        rateCardValidator.validateRateCard(rateCard);

        return service.updateRateCard(id,rateCard);
    }
}
