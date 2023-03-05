package com.uom.seat.api;

import com.uom.seat.rateCard.dto.RateCardRequest;
import com.uom.seat.rateCard.dto.RateCardResponse;

public interface RateCardApi {
    Integer createRateCard(String authorization, RateCardRequest rateCard);

    RateCardResponse getRateCard(String authorization, Integer rateCardId);

    RateCardResponse updateRateCard(Integer rateCardId, RateCardRequest rateCard);

    Boolean deleteRateCard(String authorization, Integer rateCardId);
}
