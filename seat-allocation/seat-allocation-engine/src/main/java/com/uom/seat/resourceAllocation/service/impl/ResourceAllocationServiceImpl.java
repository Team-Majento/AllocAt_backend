package com.uom.seat.resourceAllocation.service.impl;

import com.uom.seat.bookingRequest.entity.BookingRequestEntity;
import com.uom.seat.bookingRequest.repository.BookingRequestRepository;
import com.uom.seat.condition.service.ConditionService;
import com.uom.seat.resourceAllocatedCost.service.ResourceAllocatedCostService;
import com.uom.seat.resourceAllocation.dto.ResourceAllocationRequest;
import com.uom.seat.resourceAllocation.dto.ResourceAllocationResponse;
import com.uom.seat.resourceAllocation.entity.ResourceAllocationEntity;
import com.uom.seat.resourceAllocation.repository.ResourceAllocationRepository;
import com.uom.seat.resourceAllocation.service.ResourceAllocationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceAllocationServiceImpl implements ResourceAllocationService {

    @Autowired
    private BookingRequestRepository bookingRequestRepository;
    @Autowired
    private ResourceAllocationRepository resourceAllocationRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private ConditionService conditionService;

    @Autowired
    private ResourceAllocatedCostService resourceAllocatedCostService;

    @Override
    public Integer createResourceAllocation(ResourceAllocationRequest resourceAllocation) {
        return resourceAllocationRepository.save(convertToResourceAllocationEntity(resourceAllocation)).getId();
    }

    private ResourceAllocationEntity convertToResourceAllocationEntity(ResourceAllocationRequest resourceAllocation) {

        ResourceAllocationEntity entity = null;
        entity = modelMapper.map(resourceAllocation, ResourceAllocationEntity.class);

        return entity;
    }


    private ResourceAllocationResponse convertToResourceAllocationResponse(ResourceAllocationEntity entity) {

        ResourceAllocationResponse dto = null;
        dto = modelMapper.map(entity, ResourceAllocationResponse.class);

        return dto;
    }


    // ACCEPT RESOURCE BOOKING REQUEST
    @Override
    public Integer createReleventResourceAllocation(Integer bookingRequestID) {

        BookingRequestEntity releventBookingRequest = bookingRequestRepository.findById(bookingRequestID).orElse(null);
        releventBookingRequest.setStatus("Accepted");
        ResourceAllocationEntity resourceAllocationEntity = new ResourceAllocationEntity();
        resourceAllocationEntity.setResourceId(releventBookingRequest.getResourceId());
        resourceAllocationEntity.setRequesterUserId(releventBookingRequest.getRequesterUserId());
        resourceAllocationEntity.setRequestersManagersUserId(releventBookingRequest.getRequestersManagersUserId());
        resourceAllocationEntity.setCompanyId(releventBookingRequest.getCompanyId());
        resourceAllocationEntity.setRequiredDate(releventBookingRequest.getRequiredDate());
        resourceAllocationEntity.setStartTime(releventBookingRequest.getStartTime());
        resourceAllocationEntity.setEndTime(releventBookingRequest.getEndTime());
        resourceAllocationEntity.setActualEndTime(null);
        resourceAllocationEntity.setBookingRequestEntity(releventBookingRequest);

        conditionService.setConditionFkInResourceAllocationTable(resourceAllocationEntity);

        resourceAllocatedCostService.createResourceAllocatedCost(resourceAllocationEntity, releventBookingRequest.getResourceId());

        return resourceAllocationRepository.save(resourceAllocationEntity).getId();
    }


    @Override
    public Page<ResourceAllocationResponse> getALlResourceAllocations(Integer page, Integer size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<ResourceAllocationEntity> pageEntities = resourceAllocationRepository.findAll(pageable);

        List<ResourceAllocationEntity> entityList = pageEntities.getContent();
        List<ResourceAllocationResponse> dtoList = new ArrayList<ResourceAllocationResponse>();

        entityList.forEach(entity -> dtoList.add(convertToResourceAllocationResponse(entity)));

        return new PageImpl<ResourceAllocationResponse>(dtoList, pageable, pageEntities.getTotalElements());
    }

    @Override
    public Page<ResourceAllocationResponse> getALlResourceAllocationsByRequstersUserId(Integer requesterUserId, Integer page, Integer size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<ResourceAllocationEntity> pageEntities = resourceAllocationRepository.findAllByRequesterUserId(requesterUserId, pageable);

        List<ResourceAllocationEntity> entityList = pageEntities.getContent();
        List<ResourceAllocationResponse> dtoList = new ArrayList<ResourceAllocationResponse>();

        entityList.forEach(entity -> dtoList.add(convertToResourceAllocationResponse(entity)));

        return new PageImpl<ResourceAllocationResponse>(dtoList, pageable, pageEntities.getTotalElements());
    }


}
