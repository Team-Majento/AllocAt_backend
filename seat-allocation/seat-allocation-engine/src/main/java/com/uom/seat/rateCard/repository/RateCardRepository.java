package com.uom.seat.rateCard.repository;

import com.uom.seat.rateCard.entity.RateCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RateCardRepository extends JpaRepository<RateCardEntity,Integer> {
    /*@Query("select u from RateCardEntity u where u.resource_id = ?1")
    RateCardEntity findRateCardEntityByResource_id(Integer resource_id);*/
}
