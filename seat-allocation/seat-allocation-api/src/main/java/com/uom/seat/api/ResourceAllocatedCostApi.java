package com.uom.seat.api;


import net.sf.jasperreports.engine.JRException;

import javax.management.JMRuntimeException;
import java.io.FileNotFoundException;
import java.time.LocalDate;

public interface ResourceAllocatedCostApi {
    Integer generateReportCompanyWise(String authorization, Integer company_id, LocalDate from_date, LocalDate to_date) throws JMRuntimeException, FileNotFoundException, JRException, net.sf.jasperreports.engine.JRException;

    Integer generateGeneralReport(String authorization, LocalDate from_date, LocalDate to_date) throws JMRuntimeException, FileNotFoundException, JRException, net.sf.jasperreports.engine.JRException;


}