package com.uom.seat.resourceAllocation.repository;

import com.uom.seat.resourceAllocation.entity.ResourceAllocationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ResourceAllocationRepository extends JpaRepository<ResourceAllocationEntity,Integer> {
    public Page<ResourceAllocationEntity> findAllByRequesterUserId(Integer RequesterUserId, Pageable pageable);

    @Query("select u from ResourceAllocationEntity u where u.companyId = ?1 AND u.requiredDate BETWEEN ?2 AND ?3")
    List<ResourceAllocationEntity> findAllResourceAllocationsByCompanyIdAndDates(Integer company_id, LocalDate startDate, LocalDate endDate);

    @Query("select u from ResourceAllocationEntity u where u.requiredDate BETWEEN ?1 AND ?2 order by  u.companyId")
    List<ResourceAllocationEntity> findAllResourceAllocationsByDatesPlusSortByCompantID(LocalDate from_date, LocalDate to_date);
}
