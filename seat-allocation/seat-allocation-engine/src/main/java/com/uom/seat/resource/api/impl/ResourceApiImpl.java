package com.uom.seat.resource.api.impl;

import com.uom.seat.api.ResourceApi;
import com.uom.seat.resource.dto.ResourceRequest;
import com.uom.seat.resource.dto.ResourceResponse;
import com.uom.seat.resource.logic.ResourceCreationLogic;
import com.uom.seat.resource.logic.ResourceDeletionLogic;
import com.uom.seat.resource.logic.ResourceRetrievalLogic;
import com.uom.seat.resource.logic.ResourceUpdateLogic;
import com.uom.seat.resource.service.ResourceService;
import com.uom.seat.review.dto.ReviewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class ResourceApiImpl implements ResourceApi {

    @Autowired
    private ResourceCreationLogic resourceCreationLogic;

    @Autowired
    private ResourceRetrievalLogic resourceRetrievalLogic;


    @Autowired
    private ResourceUpdateLogic resourceUpdateLogic;

    @Autowired
    private ResourceDeletionLogic resourceDeletionLogic;

    @Autowired
    private ResourceService resourceService;


    @Override
    public Integer createResource(String accessToken, ResourceRequest resource,Integer companyId) {

        return resourceCreationLogic.createResource(accessToken,resource,companyId);
    }

    @Override
    public ResourceResponse getResource(String accessToken, Integer id) {

        return resourceRetrievalLogic.getResource(accessToken,id);
    }

    @Override
    public ResourceResponse updateResource(Integer resourceId, ResourceRequest resource) {
        return resourceUpdateLogic.updateResource(resourceId,resource);
    }

    @Override
    public Page<ResourceResponse> getAllResources(String authorization, Integer page, Integer size) {


        return resourceRetrievalLogic.getAllResources(page,size);
    }

    @Override
    public Boolean deleteResource(String accessToken, Integer resourceId) {
        return resourceDeletionLogic.deleteResource(accessToken,resourceId);
    }

    @Override
    public List<ReviewResponse> getReviews(String authorization, Integer resourceId) {
        return resourceRetrievalLogic.getALlReviews(authorization,resourceId);
    }

    @Override
    public Page<ResourceResponse> getAllFilteredResources(String authorization, Integer page, Integer size, Integer companyId) {
        return resourceRetrievalLogic.getAllFilteredResources(page,size,companyId);
    }

    @Override
    public Integer getAllResourceCount() {
        return resourceService.getAllResourceCount();
    }

    @Override
    public Integer getAllResourcesBelongToCompanyId1() {
        return resourceService.getAllResourcesBelongToCompanyId1();
    }

    @Override
    public Integer getAllResourcesBelongToCompanyId2() {
        return resourceService.getAllResourcesBelongToCompanyId2();
    }

    @Override
    public Integer getAllResourcesBelongToCompanyId3() {
        return resourceService.getAllResourcesBelongToCompanyId3();
    }

    @Override
    public Integer getAllResourcesBelongToCompanyId4() {
        return resourceService.getAllResourcesBelongToCompanyId4();
    }

    @Override
    public Integer getAllResourcesBelongToCompanyId5() {
        return resourceService.getAllResourcesBelongToCompanyId5();
    }

    @Override
    public Integer getAllResourcesBelongToCompanyId6() {
        return resourceService.getAllResourcesBelongToCompanyId6();
    }
}

