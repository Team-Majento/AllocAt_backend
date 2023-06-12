package com.uom.seat.server.rest.controller;

import com.uom.seat.api.RateCardApi;
import com.uom.seat.rateCard.dto.RateCardRequest;
import com.uom.seat.rateCard.dto.RateCardResponse;
import com.uom.seat.util.AccessTokenUtil;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("Companies/resources")
@Api(description = "The rateCard API for rate card management tasks")
public class RateCardController {

    private static final Logger logger = Logger.getLogger(com.uom.seat.server.rest.controller.RateCardController.class);
    @Autowired
    private RateCardApi rateCardApi;


    @ApiOperation(value = "Register rate.", response = Integer.class, consumes = "application/json")
    @ApiResponses(value = {
            // @formatter:off
            @ApiResponse(code = 201, message = "The rate card is created and return rate card id."),
            @ApiResponse(code = 400, message = "Invalid API argument.")
            // @formatter:on
    })
    @PostMapping("{resourceId}/rateCard")
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('ROLE_admin') or hasAuthority('ROLE_resourceManager')")
    public ResponseEntity<Integer> registerRateCard(
            //   @ApiParam(value = "Bearer access token", required = false) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization
            @ApiParam(value = "JSON format of the rate card request.", required = true) @RequestBody final RateCardRequest rateCard,
            @PathVariable("resourceId") final Integer resourceId,
            UriComponentsBuilder builder) {

        ResponseEntity<Integer> responseEntity = null;
        logger.info("Register rate card request is received.");
        logger.debug("Register rate card request" + rateCard.toString());

        Integer rateCardId = rateCardApi.createRateCard(AccessTokenUtil.getBearerToken("authorization"),
                rateCard, resourceId);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("rateCards/{rateCardId}").buildAndExpand(rateCardId).toUri());

        responseEntity = new ResponseEntity<Integer>(rateCardId, headers, HttpStatus.CREATED);
        return responseEntity;
    }


    @ApiOperation(value = "Get Rate Card by ID", response = RateCardResponse.class, produces = "application/json")
    @GetMapping("{rateCardId}")
    public ResponseEntity<RateCardResponse> getRateCard(
            //  @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            @PathVariable("rateCardId") final Integer rateCardId) {

        ResponseEntity<RateCardResponse> responseEntity = null;
        logger.info("Get rateCard by id request is received.");
        RateCardResponse rateCard = rateCardApi.getRateCard(AccessTokenUtil.getBearerToken("authorization"), rateCardId);

        responseEntity = new ResponseEntity<RateCardResponse>(rateCard, HttpStatus.OK);
        return responseEntity;
    }


    @ApiOperation(value = "Update rateCard by id.", response = RateCardResponse.class, produces = "application/json")
    @PutMapping("{rateCardId}")
    @PreAuthorize("hasAuthority('ROLE_admin') or hasAuthority('ROLE_resourceManager')")
    public ResponseEntity<RateCardResponse> updateRateCard(   // ***  update??
                                                              //  @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
                                                              @PathVariable("rateCardId") final Integer rateCardId, @RequestBody final RateCardRequest rateCard) {

        ResponseEntity<RateCardResponse> responseEntity = null;
        logger.info("Get rateCard by id request is received.");
        RateCardResponse response = rateCardApi.updateRateCard(rateCardId, rateCard);

        responseEntity = new ResponseEntity<RateCardResponse>(response, HttpStatus.OK);
        return responseEntity;
    }

    @ApiOperation(value = "Delete RateCard by ID", response = RateCardResponse.class, produces = "application/json")
    @DeleteMapping("{rateCardId}")
    @PreAuthorize("hasAuthority('ROLE_admin')")
    public ResponseEntity<Boolean> deleteResource(
            //  @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            @PathVariable("rateCardId") final Integer rateCardId) {

        ResponseEntity<Boolean> responseEntity = null;
        logger.info("Delete rateCard by id request is received.");
        Boolean result = rateCardApi.deleteRateCard(AccessTokenUtil.getBearerToken("authorization"), rateCardId);
        responseEntity = new ResponseEntity<Boolean>(result, HttpStatus.OK);
        return responseEntity;
    }


}
