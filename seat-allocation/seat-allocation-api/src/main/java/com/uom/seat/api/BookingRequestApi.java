package com.uom.seat.api;

import com.uom.seat.bookingRequest.dto.BookingRequestRequest;
import com.uom.seat.bookingRequest.dto.BookingRequestResponse;

import java.util.List;

public interface BookingRequestApi {
    Integer createBookingRequest(String bearerToken, BookingRequestRequest bookingRequest);

    BookingRequestResponse getBookingRequest(String bearerToken, Integer bookingRequestId);

    BookingRequestResponse updateBookingRequest(Integer bookingRequestId, BookingRequestRequest bookingRequest);

    List<BookingRequestResponse> getAllBookingRequests(String bearerToken);

}
