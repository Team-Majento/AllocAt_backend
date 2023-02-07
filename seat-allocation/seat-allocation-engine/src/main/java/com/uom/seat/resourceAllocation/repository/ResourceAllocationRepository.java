package com.uom.seat.resourceAllocation.repository;

import com.uom.seat.resourceAllocation.entity.ResourceAllocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceAllocationRepository extends JpaRepository<ResourceAllocationEntity,Integer> {
    public ResourceAllocationEntity findByRequesterUserId(Integer RequesterUserId);

}
