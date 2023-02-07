package com.uom.seat.server.rest.controller;


import com.uom.seat.api.ResourceAllocationApi;
import com.uom.seat.resourceAllocation.dto.ResourceAllocationRequest;
import com.uom.seat.resourceAllocation.dto.ResourceAllocationResponse;
import com.uom.seat.util.AccessTokenUtil;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/resourceAllocaion")
@Api(description = "The resourceAllocation API for resourceAllocation management tasks")

public class ResourceAllocationController {

    private static final Logger logger = Logger.getLogger(ResourceAllocationController.class);

    @Autowired
    private ResourceAllocationApi resourceAllocationApi;


    @ApiOperation(value = "Create resource allocation.", response = Integer.class, consumes = "application/json")
    @ApiResponses(value = {
            // @formatter:off
            @ApiResponse(code = 201, message = "The resourceAllocation is created and return resouceAllocationId."),
            @ApiResponse(code = 400, message = "Invalid API argument.")
            // @formatter:on
    })
    @PostMapping()
    public ResponseEntity<Integer> registerResourceAllocation(
           // @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            @ApiParam(value = "JSON format of the resource allocation request.", required = true) @RequestBody final ResourceAllocationRequest resourceAllocation,
            UriComponentsBuilder builder) {

        ResponseEntity<Integer> responseEntity = null;
        logger.info("Create resourceAllocation request is received.");
        logger.debug("Create resourceAllocation request" + resourceAllocation.toString());

        Integer resourceAllocationId = resourceAllocationApi.createResourceAllocation(AccessTokenUtil.getBearerToken("authorization"),
                resourceAllocation);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("resourceAllocation/{resourceAllocationId}").buildAndExpand(resourceAllocationId).toUri());

        responseEntity = new ResponseEntity<Integer>(resourceAllocationId, headers, HttpStatus.CREATED);
        return responseEntity;
    }

    @ApiOperation(value = "get all the resource allocations ", response = ResourceAllocationResponse.class, produces = "application/json")
    @GetMapping()
    public ResponseEntity<List<ResourceAllocationResponse>> getAllResourceAllocations(
            /*@ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization*/) {

        ResponseEntity<List<ResourceAllocationResponse>> responseEntity = null;
        logger.info("get all the resource allocations request is received.");
        List<ResourceAllocationResponse> listOfResourceAllocations = resourceAllocationApi.getAllResourceAllocations(AccessTokenUtil.getBearerToken("authorization"));

        responseEntity = new ResponseEntity<List<ResourceAllocationResponse>>(listOfResourceAllocations, HttpStatus.OK);
        return responseEntity;
    }


    @ApiOperation(value = "get resource allocation by requesterUserId.", response = ResourceAllocationResponse.class, produces = "application/json")
    @GetMapping("{requesterUserId}")
    public ResponseEntity<ResourceAllocationResponse> getResourceAllocationByRequesterUserId(
          //  @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            @PathVariable("requesterUserId") final Integer requesterUserId) {

        ResponseEntity<ResourceAllocationResponse> responseEntity = null;
        logger.info("Get ResourceAllocation by requesterUserId request is received.");
        ResourceAllocationResponse resourceAllocation = resourceAllocationApi
                .getResourceAllocationByRequesterUserId(AccessTokenUtil.getBearerToken("authorization"), requesterUserId);

        responseEntity = new ResponseEntity<ResourceAllocationResponse>(resourceAllocation, HttpStatus.OK);
        return responseEntity;
    }




}
