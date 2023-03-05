package com.uom.seat.report.repository;

import com.uom.seat.report.entity.GeneralReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralReportRepository extends JpaRepository<GeneralReport,Integer> {
}
