package com.uom.seat.resourceAllocation.repository;

import com.uom.seat.resourceAllocation.dto.ResourceAllocationResponse;
import com.uom.seat.resourceAllocation.entity.ResourceAllocationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ResourceAllocationRepository extends JpaRepository<ResourceAllocationEntity,Integer> {
    public Page<ResourceAllocationEntity> findAllByRequesterUserId(Integer RequesterUserId, Pageable pageable);

    @Query("select u from ResourceAllocationEntity u where u.companyId = ?1 AND u.requiredDate BETWEEN ?2 AND ?3")
    List<ResourceAllocationEntity> findAllResourceAllocationsByCompanyIdAndDates(Integer company_id, LocalDate startDate, LocalDate endDate);

    @Query("select u from ResourceAllocationEntity u where u.requiredDate BETWEEN ?1 AND ?2 order by  u.companyId")
    List<ResourceAllocationEntity> findAllResourceAllocationsByDatesPlusSortByCompantID(LocalDate from_date, LocalDate to_date);

    @Query("select u from ResourceAllocationEntity u where u.resourceId =?1  ")
    List<ResourceAllocationEntity> getAllResourceAllocationsByResourceId(Integer resourceId);

    @Query("SELECT COUNT(u) FROM ResourceAllocationEntity u WHERE MONTH(u.requiredDate) = MONTH(CURRENT_DATE())")
    Integer getAllResourceAllocationsByCurrentMonth();

    @Query("SELECT SEC_TO_TIME(SUM(TIME_TO_SEC(TIMEDIFF(u.endTime, u.startTime)))) FROM ResourceAllocationEntity u WHERE u.requesterUserId = ?1")
    LocalTime getTotalAllocationHoursUserWise(Integer userId);

    @Query("SELECT u FROM ResourceAllocationEntity u WHERE u.requiredDate=CURRENT_DATE AND ( HOUR(CURRENT_TIMESTAMP) BETWEEN u.startTime AND u.endTime)")
    List<ResourceAllocationEntity> getAllCurrentOngoingAllocations();
    @Query("SELECT u.companyId FROM ResourceAllocationEntity u")
    List<Integer> getAllCompanyIdOfTheResourceAllocation();
    @Query("SELECT COUNT(u) FROM ResourceAllocationEntity u WHERE u.startTime <= :currentTime AND u.endTime >= :currentTime")
    Integer getAllCurrentOngoingAllocationCount(LocalTime currentTime);
}
