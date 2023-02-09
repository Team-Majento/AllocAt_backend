package com.uom.seat.server.rest.controller;


import com.uom.seat.api.ResourceApi;
import com.uom.seat.resource.dto.ResourceRequest;
import com.uom.seat.resource.dto.ResourceResponse;
import com.uom.seat.util.AccessTokenUtil;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/companies")
@Api(description = "The resource API for resource management tasks")
public class ResourceController {

    private static final Logger logger = Logger.getLogger(ResourceController.class);

    @Autowired
    private ResourceApi resourceApi;


    @ApiOperation(value = "Register resource.", response = Integer.class, consumes = "application/json")
    @ApiResponses(value = {
            // @formatter:off
            @ApiResponse(code = 201, message = "The resource is created and return resource id."),
            @ApiResponse(code = 400, message = "Invalid API argument.")
            // @formatter:on
    })
    @PostMapping("{companyId}/resource")
    public ResponseEntity<Integer> registerResource(
            //   @ApiParam(value = "Bearer access token", required = false) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization
            @ApiParam(value = "JSON format of the resource request.", required = true) @RequestBody final ResourceRequest resource,
            @PathVariable("companyId") final Integer companyId,
            UriComponentsBuilder builder) {

        ResponseEntity<Integer> responseEntity = null;
        logger.info("Register company request is received.");
        logger.debug("Register company request" + resource.toString());

        Integer resourceId = resourceApi.createResource(AccessTokenUtil.getBearerToken("authorization"),
                resource,companyId);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("resources/{resourceId}").buildAndExpand(resourceId).toUri());

        responseEntity = new ResponseEntity<Integer>(resourceId, headers, HttpStatus.CREATED);
        return responseEntity;
    }

    @ApiOperation(value="Get Resource by ID",response = ResourceResponse.class,produces = "application/json")
    @GetMapping("{resourceId}")
    public ResponseEntity<ResourceResponse> getResource(
            //  @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            @PathVariable("resourceId") final Integer resourceId) {

        ResponseEntity<ResourceResponse> responseEntity = null;
        logger.info("Get resource by id request is received.");
        ResourceResponse resource = resourceApi.getResource(AccessTokenUtil.getBearerToken("authorization"), resourceId);

        responseEntity = new ResponseEntity<ResourceResponse>(resource, HttpStatus.OK);
        return responseEntity;
    }

    @ApiOperation(value = "Update resource by id.", response = ResourceResponse.class, produces = "application/json")
    @PutMapping("{resourceId}")
    public ResponseEntity<ResourceResponse> getResource(   // ***  update??
                                                           //  @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
                                                           @PathVariable("resourceId") final Integer resourceId, @RequestBody final ResourceRequest resource) {

        ResponseEntity<ResourceResponse> responseEntity = null;
        logger.info("Get resource by id request is received.");
        ResourceResponse response = resourceApi.updateResource(resourceId, resource);

        responseEntity = new ResponseEntity<ResourceResponse>(response, HttpStatus.OK);
        return responseEntity;
    }


    @ApiOperation(value="Get all Resources",response = ResourceResponse.class,produces = "application/json")
    @GetMapping("resources-page")
    public ResponseEntity<Page<ResourceResponse>> getAllResources(
            //  @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            @RequestParam(name = "page" ,defaultValue ="0" ,required = false)Integer page,
            @RequestParam(name = "size" ,defaultValue ="10" ,required = false)Integer size
            //@RequestParam(value = "sortBy" ,required = false) ResourceSortFiled sortBy,
            // @RequestParam(name = "direction" ,required = false) Sort.Direction direction
    ){

        ResponseEntity<Page<ResourceResponse>> responseEntity = null;

        Page<ResourceResponse> pageDtos = resourceApi.getAllResources( AccessTokenUtil.getBearerToken("authorization"), page,size);
        responseEntity=new ResponseEntity<Page<ResourceResponse>>(pageDtos,HttpStatus.OK);
        return responseEntity;
    }

    @ApiOperation(value="Delete Resource by ID",response = ResourceResponse.class,produces = "application/json")
    @DeleteMapping("{resourceId}")
    public ResponseEntity<Boolean> deleteResource(
            //  @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            @PathVariable("resourceId") final Integer resourceId) {

        ResponseEntity<Boolean> responseEntity = null;
        logger.info("Delete resource by id request is received.");
        Boolean result = resourceApi.deleteResource(AccessTokenUtil.getBearerToken("authorization"), resourceId);
        responseEntity = new ResponseEntity<Boolean>(result, HttpStatus.OK);
        return responseEntity;
    }




}
