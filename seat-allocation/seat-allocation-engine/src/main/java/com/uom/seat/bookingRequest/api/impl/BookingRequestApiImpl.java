package com.uom.seat.bookingRequest.api.impl;

import com.uom.seat.api.BookingRequestApi;
import com.uom.seat.bookingRequest.dto.BookingRequestRequest;
import com.uom.seat.bookingRequest.dto.BookingRequestResponse;
import com.uom.seat.bookingRequest.logic.BookingRequestCreationLogic;
import com.uom.seat.bookingRequest.logic.BookingRequestRetrievalLogic;
import com.uom.seat.bookingRequest.logic.BookingRequestUpdateLogic;
import com.uom.seat.bookingRequest.logic.GetAllBookingRequestsLogic;
import com.uom.seat.bookingRequest.service.BookingRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class BookingRequestApiImpl implements BookingRequestApi {
    @Autowired
    private BookingRequestCreationLogic bookingRequestCreationLogic;

    @Autowired
    private BookingRequestService bookingRequestService;

    @Autowired
    private BookingRequestRetrievalLogic bookingRequestRetrievalLogic;

    @Autowired
    private BookingRequestUpdateLogic bookingRequestUpdateLogic;

    @Autowired
    private GetAllBookingRequestsLogic getAllBookingRequestsLogic;
    @Override
    public Integer createBookingRequest(String bearerToken, BookingRequestRequest bookingRequest) {
        return bookingRequestCreationLogic.createBookingRequest(bearerToken, bookingRequest);

    }

    @Override
    public BookingRequestResponse getBookingRequest(String bearerToken, Integer bookingRequestId) {
        return bookingRequestRetrievalLogic.getBookingRequest(bearerToken, bookingRequestId);
    }

    @Override
    public BookingRequestResponse updateBookingRequest(Integer bookingRequestId, BookingRequestRequest bookingRequest) {
        return bookingRequestUpdateLogic.updateBookingRequest(bookingRequestId,bookingRequest);
    }

    @Override
    public List<BookingRequestResponse> getAllBookingRequests(String bearerToken) {
        return getAllBookingRequestsLogic.getAllBookingRequests(bearerToken);

    }
    @Override
    public Page<BookingRequestResponse> getAllResourceBookingRequestsByRequestersId(String authorization, Integer page, Integer size, Integer requesterUserId) {
        return bookingRequestRetrievalLogic.getAllResourceBookingRequestsByRequesterUserId(requesterUserId,page,size);

    }

    @Override
    public List<BookingRequestResponse> getAllResourceBookingRequestsByResourceId(String authorization, Integer resourceId) {
        return bookingRequestService.getALlBookingRequestsByResourceId(authorization,resourceId);

    }

    @Override
    public Integer getNumberOfRejectedBookingRequestAsNow(String authorization) {
        return bookingRequestService.getNumberOfRejectedBookingRequestAsNow();
    }

    @Override
    public Integer getAllNumberOfPendingBookingRequest(String authorization) {

        return  bookingRequestService.getAllNumberOfPendingBookingRequest();
    }
}

