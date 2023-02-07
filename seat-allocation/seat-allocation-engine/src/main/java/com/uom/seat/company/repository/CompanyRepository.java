package com.uom.seat.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uom.seat.company.entity.CompanyEntity;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {
	
	public CompanyEntity findByXid(String xid);

	public CompanyEntity findByEmail(String email);

}
