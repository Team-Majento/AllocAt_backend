package com.uom.seat.rateCard.validator;

import com.uom.seat.rateCard.dto.RateCardRequest;
import org.springframework.stereotype.Component;

@Component
public class RateCardValidator {

    public void validateRateCard(RateCardRequest rateCard) {
        validateMandatoryFields(rateCard);
        validateOptionalFields(rateCard);
    }

    private void validateMandatoryFields(RateCardRequest rateCard) {
        // TODO

    }

    private void validateOptionalFields(RateCardRequest rateCard) {
        // TODO
    }



}
