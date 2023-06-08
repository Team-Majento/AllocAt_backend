package com.uom.seat.server.rest.controller;


import com.uom.seat.api.ResourceAllocationApi;
import com.uom.seat.resourceAllocation.dto.ResourceAllocationRequest;
import com.uom.seat.resourceAllocation.dto.ResourceAllocationResponse;
import com.uom.seat.util.AccessTokenUtil;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalTime;
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
    @PreAuthorize("hasAuthority('ROLE_admin')")
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



    @CrossOrigin
    @PostMapping("accept/{bookingRequestID}")
    @PreAuthorize("hasAuthority('ROLE_admin')")
    public ResponseEntity<Integer> acceptResourceAllocation(
            // @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            //@ApiParam(value = "JSON format of the resource allocation request.", required = true) @RequestBody final ResourceAllocationRequest resourceAllocation,
            @PathVariable("bookingRequestID") Integer bookingRequestID, UriComponentsBuilder builder) {

        ResponseEntity<Integer> responseEntity = null;
        /*logger.info("Create resourceAllocation request is received.");
        logger.debug("Create resourceAllocation request" + resourceAllocation.toString());*/

        Integer resourceAllocationId = resourceAllocationApi.createReleventResourceAllocation(AccessTokenUtil.getBearerToken("authorization"),
                bookingRequestID);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("resourceAllocation/{resourceAllocationId}").buildAndExpand(resourceAllocationId).toUri());

        responseEntity = new ResponseEntity<Integer>(resourceAllocationId, headers, HttpStatus.CREATED);
        return responseEntity;
    }

    @ApiOperation(value = "get all the resource allocations ", response = ResourceAllocationResponse.class, produces = "application/json")
    @GetMapping()
    public ResponseEntity<Page<ResourceAllocationResponse>> getAllResourceAllocations(
            /*@ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization*/
            @RequestParam(name = "page" ,defaultValue ="0" ,required = false)Integer page,
            @RequestParam(name = "size" ,defaultValue ="10" ,required = false)Integer size) {

        ResponseEntity<Page<ResourceAllocationResponse>> responseEntity = null;
        logger.info("get all the resource allocations request is received.");
        Page<ResourceAllocationResponse> listOfResourceAllocations = resourceAllocationApi.getAllResourceAllocations(AccessTokenUtil.getBearerToken("authorization"),page,size);

        responseEntity = new ResponseEntity<Page<ResourceAllocationResponse>>(listOfResourceAllocations, HttpStatus.OK);
        return responseEntity;
    }


    @ApiOperation(value="Get all resource allocation by requesterUserId ",response = ResourceAllocationResponse.class,produces = "application/json")
    @GetMapping("resource-allocation-page/{requesterUserId}")
    public ResponseEntity<Page<ResourceAllocationResponse>> getAllResourceAllocationsByRequesterId(
            //  @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            @RequestParam(name = "page" ,defaultValue ="0" ,required = false)Integer page,
            @RequestParam(name = "size" ,defaultValue ="10" ,required = false)Integer size,
            @PathVariable("requesterUserId") final Integer requesterUserId
            //@RequestParam(value = "sortBy" ,required = false) ResourceSortFiled sortBy,
            // @RequestParam(name = "direction" ,required = false) Sort.Direction direction
    ){
        ResponseEntity<Page<ResourceAllocationResponse>> responseEntity = null;
        Page<ResourceAllocationResponse> pageDtos = resourceAllocationApi.getAllResourceAllocationsByRequestersId( AccessTokenUtil.getBearerToken("authorization"),page,size,requesterUserId);
        responseEntity=new ResponseEntity<Page<ResourceAllocationResponse>>(pageDtos,HttpStatus.OK);
        return responseEntity;
    }


    @ApiOperation(value="Send notification email to employee and relevant manager",response = ResourceAllocationResponse.class,produces = "application/json")
    @CrossOrigin
    @GetMapping("/send-notification-email/{userId}/{resourceManagerId}/{status}/{requiredDate}/{startTime}/{endTime}/{resourceId}")
    @PreAuthorize("hasAuthority('ROLE_admin')")
    public ResponseEntity<Integer> sendNotificationEmails(
            // @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            //@ApiParam(value = "JSON format of the resource allocation request.", required = true) @RequestBody final ResourceAllocationRequest resourceAllocation,
            @PathVariable("userId") Integer userId,@PathVariable("resourceManagerId") Integer resourceManagerId,@PathVariable("status") Integer status,
            @PathVariable("requiredDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate requiredDate,@PathVariable("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @PathVariable("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime,@PathVariable("resourceId") Integer resourceId,UriComponentsBuilder builder) {

        ResponseEntity<Integer> responseEntity = null;
        /*logger.info("Create resourceAllocation request is received.");
        logger.debug("Create resourceAllocation request" + resourceAllocation.toString());*/

        Integer resourceAllocationId = resourceAllocationApi.sendNotificationEmails(AccessTokenUtil.getBearerToken("authorization"),
                userId,resourceManagerId,status,requiredDate,startTime,endTime,resourceId);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("resourceAllocation/{resourceAllocationId}").buildAndExpand(resourceAllocationId).toUri());

        responseEntity = new ResponseEntity<Integer>(resourceAllocationId, headers, HttpStatus.CREATED);
        return responseEntity;
    }


    @CrossOrigin
    @PostMapping("reject/{bookingRequestID}")
    @PreAuthorize("hasAuthority('ROLE_admin')")
    public ResponseEntity<Integer> rejectBookingRequest(
            // @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            //@ApiParam(value = "JSON format of the resource allocation request.", required = true) @RequestBody final ResourceAllocationRequest resourceAllocation,
            @PathVariable("bookingRequestID") Integer bookingRequestID, UriComponentsBuilder builder) {

        ResponseEntity<Integer> responseEntity = null;
        /*logger.info("Create resourceAllocation request is received.");
        logger.debug("Create resourceAllocation request" + resourceAllocation.toString());*/

        Integer resourceAllocationId = resourceAllocationApi.rejectRelevantBookingRequest(AccessTokenUtil.getBearerToken("authorization"),
                bookingRequestID);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("resourceAllocation/{resourceAllocationId}").buildAndExpand(resourceAllocationId).toUri());

        responseEntity = new ResponseEntity<Integer>(resourceAllocationId, headers, HttpStatus.CREATED);
        return responseEntity;
    }





}
