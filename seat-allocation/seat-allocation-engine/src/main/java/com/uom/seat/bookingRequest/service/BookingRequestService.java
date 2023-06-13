package com.uom.seat.bookingRequest.service;

import com.uom.seat.bookingRequest.dto.BookingRequestRequest;
import com.uom.seat.bookingRequest.dto.BookingRequestResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookingRequestService {
    Integer createBookingRequest(BookingRequestRequest bookingRequest);

    BookingRequestResponse getBookingRequest(Integer bookingRequestId);

    BookingRequestResponse updateResource(Integer bookingRequestId, BookingRequestRequest bookingRequest);

    List<BookingRequestResponse> getALlBookingRequests();

    List<BookingRequestResponse> getALlResourceBookingRequestsByRequstersUserId(Integer requesterUserId);


    List<BookingRequestResponse> getALlBookingRequestsByResourceId(String authorization, Integer resourceId);

    Integer getNumberOfRejectedBookingRequestAsNow();

    Integer getAllNumberOfPendingBookingRequest();

    List<BookingRequestResponse> getAllSubordinateResourceBookingRequests(Integer rmId);

    Integer getNumberOfRejectedBookingRequestAsNowById(Integer userId);

    Integer getAllNumberOfPendingBookingRequestById(Integer userId);

    Integer getAllResourceBookingRequestCountByRequesterId(Integer requesterUserId);
}
