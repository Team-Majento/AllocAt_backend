package com.uom.seat.resourceAllocatedCost.service;

import com.uom.seat.resourceAllocation.entity.ResourceAllocationEntity;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.time.LocalDate;

public interface ResourceAllocatedCostService {
    void createResourceAllocatedCost(ResourceAllocationEntity resourceAllocationEntity, Integer resource_id);

    Integer generateGeneralReport(LocalDate from_date, LocalDate to_date) throws JRException, FileNotFoundException;

    Integer generateReportCompanyWise(Integer company_id, LocalDate from_date, LocalDate to_date) throws JRException, FileNotFoundException;
}