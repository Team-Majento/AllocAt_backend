package com.uom.seat.server.rest.controller;

import com.uom.seat.api.ReviewApi;
import com.uom.seat.company.dto.CompanyResponse;
import com.uom.seat.resource.dto.ResourceResponse;
import com.uom.seat.review.dto.ReviewRequest;
import com.uom.seat.review.dto.ReviewResponse;
import com.uom.seat.util.AccessTokenUtil;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/resources")
@Api(description = "The review API for review management tasks")
public class ReviewController {

    private static final Logger logger = Logger.getLogger(ReviewController.class);

    @Autowired
    private ReviewApi reviewApi;



    @ApiOperation(value = "Register review.", response = Integer.class, consumes = "application/json")
    @ApiResponses(value = {
            // @formatter:off
            @ApiResponse(code = 201, message = "The review is created and return review id."),
            @ApiResponse(code = 400, message = "Invalid API argument.")
            // @formatter:on
    })
    @PostMapping("{resourceId}/review")
    public ResponseEntity<Integer> registerReview(
            //   @ApiParam(value = "Bearer access token", required = false) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization
            @ApiParam(value = "JSON format of the review request.", required = true) @RequestBody final ReviewRequest review,
            @PathVariable("resourceId") final Integer resourceId,
            UriComponentsBuilder builder) {

        ResponseEntity<Integer> responseEntity = null;
        logger.info("Register review request is received.");
        logger.debug("Register review request" + review.toString());

        System.out.println(resourceId);

        Integer reviewId = reviewApi.createReview(AccessTokenUtil.getBearerToken("authorization"),
                review,resourceId);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("reviews/{reviewId}").buildAndExpand(reviewId).toUri());

        responseEntity = new ResponseEntity<Integer>(reviewId, headers, HttpStatus.CREATED);
        return responseEntity;
    }

    @ApiOperation(value="Get Review by ID",response = ReviewResponse.class,produces = "application/json")
    @GetMapping("{reviewId}")
    public ResponseEntity<ReviewResponse> getReview(
            //  @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            @PathVariable("reviewId") final Integer reviewId) {

        ResponseEntity<ReviewResponse> responseEntity = null;
        logger.info("Get review by id request is received.");
        ReviewResponse review = reviewApi.getReview(AccessTokenUtil.getBearerToken("authorization"), reviewId);

        responseEntity = new ResponseEntity<ReviewResponse>(review, HttpStatus.OK);
        return responseEntity;
    }


    @ApiOperation(value = "Update review by id.", response = CompanyResponse.class, produces = "application/json")
    @PutMapping("{reviewId}")
    public ResponseEntity<ReviewResponse> updateReview(
            //@ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            @PathVariable("reviewId") final Integer  reviewId, @RequestBody final ReviewRequest review) {

        ResponseEntity<ReviewResponse> responseEntity = null;
        logger.info("update review by id request is received.");
        ReviewResponse response = reviewApi.updateReview(reviewId,review);

        responseEntity = new ResponseEntity<ReviewResponse>(response, HttpStatus.OK);
        return responseEntity;
    }


    @ApiOperation(value="Delete Review by ID",response = ResourceResponse.class,produces = "application/json")
    @DeleteMapping("{reviewId}")
    public ResponseEntity<Boolean> deleteReview(
            //  @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            @PathVariable("reviewId") final Integer reviewId) {

        ResponseEntity<Boolean> responseEntity = null;
        logger.info("Delete review by id request is received.");
        Boolean result = reviewApi.deleteReview(AccessTokenUtil.getBearerToken("authorization"), reviewId);
        responseEntity = new ResponseEntity<Boolean>(result, HttpStatus.OK);
        return responseEntity;
    }









}
