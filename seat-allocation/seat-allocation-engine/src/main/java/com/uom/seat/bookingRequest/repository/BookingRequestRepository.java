package com.uom.seat.bookingRequest.repository;

import com.uom.seat.bookingRequest.entity.BookingRequestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookingRequestRepository extends JpaRepository<BookingRequestEntity, Integer> {
    Page<BookingRequestEntity> findAllByRequesterUserId(Integer requesterUserId, Pageable pageable);

    List<BookingRequestEntity> findAllByResourceId(int i);

    @Query("SELECT COUNT(u) FROM BookingRequestEntity u WHERE MONTH(u.requiredDate) = MONTH(CURRENT_DATE()) AND u.status='Rejected'")
    Integer getNumberOfRejectedBookingRequestAsNow();

    @Query("SELECT COUNT(u) FROM BookingRequestEntity u WHERE MONTH(u.requiredDate) = MONTH(CURRENT_DATE()) AND u.status='pending'")
    Integer getAllNumberOfPendingBookingRequest();
}
