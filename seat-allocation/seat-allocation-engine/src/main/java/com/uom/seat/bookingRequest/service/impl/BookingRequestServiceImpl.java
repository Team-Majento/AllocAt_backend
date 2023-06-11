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
    public Page<BookingRequestResponse> getALlResourceBookingRequestsByRequstersUserId(Integer requesterUserId, Integer page, Integer size) {
        PageRequest pageable= PageRequest.of(page,size);
        Page<BookingRequestEntity> pageEntities=bookingRequestRepository.findAllByRequesterUserId(requesterUserId,pageable);

        List<BookingRequestEntity> entityList= pageEntities.getContent();
        List<BookingRequestResponse> dtoList = new ArrayList<BookingRequestResponse>();

        entityList.forEach(entity -> dtoList.add(convertToBookingRequestResponse(entity)));

        return new PageImpl<BookingRequestResponse>(dtoList,pageable,pageEntities.getTotalElements());
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
}
