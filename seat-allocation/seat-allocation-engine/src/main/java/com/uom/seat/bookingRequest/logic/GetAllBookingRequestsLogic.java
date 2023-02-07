package com.uom.seat.bookingRequest.logic;

import com.uom.seat.bookingRequest.dto.BookingRequestResponse;
import com.uom.seat.bookingRequest.service.BookingRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllBookingRequestsLogic {
    @Autowired
    private BookingRequestService bookingRequestService;

    public List<BookingRequestResponse> getAllBookingRequests(String bearerToken) {
        return bookingRequestService.getALlBookingRequests();
    }
}
