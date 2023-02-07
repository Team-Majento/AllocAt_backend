package com.uom.seat.resource.repository;

import com.uom.seat.resource.entity.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ResourceRepository extends JpaRepository<ResourceEntity,Integer> {

    public ResourceEntity findByXid(String xid);


}
