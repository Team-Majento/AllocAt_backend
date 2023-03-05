package com.uom.seat.report.repository;

import com.uom.seat.report.entity.CompanyWiseReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyWiseReportRepository extends JpaRepository<CompanyWiseReport,Integer> {
}
