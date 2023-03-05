package com.uom.seat.condition.repository;

import com.uom.seat.condition.entity.ConditionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionRepository extends JpaRepository<ConditionEntity,Integer> {
}
