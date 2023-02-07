package com.uom.seat.bookingRequest.logic;
import com.uom.seat.bookingRequest.service.BookingRequestService;
import org.apache.log4j.Logger;
import com.uom.seat.bookingRequest.validator.BookingRequestValidator;
import com.uom.seat.bookingRequest.dto.BookingRequestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BookingRequestCreationLogic {

    private static final Logger logger = Logger.getLogger(BookingRequestCreationLogic.class);
    @Autowired
    private BookingRequestValidator bookingRequestValidator;


    @Autowired
    private BookingRequestService bookingRequestService;

    public Integer createBookingRequest(String bearerToken, BookingRequestRequest bookingRequest) {

        // 1. validate bookingRequest request
        // 2. create company

        bookingRequestValidator.validateBookingRequest(bookingRequest);
        logger.info("The bookingRequest is validated.");

        Integer id = bookingRequestService.createBookingRequest(bookingRequest);
        logger.info("The bookingRequest is created.");

        return id;

    }
}
