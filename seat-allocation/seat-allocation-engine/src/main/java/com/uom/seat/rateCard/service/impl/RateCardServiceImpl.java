package com.uom.seat.rateCard.service.impl;

import com.uom.seat.company.dto.CompanyResponse;
import com.uom.seat.company.entity.CompanyEntity;
import com.uom.seat.rateCard.dto.RateCardRequest;
import com.uom.seat.rateCard.dto.RateCardResponse;
import com.uom.seat.rateCard.entity.RateCardEntity;
import com.uom.seat.rateCard.repository.RateCardRepository;
import com.uom.seat.rateCard.service.RateCardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RateCardServiceImpl implements RateCardService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RateCardRepository rateCardRepository;

    @Override
    public Integer createRateCard(RateCardRequest rateCard) {
        return rateCardRepository.save(convertToRateCardEntity(rateCard)).getId() ;
    }

    @Override
    public RateCardResponse getRateCard(Integer rateCardId) {
        return convertToRateCardResponse(rateCardRepository.findById(rateCardId).get());
    }

    @Override
    public RateCardResponse updateRateCard(Integer id, RateCardRequest rateCard) {
        RateCardEntity entity = rateCardRepository.saveAndFlush(updateRateCardEntity(rateCardRepository.findById(id).get(), rateCard));
        return convertToRateCardResponse(entity);
    }

    @Override
    public Boolean deleteRateCard(Integer rateCardId) {
        rateCardRepository.deleteById(rateCardId);
        return true;
    }

    private RateCardEntity updateRateCardEntity(RateCardEntity rateCardEntity, RateCardRequest rateCard) {
    rateCardEntity.setExtraHourRate(rateCard.getExtraHourRate());
    rateCardEntity.setHourRate(rateCard.getHourRate());
    rateCardEntity.setUnit(rateCard.getUnit());
    return  rateCardEntity;
    }

    private RateCardResponse convertToRateCardResponse(RateCardEntity rateCardEntity) {
        RateCardResponse dto = null;
        dto = modelMapper.map(rateCardEntity, RateCardResponse.class);
        return dto;
    }

    private RateCardEntity convertToRateCardEntity(RateCardRequest rateCard) {

        RateCardEntity entity = null;
        entity = modelMapper.map(rateCard, RateCardEntity.class);
        return entity;

    }
}
