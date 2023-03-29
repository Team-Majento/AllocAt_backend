package com.uom.seat.rateCard.service.impl;

import com.uom.seat.rateCard.dto.RateCardRequest;
import com.uom.seat.rateCard.dto.RateCardResponse;
import com.uom.seat.rateCard.entity.RateCardEntity;
import com.uom.seat.rateCard.repository.RateCardRepository;
import com.uom.seat.rateCard.service.RateCardService;
import com.uom.seat.resource.entity.ResourceEntity;
import com.uom.seat.resource.repository.ResourceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RateCardServiceImpl implements RateCardService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RateCardRepository rateCardRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public Integer createRateCard(RateCardRequest rateCard, Integer resourceId) {
        return rateCardRepository.save(convertToRateCardEntity(rateCard,resourceId)).getId() ;
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

    private RateCardEntity convertToRateCardEntity(RateCardRequest rateCard, Integer resourceId) {

        ResourceEntity resource=resourceRepository.findById(resourceId).get();
        RateCardEntity entity = null;
        entity = modelMapper.map(rateCard, RateCardEntity.class);
        entity.setResourceEntity(resource);
        return entity;

    }
}
