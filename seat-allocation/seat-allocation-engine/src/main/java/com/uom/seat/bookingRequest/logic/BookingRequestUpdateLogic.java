package com.uom.seat.bookingRequest.logic;


import com.uom.seat.bookingRequest.dto.BookingRequestRequest;
import com.uom.seat.bookingRequest.dto.BookingRequestResponse;
import com.uom.seat.bookingRequest.service.BookingRequestService;
import com.uom.seat.bookingRequest.validator.BookingRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingRequestUpdateLogic {

    @Autowired
    private BookingRequestValidator bookingRequestValidator;

    @Autowired
    private BookingRequestService bookingRequestService;


    public BookingRequestResponse updateBookingRequest(Integer bookingRequestId, BookingRequestRequest bookingRequest) {

        bookingRequestValidator.validateBookingRequest(bookingRequest);

        return bookingRequestService.updateResource(bookingRequestId,bookingRequest);


    }




}
