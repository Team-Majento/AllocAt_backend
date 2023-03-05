package com.uom.seat.rateCard.logic;

import com.uom.seat.rateCard.dto.RateCardRequest;
import com.uom.seat.rateCard.service.RateCardService;
import com.uom.seat.rateCard.validator.RateCardValidator;
import com.uom.seat.resource.logic.ResourceCreationLogic;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RateCardCreationLogic {
    private static final Logger logger = Logger.getLogger(ResourceCreationLogic.class);

    @Autowired
    private RateCardValidator rateCardValidator;

    @Autowired
    private RateCardService rateCardService;

    public Integer createRateCard(String accessToken, RateCardRequest rateCard) {

        // 1. validate company request
        // 2. create company

        rateCardValidator.validateRateCard(rateCard);
        logger.info("The rate card is validated.");

        Integer id = rateCardService.createRateCard(rateCard);
        logger.info("The rate card is created.");

        return id;
    }

}
