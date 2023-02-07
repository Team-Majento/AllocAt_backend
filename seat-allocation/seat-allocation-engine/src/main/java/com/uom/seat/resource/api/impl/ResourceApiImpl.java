package com.uom.seat.resource.api.impl;

import com.uom.seat.api.ResourceApi;
import com.uom.seat.resource.dto.ResourceRequest;
import com.uom.seat.resource.dto.ResourceResponse;
import com.uom.seat.resource.logic.ResourceCreationLogic;
import com.uom.seat.resource.logic.ResourceDeletionLogic;
import com.uom.seat.resource.logic.ResourceRetrievalLogic;
import com.uom.seat.resource.logic.ResourceUpdateLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


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

    @Override
    public Integer createResource(String accessToken, ResourceRequest resource) {

        return resourceCreationLogic.createResource(accessToken,resource);
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
}
