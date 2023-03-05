package com.uom.seat.resourceAllocatedCost.api.impl;

import com.uom.seat.api.ResourceAllocatedCostApi;
import com.uom.seat.api.ResourceAllocationApi;
import com.uom.seat.resourceAllocatedCost.logic.GenerateReportLogic;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.JMRuntimeException;
import java.io.FileNotFoundException;
import java.time.LocalDate;

@Service
public class ResourceAllocatedCostApiImpl implements ResourceAllocatedCostApi {

    @Autowired
    private GenerateReportLogic generateReportLogic;

    @Override
    public Integer generateReportCompanyWise(String authorization, Integer company_id, LocalDate from_date, LocalDate to_date) throws JMRuntimeException, FileNotFoundException, JRException {
        return generateReportLogic.generateReportCompanyWise(company_id, from_date, to_date);
    }

    @Override
    public Integer generateGeneralReport(String authorization, LocalDate from_date, LocalDate to_date) throws JMRuntimeException, FileNotFoundException, JRException, JRException {
        return generateReportLogic.generateGeneralReport(from_date, to_date);
    }

}