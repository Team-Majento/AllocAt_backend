package com.uom.seat.bookingRequest.service.impl;

import com.uom.seat.bookingRequest.dto.BookingRequestRequest;
import com.uom.seat.bookingRequest.dto.BookingRequestResponse;
import com.uom.seat.bookingRequest.entity.BookingRequestEntity;
import com.uom.seat.bookingRequest.repository.BookingRequestRepository;
import com.uom.seat.bookingRequest.service.BookingRequestService;
import com.uom.seat.company.dto.CompanyResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class BookingRequestServiceImpl implements BookingRequestService {

    @Autowired
    private BookingRequestRepository bookingRequestRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Integer createBookingRequest(BookingRequestRequest bookingRequest) {
        List<BookingRequestEntity> reqList =bookingRequestRepository.findByResourceAndDate(bookingRequest.getResourceId(),bookingRequest.getRequiredDate());
      boolean isAvailable=true;
        boolean isBookable = true;
        for (BookingRequestEntity old : reqList) {
            if ((bookingRequest.getEndTime().isBefore(old.getStartTime()) || bookingRequest.getEndTime().equals(old.getStartTime()))) {
                continue;
            }
            if ((bookingRequest.getStartTime().isAfter(old.getEndTime()) || bookingRequest.getStartTime().equals(old.getEndTime()))) {
                continue;
            }
            if ((old.getStartTime().isBefore(bookingRequest.getEndTime()) || old.getStartTime().equals(bookingRequest.getEndTime()))
                    && (bookingRequest.getEndTime().isBefore(old.getEndTime()) || bookingRequest.getEndTime().equals(old.getEndTime()))) {
                isBookable = false;
                break;
            }
            if ((old.getStartTime().isBefore(bookingRequest.getStartTime()) || old.getStartTime().equals(bookingRequest.getStartTime()))
                    && (bookingRequest.getStartTime().isBefore(old.getEndTime()) || bookingRequest.getStartTime().equals(old.getEndTime()))) {
                isBookable = false;
                break;
            }
            if ((bookingRequest.getStartTime().isBefore(old.getStartTime()) || bookingRequest.getStartTime().equals(old.getStartTime()))
                    && (old.getEndTime().isBefore(bookingRequest.getEndTime()) || old.getEndTime().equals(bookingRequest.getEndTime()))) {
                isBookable = false;
                break;
            }
        }

        if (!isBookable) {

            return -1;
        }

//            boolean isBookable = true;
//            for (int i = 0; i < reqList.size(); i++) {
//                BookingRequestEntity old = reqList.get(i);
//                if (bookingRequest.getEndTime().isBefore(old.getStartTime())) {
//                    continue;
//                }
//                if (bookingRequest.getStartTime().isAfter(old.getEndTime())) {
//                    continue;
//                }
//                if (old.getStartTime().isBefore(bookingRequest.getEndTime()) && bookingRequest.getEndTime().isBefore(old.getEndTime())) {
//                    isBookable = false;
//                }
//                if (old.getStartTime().isBefore(bookingRequest.getStartTime()) && bookingRequest.getStartTime().isBefore(old.getEndTime())) {
//                    isBookable = false;
//                }
//                if (bookingRequest.getStartTime().isBefore(old.getStartTime()) && old.getEndTime().isBefore(bookingRequest.getEndTime())) {
//                    isBookable = false;
//
//                }
//
//                if (!isBookable) {
//                    return -1;
//                }

//            }


        return bookingRequestRepository.save(convertToBookingRequestEntity(bookingRequest)).getId();
    }

    @Override
    public BookingRequestResponse getBookingRequest(Integer bookingRequestId) {
        return convertToBookingRequestResponse(bookingRequestRepository.findById(bookingRequestId).get());
    }

    @Override
    public BookingRequestResponse updateResource(Integer bookingRequestId, BookingRequestRequest bookingRequest) {
        BookingRequestEntity entity = bookingRequestRepository.saveAndFlush(updateBookingRequestEntity(bookingRequestRepository.findById(bookingRequestId).get(), bookingRequest));
        return convertToBookingRequestResponse(entity);
    }

    private BookingRequestEntity updateBookingRequestEntity(BookingRequestEntity bookingRequestEntity, BookingRequestRequest bookingRequest) {
                 bookingRequestEntity.setStartTime(bookingRequest.getStartTime());
                bookingRequestEntity.setEndTime(bookingRequest.getEndTime());
                bookingRequestEntity.setRequiredDate(bookingRequest.getRequiredDate());

        return  bookingRequestEntity;
    }

    private BookingRequestResponse convertToBookingRequestResponse(BookingRequestEntity bookingRequestEntity) {

        BookingRequestResponse dto = null;
        dto = modelMapper.map(bookingRequestEntity, BookingRequestResponse.class);
        return dto;

    }


    private BookingRequestEntity convertToBookingRequestEntity(BookingRequestRequest bookingRequest) {

        BookingRequestEntity entity = null;
        entity = modelMapper.map(bookingRequest, BookingRequestEntity.class);
       // entity.setXid(UUID.randomUUID().toString());

        return entity;
    }

    @Override
    public List<BookingRequestResponse> getALlBookingRequests() {
        return convertToBookingRequestResponseList(bookingRequestRepository.findAll());
    }

    private List<BookingRequestResponse> convertToBookingRequestResponseList(List<BookingRequestEntity> entityList) {
        int val=0;
        List<BookingRequestResponse> dtoList=new ArrayList<>();
        while(entityList.size()>val) {
            BookingRequestResponse dto=null;
            dto=modelMapper.map(entityList.get(val), BookingRequestResponse.class);
            dtoList.add(dto);
            val++;
        }
        return dtoList;
    }
    @Override
    public List<BookingRequestResponse> getALlResourceBookingRequestsByRequstersUserId(Integer requesterUserId) {
        //PageRequest pageable= PageRequest.of(page,size);
        List<BookingRequestEntity> pageEntities=bookingRequestRepository.findAllByRequesterUserId(requesterUserId);

        List<BookingRequestEntity> entityList= pageEntities;
        List<BookingRequestResponse> dtoList = new ArrayList<BookingRequestResponse>();

        entityList.forEach(entity -> dtoList.add(convertToBookingRequestResponse(entity)));

        return dtoList;
    }

    @Override
    public List<BookingRequestResponse> getALlBookingRequestsByResourceId(String authorization, Integer resourceId) {
        List<BookingRequestResponse> requestResponseList=new ArrayList<>();
        int k=bookingRequestRepository.findAllByResourceId(resourceId).size();
        List<BookingRequestEntity> list =bookingRequestRepository.findAllByResourceId(resourceId);
        for (int i=0;i<k;i++){
            BookingRequestResponse requestResponse=convertToBookingRequestResponse(list.get(i));
            requestResponseList.add(requestResponse);
        }
        return requestResponseList;

    }

    @Override
    public Integer getNumberOfRejectedBookingRequestAsNow() {
        return bookingRequestRepository.getNumberOfRejectedBookingRequestAsNow();
    }

    @Override
    public Integer getAllNumberOfPendingBookingRequest() {
        return bookingRequestRepository.getAllNumberOfPendingBookingRequest();
    }

    @Override
    public Integer getNumberOfRejectedBookingRequestAsNowById(Integer userId) {
        return bookingRequestRepository.getNumberOfRejectedBookingRequestAsNowById(userId);
    }

    @Override
    public Integer getAllNumberOfPendingBookingRequestById(Integer userId) {
        return bookingRequestRepository.getAllNumberOfPendingBookingRequestById( userId);
    }

    @Override
    public List<BookingRequestResponse> getAllSubordinateResourceBookingRequests(Integer rmId) {
       // List<BookingRequestResponse> requestResponseList=new ArrayList<>();
        List<BookingRequestEntity> entityList =  bookingRequestRepository.getAllSubordinateResourceBookingRequests(rmId);
        List<BookingRequestResponse> dtoList = new ArrayList<BookingRequestResponse>();

        entityList.forEach(entity -> dtoList.add(convertToBookingRequestResponse(entity)));

        return dtoList;
    }

    @Override
    public Integer getAllResourceBookingRequestCountByRequesterId(Integer requesterUserId) {
        return bookingRequestRepository.getAllResourceBookingRequestCountByRequesterId(requesterUserId);
    }
}
