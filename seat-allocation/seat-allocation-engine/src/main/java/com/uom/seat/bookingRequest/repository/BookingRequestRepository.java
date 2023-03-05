package com.uom.seat.bookingRequest.repository;

import com.uom.seat.bookingRequest.entity.BookingRequestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookingRequestRepository extends JpaRepository<BookingRequestEntity, Integer> {
    Page<BookingRequestEntity> findAllByRequesterUserId(Integer requesterUserId, Pageable pageable);

}