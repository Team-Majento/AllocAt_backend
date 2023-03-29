package com.uom.seat.rateCard.api.impl;

import com.uom.seat.api.RateCardApi;
import com.uom.seat.rateCard.dto.RateCardRequest;
import com.uom.seat.rateCard.dto.RateCardResponse;
import com.uom.seat.rateCard.logic.RateCardCreationLogic;
import com.uom.seat.rateCard.logic.RateCardDeletionLogic;
import com.uom.seat.rateCard.logic.RateCardRetrivalLogic;
import com.uom.seat.rateCard.logic.RateCardUpdateLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)

public class RateCardApiImpl implements RateCardApi {

    @Autowired
    private RateCardCreationLogic rateCardCreationLogic;

    @Autowired
    private RateCardRetrivalLogic rateCardRetrivalLogic;

    @Autowired
    private RateCardUpdateLogic rateCardUpdateLogic;

    @Autowired
    private RateCardDeletionLogic rateCardDeletionLogic;

    @Override
    public Integer createRateCard(String authorization, RateCardRequest rateCard, Integer resourceId) {
        return rateCardCreationLogic.createRateCard(authorization,rateCard,resourceId);

    }

    @Override
    public RateCardResponse getRateCard(String authorization, Integer rateCardId) {
        return rateCardRetrivalLogic.getRateCard(authorization,rateCardId);
    }

    @Override
    public RateCardResponse updateRateCard(Integer rateCardId, RateCardRequest rateCard) {
        return rateCardUpdateLogic.updateRateCard(rateCardId,rateCard);
    }

    @Override
    public Boolean deleteRateCard(String authorization, Integer rateCardId) {
        return rateCardDeletionLogic.deleteRateCard(authorization,rateCardId);
    }


}
