package com.uom.seat.resource.service.impl;

import com.uom.seat.company.entity.CompanyEntity;
import com.uom.seat.company.repository.CompanyRepository;
import com.uom.seat.resource.dto.ResourceRequest;
import com.uom.seat.resource.dto.ResourceResponse;
import com.uom.seat.resource.entity.ResourceEntity;
import com.uom.seat.resource.repository.ResourceRepository;
import com.uom.seat.resource.service.ResourceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Integer createResource(ResourceRequest resource,Integer companyId) {
        return resourceRepository.save(convertToResourceEntity(resource,companyId)).getId() ;
    }

    @Override
    public ResourceResponse getResource(Integer id) {
        return convertToResourceResponse(resourceRepository.findById(id).get()) ;

    }

    @Override
    public ResourceResponse updateResource(Integer id, ResourceRequest resource) {
        ResourceEntity entity = resourceRepository.saveAndFlush(updateResourceEntity(resourceRepository.findById(id).get(), resource));
        return convertToResourceResponse(entity);

    }

    @Override
    public Page<ResourceResponse> getAllResources(Integer page, Integer size) {
        PageRequest pageable= PageRequest.of(page,size);
        Page<ResourceEntity> pageEntities=resourceRepository.findAll(pageable);

        List<ResourceEntity> entityList= pageEntities.getContent();
        List<ResourceResponse> dtoList = new ArrayList<ResourceResponse>();

        entityList.forEach(entity -> dtoList.add(convertToResourceResponse(entity)));

        return new PageImpl<ResourceResponse>(dtoList,pageable,pageEntities.getTotalElements());
    }

    @Override
    public Boolean deleteResource(Integer resourceId) {
        ResourceEntity entity = resourceRepository.saveAndFlush(deleteResource(resourceRepository.findById(resourceId).get()));
        return  !entity.getActiveStatus();
    }

    private ResourceEntity deleteResource(ResourceEntity resourceEntity) {
        resourceEntity.setActiveStatus(false);
        return  resourceEntity;
    }

    private ResourceEntity updateResourceEntity(ResourceEntity entity, ResourceRequest resource) {

        entity.setResourceType(resource.getResourceType());
        entity.setAvailability(resource.getAvailability());
        entity.setBuildingId(resource.getBuildingId());
        entity.setDescription(resource.getDescription());
        entity.setFloor(resource.getFloor());
        entity.setImgUrl(resource.getImgUrl());
        entity.setMaximumCapacity(resource.getMaximumCapacity());
        entity.setRateCardId(resource.getRateCardId());
        entity.setActiveStatus(resource.getActiveStatus());
        return entity;
    }

    private ResourceEntity convertToResourceEntity(ResourceRequest resource,Integer companyId) {

        CompanyEntity companyEntity= companyRepository.findById(companyId).get();

        ResourceEntity entity = null;
        entity = modelMapper.map(resource, ResourceEntity.class);
        entity.setCompany(companyEntity);
        entity.setXid(UUID.randomUUID().toString());

        return entity;
    }


    private ResourceResponse convertToResourceResponse(ResourceEntity entity) {
        ResourceResponse dto = null;
        dto = modelMapper.map(entity, ResourceResponse.class);

        return dto;
    }

}