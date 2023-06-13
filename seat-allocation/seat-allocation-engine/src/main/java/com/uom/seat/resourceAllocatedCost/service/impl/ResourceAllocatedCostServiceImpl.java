package com.uom.seat.resourceAllocatedCost.service.impl;

import com.uom.seat.company.repository.CompanyRepository;
import com.uom.seat.condition.entity.ConditionEntity;
import com.uom.seat.rateCard.entity.RateCardEntity;
import com.uom.seat.report.entity.CompanyWiseReport;
import com.uom.seat.report.entity.GeneralReport;
import com.uom.seat.report.repository.CompanyWiseReportRepository;
import com.uom.seat.report.repository.GeneralReportRepository;
import com.uom.seat.resource.entity.ResourceEntity;
import com.uom.seat.resource.repository.ResourceRepository;
import com.uom.seat.resourceAllocatedCost.entity.ResourceAllocatedCostEntity;
import com.uom.seat.resourceAllocatedCost.repository.ResourceAllocatedCostRepository;
import com.uom.seat.resourceAllocatedCost.service.ResourceAllocatedCostService;
import com.uom.seat.resourceAllocation.entity.ResourceAllocationEntity;
import com.uom.seat.resourceAllocation.repository.ResourceAllocationRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ResourceAllocatedCostServiceImpl implements ResourceAllocatedCostService {


    @Autowired
    private ResourceAllocatedCostRepository resourceAllocatedCostRepository;

    @Autowired
    private ResourceRepository resourceRepository;


    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ResourceAllocationRepository resourceAllocationRepository;

    @Autowired
    private CompanyWiseReportRepository companyWiseReportRepository;

    @Autowired
    private  GeneralReportRepository generalReportRepository;

    @Override
    public void createResourceAllocatedCost(ResourceAllocationEntity resourceAllocationEntity, Integer resource_id) {
        ResourceAllocatedCostEntity resourceAllocatedCostEntity = new ResourceAllocatedCostEntity();


        Double units;
        Double allocated_cost;
        Integer usage_in_minutes;
        ResourceEntity resourceEntity;
        Double discount;
        Double final_cost;
        Double discount_rate;
        resourceEntity = resourceRepository.findById(resource_id).get();
        RateCardEntity rateCardEntity = resourceEntity.getRateCardEntity();
        usage_in_minutes = Math.toIntExact(resourceAllocationEntity.getStartTime().until(resourceAllocationEntity.getEndTime(), ChronoUnit.MINUTES));


        units = Math.ceil((double) usage_in_minutes / rateCardEntity.getUnit());
        allocated_cost = (rateCardEntity.getHourRate() / 60) * rateCardEntity.getUnit() *  units;


        resourceAllocatedCostEntity.setCalculated_cost(Math.round(allocated_cost * 100.0) / 100.0);


        if (resourceAllocationEntity.getConditionEntity() != null) {

            ConditionEntity conditionEntity = resourceAllocationEntity.getConditionEntity();
            discount_rate = conditionEntity.getDiscount_rate();
            discount = discount_rate * allocated_cost / 100;
            final_cost = allocated_cost - discount;

            resourceAllocatedCostEntity.setFinal_cost(Math.round(final_cost * 100.0) / 100.0);
            resourceAllocatedCostEntity.setDiscount(discount);
            resourceAllocatedCostEntity.setDiscount_rate(discount_rate);

        } else {

            resourceAllocatedCostEntity.setFinal_cost(Math.round(allocated_cost * 100.0) / 100.0);
            resourceAllocatedCostEntity.setDiscount(0.0);
            resourceAllocatedCostEntity.setDiscount_rate(0.0);

        }

        resourceAllocatedCostEntity.setResourceAllocationEntity(resourceAllocationEntity);
        resourceAllocatedCostRepository.save(resourceAllocatedCostEntity);

    }

    @Override
    public Integer generateReportCompanyWise(Integer company_id, LocalDate from_date, LocalDate to_date) throws JRException, FileNotFoundException {
        List<ResourceAllocationEntity> resourceAllocationEntities = resourceAllocationRepository.findAllResourceAllocationsByCompanyIdAndDates(company_id, from_date, to_date);
        companyWiseReportRepository.deleteAll();

        Double total_cost_per_company = 0.0;
        String company_name = companyRepository.findById(company_id).get().getName();
        for (int i = 0; i < resourceAllocationEntities.size(); i++) {
            CompanyWiseReport reportEntity = new CompanyWiseReport();

            Integer resource_id;
            Integer requester_id;
            Double units;
            Double allocated_cost;
            Integer usage_in_minutes;
            Integer usage_in_hours;
            ResourceEntity resourceEntity;
            Double discount;
            Double final_cost;
            String usage_in_hours_and_minutes;
            Double discount_rate;
            LocalDate required_date;


            ResourceAllocationEntity resourceAllocationEntity = resourceAllocationEntities.get(i);
            resource_id = resourceAllocationEntity.getResourceId();
            ResourceAllocatedCostEntity resourceAllocatedCostEntity = resourceAllocationEntity.getResourceAllocatedCostEntity();
            resourceEntity = resourceRepository.findById(resource_id).get();


            requester_id = resourceAllocationEntity.getRequesterUserId();
            required_date = resourceAllocationEntity.getRequiredDate();
            usage_in_hours = Math.toIntExact(resourceAllocationEntity.getStartTime().until(resourceAllocationEntity.getEndTime(), ChronoUnit.HOURS));
            usage_in_minutes = Math.toIntExact(resourceAllocationEntity.getStartTime().until(resourceAllocationEntity.getEndTime(), ChronoUnit.MINUTES));

            RateCardEntity rateCardEntity = resourceEntity.getRateCardEntity();
            units = Math.ceil((double) usage_in_minutes / rateCardEntity.getUnit());


            usage_in_minutes=usage_in_minutes-usage_in_hours*60;
            usage_in_hours_and_minutes = usage_in_hours + " h " + usage_in_minutes + " min ";
            discount_rate = resourceAllocatedCostEntity.getDiscount_rate();
            discount = resourceAllocatedCostEntity.getDiscount();
            allocated_cost = resourceAllocatedCostEntity.getCalculated_cost();
            final_cost = resourceAllocatedCostEntity.getFinal_cost();

            total_cost_per_company += final_cost;





            reportEntity.setRequester_id(requester_id);
            reportEntity.setResource_id(resource_id);
            reportEntity.setRequired_date(required_date);
            reportEntity.setUsage_in_hours_and_minutes(usage_in_hours_and_minutes);
            reportEntity.setUnits(units);
            reportEntity.setAllocated_cost(allocated_cost);
            reportEntity.setDiscount_rate(discount_rate);
            reportEntity.setDiscount(discount);
            reportEntity.setFinal_cost(final_cost);
            reportEntity.setCompany_name(company_name);
            reportEntity.setTotal_allocated_cost(total_cost_per_company);

            companyWiseReportRepository.save(reportEntity);
        }


        exportReport();
        return 200;
    }


    @Override
    public Integer generateGeneralReport(LocalDate from_date, LocalDate to_date) throws JRException, FileNotFoundException {
        List<ResourceAllocationEntity> resourceAllocationEntities = resourceAllocationRepository.findAllResourceAllocationsByDatesPlusSortByCompantID(from_date, to_date);
        generalReportRepository.deleteAll();
        Double total_cost_per_company = 0.0;
        String old_company_name = companyRepository.findById(resourceAllocationEntities.get(0).getCompanyId()).get().getName();

        GeneralReport reportEntity=null;
        Double final_cost;
        Integer no_of_reservations=0;


        for (int i = 0; i < resourceAllocationEntities.size(); i++) {
            String current_company_name = companyRepository.findById(resourceAllocationEntities.get(i).getCompanyId()).get().getName();


            if (old_company_name != current_company_name) {

                  reportEntity.setTotal_cost_allocation(total_cost_per_company);
                  reportEntity.setCompany_id(resourceAllocationEntities.get(i - 1).getCompanyId());
                  reportEntity.setCompany_name(old_company_name);
                  reportEntity.setFrom_date(from_date);
                  reportEntity.setTo_date(to_date);
                  reportEntity.setNo_of_reservations(no_of_reservations);
                  total_cost_per_company = 0.0;
                  no_of_reservations=0;

                  generalReportRepository.save(reportEntity);
            }
            reportEntity = new GeneralReport();

            no_of_reservations+=1;
            ResourceAllocationEntity resourceAllocationEntity = resourceAllocationEntities.get(i);
            ResourceAllocatedCostEntity resourceAllocatedCostEntity = resourceAllocationEntity.getResourceAllocatedCostEntity();
            final_cost = resourceAllocatedCostEntity.getFinal_cost();
            total_cost_per_company += final_cost;
            old_company_name = current_company_name;

        }

        //To store the total allocated cost information of the last company record
        String last_company_name = companyRepository.findById(resourceAllocationEntities.get(resourceAllocationEntities.size() - 1).getCompanyId()).get().getName();
        reportEntity.setTotal_cost_allocation(total_cost_per_company);
        reportEntity.setCompany_id(resourceAllocationEntities.get(resourceAllocationEntities.size()- 1).getCompanyId());
        reportEntity.setCompany_name(last_company_name);
        reportEntity.setFrom_date(from_date);
        reportEntity.setTo_date(to_date);
        reportEntity.setNo_of_reservations(no_of_reservations);

        generalReportRepository.save(reportEntity);

        exportGeneralReport();
        return 200;
    }

//    public String exportReport() throws FileNotFoundException, JRException {
//        List<CompanyWiseReport> reportEntities = companyWiseReportRepository.findAll();
//        File file = ResourceUtils.getFile("classpath:company_wise_report1.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportEntities);
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("createdBy", "Java Techie");
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//        String computerUserName = "";
//        computerUserName = System.getProperty("user.name");
//
//        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\" + computerUserName + "\\Downloads\\company_wise_report.pdf");
//
//        return "Report Generated";
//    }
public String exportReport() throws FileNotFoundException, JRException {
    List<CompanyWiseReport> reportEntities = companyWiseReportRepository.findAll();
    File file = ResourceUtils.getFile("classpath:company_wise_report1.jrxml");
    JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportEntities);
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("createdBy", "Java Techie");
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
    String computerUserName = System.getProperty("user.name");

    int reportNumber = 0;
    String filename = "company_wise_report_"+reportEntities.get(0).getCompany_name()+".pdf";
    String path = "C:\\Users\\" + computerUserName + "\\Downloads\\" + filename;
    File reportFile = new File(path);

    while (reportFile.exists()) {
        reportNumber++;
        filename ="company_wise_report_"+reportEntities.get(0).getCompany_name()+"("+reportNumber+").pdf";;
        path = "C:\\Users\\" + computerUserName + "\\Downloads\\" + filename;
        reportFile = new File(path);
    }

    JasperExportManager.exportReportToPdfFile(jasperPrint, path);

    return "Report Generated: " + filename;
}

//    public String exportGeneralReport() throws FileNotFoundException, JRException {
//
//        List<GeneralReport> reportEntities = generalReportRepository.findAll();
//        File file = ResourceUtils.getFile("classpath:general_report_2.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportEntities);
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("generalReportDataset",dataSource);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
//        String computerUserName = "";
//        computerUserName = System.getProperty("user.name");
//
//        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\" + computerUserName + "\\Downloads\\general_report.pdf");
//
//        return "Report Generated";
//    }

    public String exportGeneralReport() throws FileNotFoundException, JRException {
        List<GeneralReport> reportEntities = generalReportRepository.findAll();
        File file = ResourceUtils.getFile("classpath:general_report_3.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportEntities);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("generalReportDataset", dataSource);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        String computerUserName = System.getProperty("user.name");

        int reportNumber = 0;
        String filename = "general_report.pdf";
        String path = "C:\\Users\\" + computerUserName + "\\Downloads\\" + filename;
        File reportFile = new File(path);

        while (reportFile.exists()) {
            reportNumber++;
            filename = "general_report(" + reportNumber + ").pdf";
            path = "C:\\Users\\" + computerUserName + "\\Downloads\\" + filename;
            reportFile = new File(path);
        }

        JasperExportManager.exportReportToPdfFile(jasperPrint, path);

        return "Report Generated: " + filename;
    }
}