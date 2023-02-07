package com.uom.seat.bookingRequest.validator;

import com.uom.seat.bookingRequest.dto.BookingRequestRequest;

import org.springframework.stereotype.Component;


@Component
public class BookingRequestValidator {
    public void validateBookingRequest(BookingRequestRequest bookingRequest) {
        validateMandotoryFields(bookingRequest);
        validateOptionalFields(bookingRequest);
    }
    private void validateMandotoryFields(BookingRequestRequest bookingRequest) {
        // TODO

    }

    private void validateOptionalFields(BookingRequestRequest bookingRequest) {
        // TODO
    }



}

