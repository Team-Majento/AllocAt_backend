package com.uom.seat.resourceAllocation.service.impl;



import com.uom.seat.resourceAllocation.dto.ResourceAllocationRequest;
import com.uom.seat.resourceAllocation.dto.ResourceAllocationResponse;
import com.uom.seat.resourceAllocation.entity.ResourceAllocationEntity;
import com.uom.seat.resourceAllocation.repository.ResourceAllocationRepository;
import com.uom.seat.resourceAllocation.service.ResourceAllocationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceAllocationServiceImpl implements ResourceAllocationService {

    @Autowired
    private ResourceAllocationRepository resourceAllocationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Integer createResourceAllocation(ResourceAllocationRequest resourceAllocation) {
        return resourceAllocationRepository.save(convertToResourceAllocationEntity(resourceAllocation)).getId();
    }

    private ResourceAllocationEntity convertToResourceAllocationEntity(ResourceAllocationRequest resourceAllocation) {

        ResourceAllocationEntity entity = null;
        entity = modelMapper.map(resourceAllocation, ResourceAllocationEntity.class);
        // entity.setXid(UUID.randomUUID().toString());

        return entity;
    }


    @Override
    public List<ResourceAllocationResponse> getALlResourceAllocations() {
        return convertToResourceAloocationResponseList(resourceAllocationRepository.findAll());
    }


    private List<ResourceAllocationResponse> convertToResourceAloocationResponseList(List<ResourceAllocationEntity> entityList) {
        int val=0;
        List<ResourceAllocationResponse> dtoList=new ArrayList<>();
        while(entityList.size()>val) {
            ResourceAllocationResponse dto=null;
            dto=modelMapper.map(entityList.get(val), ResourceAllocationResponse.class);
            dtoList.add(dto);
            val++;
        }
        return dtoList;
    }

    @Override
    public ResourceAllocationResponse getResourceAllocationByRequesterUserId(Integer requesterUserId) {
        return convertToResourceAloocationResponse(resourceAllocationRepository.findByRequesterUserId(requesterUserId));
    }

    private ResourceAllocationResponse convertToResourceAloocationResponse(ResourceAllocationEntity entity) {

        ResourceAllocationResponse dto=null;
        dto=modelMapper.map(entity, ResourceAllocationResponse.class);

        return dto;
    }
}
