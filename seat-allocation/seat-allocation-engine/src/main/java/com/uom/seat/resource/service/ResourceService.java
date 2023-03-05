package com.uom.seat.resource.service;

import com.uom.seat.resource.dto.ResourceRequest;
import com.uom.seat.resource.dto.ResourceResponse;
import org.springframework.data.domain.Page;

public interface ResourceService {

    public Integer createResource(ResourceRequest resource,Integer companyId);

    public ResourceResponse getResource(Integer id);
    public ResourceResponse updateResource(Integer id, ResourceRequest resource);

    Page<ResourceResponse> getAllResources(Integer page, Integer size);

    Boolean deleteResource(Integer resourceId);
}
