package com.uom.seat.rateCard.service;

import com.uom.seat.rateCard.dto.RateCardRequest;
import com.uom.seat.rateCard.dto.RateCardResponse;

public interface RateCardService {
    Integer createRateCard(RateCardRequest rateCard, Integer resourceId);

    RateCardResponse getRateCard(Integer rateCardId);

    RateCardResponse updateRateCard(Integer id, RateCardRequest rateCard);

    Boolean deleteRateCard(Integer rateCardId);

}
