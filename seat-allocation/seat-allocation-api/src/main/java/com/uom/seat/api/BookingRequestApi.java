package com.uom.seat.api;

import com.uom.seat.bookingRequest.dto.BookingRequestRequest;
import com.uom.seat.bookingRequest.dto.BookingRequestResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookingRequestApi {
    Integer createBookingRequest(String bearerToken, BookingRequestRequest bookingRequest);

    BookingRequestResponse getBookingRequest(String bearerToken, Integer bookingRequestId);

    BookingRequestResponse updateBookingRequest(Integer bookingRequestId, BookingRequestRequest bookingRequest);

    List<BookingRequestResponse> getAllBookingRequests(String bearerToken);

    Page<BookingRequestResponse> getAllResourceBookingRequestsByRequestersId(String authorization, Integer page, Integer size, Integer requesterUserId);

    List<BookingRequestResponse> getAllResourceBookingRequestsByResourceId(String authorization, Integer resourceId);

    Integer getNumberOfRejectedBookingRequestAsNow(String authorization);

    Integer getAllNumberOfPendingBookingRequest(String authorization);
}
