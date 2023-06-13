package com.uom.seat.resource.service;

import com.uom.seat.resource.dto.ResourceRequest;
import com.uom.seat.resource.dto.ResourceResponse;
import com.uom.seat.review.dto.ReviewResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ResourceService {

    public Integer createResource(ResourceRequest resource,Integer companyId);

    public ResourceResponse getResource(Integer id);
    public ResourceResponse updateResource(Integer id, ResourceRequest resource);

    Page<ResourceResponse> getAllResources(Integer page, Integer size);

    Boolean deleteResource(Integer resourceId);

    List<ReviewResponse> getAllReviews(Integer resourceId);

    Page<ResourceResponse> getAllFilteredResources(Integer page, Integer size, Integer companyId);

    Integer getAllResourceCount();

    Integer getAllResourcesBelongToCompanyId1();

    Integer getAllResourcesBelongToCompanyId2();

    Integer getAllResourcesBelongToCompanyId3();

    Integer getAllResourcesBelongToCompanyId4();

    Integer getAllResourcesBelongToCompanyId5();

    Integer getAllResourcesBelongToCompanyId6();
}
