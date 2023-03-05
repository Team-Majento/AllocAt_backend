package com.uom.seat.resourceAllocatedCost.repository;

import com.uom.seat.resourceAllocatedCost.entity.ResourceAllocatedCostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceAllocatedCostRepository extends JpaRepository<ResourceAllocatedCostEntity,Integer> {

}
