package com.uom.seat.server.rest.controller;


import com.uom.seat.api.ConditionApi;
import com.uom.seat.condition.dto.ConditionRequest;
import com.uom.seat.util.AccessTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/conditions")
@Api(description = "The Condition API for condition management tasks")
public class ConditionController {

    @Autowired
    private ConditionApi conditionApi;

    @CrossOrigin
    @PostMapping("/add-condition")
    public ResponseEntity<Integer> registerCondition(
            //   @ApiParam(value = "Bearer access token", required = false) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization
            @ApiParam(value = "JSON format of the resource request.", required = true) @RequestBody final ConditionRequest conditionRequest,

            UriComponentsBuilder builder) {

        ResponseEntity<Integer> responseEntity = null;


        Integer conditionId = conditionApi.createCondition(AccessTokenUtil.getBearerToken("authorization"),
                conditionRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("conditions/{conditionId}").buildAndExpand(conditionId).toUri());

        responseEntity = new ResponseEntity<Integer>(conditionId, headers, HttpStatus.CREATED);
        return responseEntity;
    }
}
