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

    List<BookingRequestResponse> getAllResourceBookingRequestsByRequestersId(String authorization, Integer requesterUserId);

    List<BookingRequestResponse> getAllResourceBookingRequestsByResourceId(String authorization, Integer resourceId);

    Integer getNumberOfRejectedBookingRequestAsNow(String authorization);

    Integer getAllNumberOfPendingBookingRequest(String authorization);

    List<BookingRequestResponse> getAllSubordinateResourceBookingRequests(String authorization, Integer rmId);

    Integer getNumberOfRejectedBookingRequestAsNowById(String authorization, Integer userId);

    Integer getAllNumberOfPendingBookingRequestById(String authorization, Integer userId);


    Integer getAllResourceBookingRequestCountByRequesterId(String authorization, Integer requesterUserId);
}
