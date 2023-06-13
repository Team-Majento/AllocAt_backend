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

    @Query(value = "SELECT * FROM Resource u WHERE u.company_id= ?1", nativeQuery = true)
    Page<ResourceEntity> findByCompany(Integer company, PageRequest pageable);
    @Query(value = "SELECT * FROM Resource u WHERE u.is_active=true", nativeQuery = true)
    Page<ResourceEntity> findAllActiveResource(PageRequest pageable);

    @Query("SELECT COUNT(u) FROM ResourceEntity u WHERE u.company.id=?1 AND u.activeStatus=true")
    Integer getAllResourcesBelongToCompanyId1(Integer companyId);

    @Query("SELECT COUNT(u) FROM ResourceEntity u WHERE u.company.id=?1 AND u.activeStatus=true")
    Integer getAllResourcesBelongToCompanyId2(int i);
    @Query("SELECT COUNT(u) FROM ResourceEntity u WHERE u.company.id=?1 AND u.activeStatus=true")
    Integer getAllResourcesBelongToCompanyId3(int i);

    @Query("SELECT COUNT(u) FROM ResourceEntity u WHERE u.company.id=?1 AND u.activeStatus=true")
    Integer getAllResourcesBelongToCompanyId4(int i);

    @Query("SELECT COUNT(u) FROM ResourceEntity u WHERE u.company.id=?1 AND u.activeStatus=true")
    Integer getAllResourcesBelongToCompanyId5(int i);
    @Query("SELECT COUNT(u) FROM ResourceEntity u WHERE u.company.id=?1 AND u.activeStatus=true")
    Integer getAllResourcesBelongToCompanyId6(int i);
}
