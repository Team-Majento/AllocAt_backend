package com.uom.seat.resourceAllocatedCost.logic;


import com.uom.seat.resourceAllocatedCost.service.ResourceAllocatedCostService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.time.LocalDate;

@Component
public class GenerateReportLogic {

    @Autowired
    private ResourceAllocatedCostService resourceAllocatedCostService;
    public Integer generateReportCompanyWise(Integer company_id, LocalDate from_date, LocalDate to_date) throws JRException, FileNotFoundException {
        resourceAllocatedCostService.generateReportCompanyWise(company_id,from_date,to_date);
        return 200;
    }

    public Integer generateGeneralReport(LocalDate from_date, LocalDate to_date) throws JRException, FileNotFoundException {
        resourceAllocatedCostService.generateGeneralReport(from_date,to_date);
        return 200;
    }
}
