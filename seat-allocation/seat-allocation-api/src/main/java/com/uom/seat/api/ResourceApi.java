package com.uom.seat.api;

import com.uom.seat.resource.dto.ResourceRequest;
import com.uom.seat.resource.dto.ResourceResponse;
import org.springframework.data.domain.Page;


public interface ResourceApi {

    /**
     * Register resource.
     *
     * @param accessToken the access token
     * @param resource
     * @return the resource id
     */
    public Integer createResource(String accessToken, ResourceRequest resource);

    ResourceResponse getResource(String accessToken, Integer id);

    ResourceResponse updateResource(Integer resourceId, ResourceRequest resource);


    Page<ResourceResponse> getAllResources(String authorization, Integer page, Integer size);

    Boolean deleteResource(String authorization, Integer resourceId);
}
