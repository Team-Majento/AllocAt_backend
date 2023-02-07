package com.uom.seat.bookingRequest.logic;

import com.uom.seat.bookingRequest.dto.BookingRequestResponse;
import com.uom.seat.bookingRequest.service.BookingRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BookingRequestRetrievalLogic {


    @Autowired
    private BookingRequestService service;

    public BookingRequestResponse getBookingRequest(String bearerToken, Integer bookingRequestId) {
        return service.getBookingRequest(bookingRequestId);
    }


    private void validateServiceParam(Integer id) {
        //TODO
        //ValidationUtil.validateNotEmpty(id, "The id is mandatory");
    }



}
