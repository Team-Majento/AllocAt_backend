package com.uom.seat.server.rest.controller;


import com.uom.seat.api.BookingRequestApi;
import com.uom.seat.bookingRequest.dto.BookingRequestRequest;
import com.uom.seat.bookingRequest.dto.BookingRequestResponse;
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

import java.util.List;

@RestController
@RequestMapping("/bookingRequests")
@Api(description = "The bookingRequest API for bookingRequest management tasks")
public class BookingRequestController {

    private static final Logger logger = Logger.getLogger(BookingRequestController.class);

    @Autowired
    private BookingRequestApi bookingRequestApi;


    @ApiOperation(value = "Create booking request.", response = Integer.class, consumes = "application/json")
    @ApiResponses(value = {
            // @formatter:off
            @ApiResponse(code = 201, message = "The booking request is created and return booking request id."),
            @ApiResponse(code = 400, message = "Invalid API argument.")
            // @formatter:on
    })
    @PostMapping()
    public ResponseEntity<Integer> registerBookingRequest(
           // @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            @ApiParam(value = "JSON format of the company request.", required = true) @RequestBody final BookingRequestRequest bookingRequest,
            UriComponentsBuilder builder) {

        ResponseEntity<Integer> responseEntity = null;
        logger.info("Create bookingRequest request is received.");
        logger.debug("Create bookingRequest request" + bookingRequest.toString());

        Integer bookingRequestId = bookingRequestApi.createBookingRequest(AccessTokenUtil.getBearerToken("authorization"),
                bookingRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("bookingRequests/{bookingRequestId}").buildAndExpand(bookingRequestId).toUri());

        responseEntity = new ResponseEntity<Integer>(bookingRequestId, headers, HttpStatus.CREATED);
        return responseEntity;
    }


    @ApiOperation(value = "get booking-request by id.", response = BookingRequestResponse.class, produces = "application/json")
    @GetMapping("{bookingRequestId}")
    public ResponseEntity<BookingRequestResponse> getBookingRequest(
            //@ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            @PathVariable("bookingRequestId") final Integer bookingRequestId) {

        ResponseEntity<BookingRequestResponse> responseEntity = null;
        logger.info("Get booking-request by id request is received.");
        BookingRequestResponse bookingRequest =bookingRequestApi.getBookingRequest(AccessTokenUtil.getBearerToken("authorization"),bookingRequestId);

        responseEntity = new ResponseEntity<BookingRequestResponse>(bookingRequest, HttpStatus.OK);
        return responseEntity;
    }


    @ApiOperation(value = "Update BookingRequest by id.", response = BookingRequestResponse.class, produces = "application/json")
    @PutMapping("{bookingRequestId}")
    public ResponseEntity<BookingRequestResponse>updateBookingRequest(   
                                                           //@ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
                                                           @PathVariable("bookingRequestId") final Integer bookingRequestId, @RequestBody final BookingRequestRequest bookingRequest) {

        ResponseEntity<BookingRequestResponse> responseEntity = null;
        logger.info("Get bookingRequestId by id request is received.");
        BookingRequestResponse response = bookingRequestApi.updateBookingRequest(bookingRequestId, bookingRequest);

        responseEntity = new ResponseEntity<BookingRequestResponse>(response, HttpStatus.OK);
        return responseEntity;
    }

    @ApiOperation(value = "get all the booking requests ", response = BookingRequestResponse.class, produces = "application/json")
    @GetMapping()
    public ResponseEntity<List<BookingRequestResponse>> getAllBookingRequests(
            //@ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization
    )
            {

        ResponseEntity<List<BookingRequestResponse>> responseEntity = null;
        logger.info("get all the resource allocations request is received.");
        List<BookingRequestResponse> listOfResourceAllocations = bookingRequestApi.getAllBookingRequests(AccessTokenUtil.getBearerToken("authorization"));

        responseEntity = new ResponseEntity<List<BookingRequestResponse>>(listOfResourceAllocations, HttpStatus.OK);
        return responseEntity;
    }

    @ApiOperation(value="Get all resource booking requests by requesterUserId ",response = BookingRequestResponse.class,produces = "application/json")
    @GetMapping("resource-booking-request-page/{requesterUserId}")
    public ResponseEntity<Page<BookingRequestResponse>> getAllResourceBookingRequestsByRequesterId(
            //  @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            @RequestParam(name = "page" ,defaultValue ="0" ,required = false)Integer page,
            @RequestParam(name = "size" ,defaultValue ="10" ,required = false)Integer size,
            @PathVariable("requesterUserId") final Integer requesterUserId
            //@RequestParam(value = "sortBy" ,required = false) ResourceSortFiled sortBy,
            // @RequestParam(name = "direction" ,required = false) Sort.Direction direction
    ){
        ResponseEntity<Page<BookingRequestResponse>> responseEntity = null;
        Page<BookingRequestResponse> pageDtos = bookingRequestApi.getAllResourceBookingRequestsByRequestersId( AccessTokenUtil.getBearerToken("authorization"),page,size,requesterUserId);
        responseEntity=new ResponseEntity<Page<BookingRequestResponse>>(pageDtos,HttpStatus.OK);
        return responseEntity;
    }


}