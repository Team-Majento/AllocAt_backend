package com.uom.seat.server.rest.controller;

import com.uom.seat.api.ConditionApi;

import com.uom.seat.api.ResourceAllocatedCostApi;
import com.uom.seat.api.ResourceAllocationApi;

import com.uom.seat.api.ResourceApi;
import com.uom.seat.condition.dto.ConditionRequest;
import com.uom.seat.resource.dto.ResourceRequest;
import com.uom.seat.util.AccessTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.FileNotFoundException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/resourceAllocatedCost")
@Api(description = "The resourceAllocatedCost API for resourceAllocatedCost management tasks")
public class RsourceAllocatedCostController {


    @Autowired
    private ResourceAllocatedCostApi resourceAllocatedCostApi;

    @PostMapping("generate-company-wise-report/{company_id}/{from_date}/{to_date}")
    public ResponseEntity<Integer> generateReport(
            // @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            //@ApiParam(value = "JSON format of the resource allocation request.", required = true) @RequestBody final ResourceAllocationRequest resourceAllocation,
            @PathVariable("company_id") Integer company_id, @PathVariable("from_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from_date,
            @PathVariable("to_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to_date,
            UriComponentsBuilder builder) throws JRException, FileNotFoundException {

        ResponseEntity<Integer> responseEntity = null;
        /*logger.info("Create resourceAllocation request is received.");
        logger.debug("Create resourceAllocation request" + resourceAllocation.toString());*/

        Integer resourceAllocationId = resourceAllocatedCostApi.generateReportCompanyWise(AccessTokenUtil.getBearerToken("authorization"), company_id,from_date,to_date);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("resourceAllocation/{resourceAllocationId}").buildAndExpand(resourceAllocationId).toUri());

        responseEntity = new ResponseEntity<Integer>(resourceAllocationId, headers, HttpStatus.CREATED);
        return responseEntity;
    }
    @PostMapping("generate-general-report/{from_date}/{to_date}")
    public ResponseEntity<Integer> generateReport(
            // @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            //@ApiParam(value = "JSON format of the resource allocation request.", required = true) @RequestBody final ResourceAllocationRequest resourceAllocation,
            @PathVariable("from_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from_date,
            @PathVariable("to_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to_date,
            UriComponentsBuilder builder) throws JRException, FileNotFoundException {

        ResponseEntity<Integer> responseEntity = null;
        /*logger.info("Create resourceAllocation request is received.");
        logger.debug("Create resourceAllocation request" + resourceAllocation.toString());*/

        Integer resourceAllocationId = resourceAllocatedCostApi.generateGeneralReport(AccessTokenUtil.getBearerToken("authorization"),from_date,to_date);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("resourceAllocation/{resourceAllocationId}").buildAndExpand(resourceAllocationId).toUri());

        responseEntity = new ResponseEntity<Integer>(resourceAllocationId, headers, HttpStatus.CREATED);
        return responseEntity;
    }

}
