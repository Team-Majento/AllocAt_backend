package com.uom.seat.resource.repository;

import com.uom.seat.company.entity.CompanyEntity;
import com.uom.seat.resource.entity.ResourceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ResourceRepository extends JpaRepository<ResourceEntity,Integer> {

    public ResourceEntity findByXid(String xid);

    @Query(
            value = "SELECT * FROM Resource u WHERE u.company_id= ?1",
            nativeQuery = true)
    Page<ResourceEntity> findByCompany(Integer company, PageRequest pageable);
}
