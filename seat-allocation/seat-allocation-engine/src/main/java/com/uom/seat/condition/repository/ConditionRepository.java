package com.uom.seat.condition.repository;

import com.uom.seat.condition.entity.ConditionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionRepository extends JpaRepository<ConditionEntity,Integer> {
//    @Query("select u.condition_name from ConditionEntity u")
//    List<ConditionResponse> getConditionNames();

    @Query("select u from ConditionEntity u where u.condition_name=?1")
    ConditionEntity findConditionByName(String conditionName);
}
