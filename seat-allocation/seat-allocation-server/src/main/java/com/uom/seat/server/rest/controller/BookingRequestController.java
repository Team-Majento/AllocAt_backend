package com.uom.seat.server.rest.controller;


import com.uom.seat.api.BookingRequestApi;
import com.uom.seat.bookingRequest.dto.BookingRequestRequest;
import com.uom.seat.bookingRequest.dto.BookingRequestResponse;
import com.uom.seat.company.dto.CompanyResponse;
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
@CrossOrigin(origins = "http://localhost:4200")
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
    @GetMapping("resource-booking-request/all-booking-requests/{requesterUserId}")
    public ResponseEntity<List<BookingRequestResponse>> getAllResourceBookingRequestsByRequesterId(
            //  @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
         //   @RequestParam(name = "page" ,defaultValue ="0" ,required = false)Integer page,
         //   @RequestParam(name = "size" ,defaultValue ="10" ,required = false)Integer size,
            @PathVariable("requesterUserId") final Integer requesterUserId
            //@RequestParam(value = "sortBy" ,required = false) ResourceSortFiled sortBy,
            // @RequestParam(name = "direction" ,required = false) Sort.Direction direction
    ){
        ResponseEntity<List<BookingRequestResponse>> responseEntity = null;
        List<BookingRequestResponse> reqList = bookingRequestApi.getAllResourceBookingRequestsByRequestersId( AccessTokenUtil.getBearerToken("authorization"),requesterUserId);
        responseEntity=new ResponseEntity<List<BookingRequestResponse>>(reqList,HttpStatus.OK);
        return responseEntity;
    }




    @ApiOperation(value="Get all resource booking requests by resourceId ",response = BookingRequestResponse.class,produces = "application/json")
    @GetMapping("resource-booking-request/{resourceId}")
    public ResponseEntity<List<BookingRequestResponse>> getAllResourceBookingRequestsByResourceId(
            //  @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            @PathVariable("resourceId") final Integer resourceId
            //@RequestParam(value = "sortBy" ,required = false) ResourceSortFiled sortBy,
            // @RequestParam(name = "direction" ,required = false) Sort.Direction direction
    ){
        ResponseEntity<List<BookingRequestResponse>> responseEntity = null;
        List<BookingRequestResponse> requests = bookingRequestApi.getAllResourceBookingRequestsByResourceId( AccessTokenUtil.getBearerToken("authorization"),resourceId);
        responseEntity=new ResponseEntity<List<BookingRequestResponse>>(requests,HttpStatus.OK);
        return responseEntity;
    }

    @ApiOperation(value="Get number of rejected booking request",response = Integer.class,produces = "application/json")
    @GetMapping("resource-booking-request/num-of-rejected-booking-request")
    public ResponseEntity<Integer> getNumberOfRejectedBookingRequestAsNow(
            //  @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            //@RequestParam(value = "sortBy" ,required = false) ResourceSortFiled sortBy,
            // @RequestParam(name = "direction" ,required = false) Sort.Direction direction
    ){
        ResponseEntity<Integer> responseEntity = null;
        Integer requests = bookingRequestApi.getNumberOfRejectedBookingRequestAsNow( AccessTokenUtil.getBearerToken("authorization"));
        responseEntity=new ResponseEntity<Integer>(requests,HttpStatus.OK);
        return responseEntity;
    }


    @ApiOperation(value="Get number of  pending booking requests as now",response =Integer.class,produces = "application/json")
    @GetMapping("resource-booking-request/num-of-pending-booking-request")
    public ResponseEntity<Integer> getAllNumberOfPendingBookingRequest(
            //  @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            //@PathVariable("resourceId") final Integer resourceId
            //@RequestParam(value = "sortBy" ,required = false) ResourceSortFiled sortBy,
            // @RequestParam(name = "direction" ,required = false) Sort.Direction direction
    ){
        ResponseEntity<Integer> responseEntity = null;
       Integer numOfPendingRequest = bookingRequestApi.getAllNumberOfPendingBookingRequest( AccessTokenUtil.getBearerToken("authorization"));
        responseEntity=new ResponseEntity<Integer>(numOfPendingRequest,HttpStatus.OK);
        return responseEntity;
    }



    @ApiOperation(value="Get number of rejected booking request",response = Integer.class,produces = "application/json")
    @GetMapping("resource-booking-request/num-of-rejected-booking-request/{userId}")
    public ResponseEntity<Integer> getNumberOfRejectedBookingRequestAsNowById(
            @PathVariable("userId") final Integer userId
            //  @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            //@RequestParam(value = "sortBy" ,required = false) ResourceSortFiled sortBy,
            // @RequestParam(name = "direction" ,required = false) Sort.Direction direction
    ){
        ResponseEntity<Integer> responseEntity = null;
        Integer requests = bookingRequestApi.getNumberOfRejectedBookingRequestAsNowById( AccessTokenUtil.getBearerToken("authorization"),userId);
        responseEntity=new ResponseEntity<Integer>(requests,HttpStatus.OK);
        return responseEntity;
    }


    @ApiOperation(value="Get number of  pending booking requests as now",response =Integer.class,produces = "application/json")
    @GetMapping("resource-booking-request/num-of-pending-booking-request/{userId}")
    public ResponseEntity<Integer> getAllNumberOfPendingBookingRequestById(
            @PathVariable("userId") final Integer userId
            //  @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            //@PathVariable("resourceId") final Integer resourceId
            //@RequestParam(value = "sortBy" ,required = false) ResourceSortFiled sortBy,
            // @RequestParam(name = "direction" ,required = false) Sort.Direction direction
    ){
        ResponseEntity<Integer> responseEntity = null;
        Integer numOfPendingRequest = bookingRequestApi.getAllNumberOfPendingBookingRequestById( AccessTokenUtil.getBearerToken("authorization"),userId);
        responseEntity=new ResponseEntity<Integer>(numOfPendingRequest,HttpStatus.OK);
        return responseEntity;
    }



    @ApiOperation(value="Get all subordinate booking req ",response = BookingRequestResponse.class,produces = "application/json")
    @GetMapping("resource-booking-request/all-sub-booking-requests/{rmId}")
    public ResponseEntity<List<BookingRequestResponse>> getAllSubordinateResourceBookingRequests(
            //  @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            //   @RequestParam(name = "page" ,defaultValue ="0" ,required = false)Integer page,
            //   @RequestParam(name = "size" ,defaultValue ="10" ,required = false)Integer size,
            @PathVariable("rmId") final Integer rmId
            //@RequestParam(value = "sortBy" ,required = false) ResourceSortFiled sortBy,
            // @RequestParam(name = "direction" ,required = false) Sort.Direction direction
    ){
        ResponseEntity<List<BookingRequestResponse>> responseEntity = null;
        List<BookingRequestResponse> reqList = bookingRequestApi.getAllSubordinateResourceBookingRequests( AccessTokenUtil.getBearerToken("authorization"),rmId);
        responseEntity=new ResponseEntity<List<BookingRequestResponse>>(reqList,HttpStatus.OK);
        return responseEntity;
    }








}
