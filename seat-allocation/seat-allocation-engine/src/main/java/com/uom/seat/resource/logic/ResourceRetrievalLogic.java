package com.uom.seat.resource.logic;

import com.uom.seat.resource.dto.ResourceResponse;
import com.uom.seat.resource.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


@Component
public class ResourceRetrievalLogic {

    @Autowired
    private ResourceService service;
    public ResourceResponse getResource(String accessToken, Integer id) {
        return service.getResource(id);
    }

    private void validateServiceParam(Integer id) {
        //TODO
        //ValidationUtil.validateNotEmpty(id, "The id is mandatory");
    }


    public Page<ResourceResponse> getAllResources(Integer page, Integer size) {
        return service.getAllResources(page,size);
    }
}
