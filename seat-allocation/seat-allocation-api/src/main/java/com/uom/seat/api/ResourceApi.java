package com.uom.seat.api;

import com.uom.seat.resource.dto.ResourceRequest;
import com.uom.seat.resource.dto.ResourceResponse;
import com.uom.seat.review.dto.ReviewResponse;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ResourceApi {

    /**
     * Register resource.
     *
     * @param accessToken the access token
     * @param resource
     * @return the resource id
     */
    public Integer createResource(String accessToken, ResourceRequest resource,Integer companyId);

    ResourceResponse getResource(String accessToken, Integer id);

    ResourceResponse updateResource(Integer resourceId, ResourceRequest resource);


    Page<ResourceResponse> getAllResources(String authorization, Integer page, Integer size);

    Boolean deleteResource(String authorization, Integer resourceId);

    List<ReviewResponse> getReviews(String authorization, Integer resourceId);

    Page<ResourceResponse> getAllFilteredResources(String authorization, Integer page, Integer size, Integer companyId);
}
