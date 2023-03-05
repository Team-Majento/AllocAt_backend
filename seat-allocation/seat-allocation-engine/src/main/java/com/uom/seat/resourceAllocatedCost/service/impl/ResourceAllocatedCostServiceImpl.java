package com.uom.seat.resourceAllocatedCost.service.impl;

import com.uom.seat.company.entity.CompanyEntity;
import com.uom.seat.company.repository.CompanyRepository;
import com.uom.seat.condition.entity.ConditionEntity;
import com.uom.seat.rateCard.entity.RateCardEntity;
import com.uom.seat.rateCard.repository.RateCardRepository;
import com.uom.seat.report.entity.CompanyWiseReport;
import com.uom.seat.report.entity.GeneralReport;
import com.uom.seat.report.entity.GeneralReportSubReport;
import com.uom.seat.report.repository.CompanyWiseReportRepository;
import com.uom.seat.report.repository.GeneralReportRepository;
import com.uom.seat.resource.entity.ResourceEntity;
import com.uom.seat.resource.repository.ResourceRepository;
import com.uom.seat.resourceAllocatedCost.entity.ResourceAllocatedCostEntity;
import com.uom.seat.resourceAllocatedCost.repository.ResourceAllocatedCostRepository;
import com.uom.seat.resourceAllocatedCost.service.ResourceAllocatedCostService;
import com.uom.seat.resourceAllocation.entity.ResourceAllocationEntity;
import com.uom.seat.resourceAllocation.repository.ResourceAllocationRepository;
import com.uom.seat.resourceAllocation.service.ResourceAllocationService;
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
    private RateCardRepository rateCardRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ResourceAllocationRepository resourceAllocationRepository;

    @Autowired
    private CompanyWiseReportRepository companyWiseReportRepository;

    @Autowired
    private GeneralReportRepository generalReportRepository;
/*    @Override
    public void createResourceAllocatedCost(ResourceAllocationEntity resourceAllocationEntity, Integer resource_id){
        ResourceAllocatedCostEntity resourceAllocatedCostEntity=new ResourceAllocatedCostEntity();


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
        requester_id=resourceAllocationEntity.getRequesterUserId();
        Integer company_ID;
        LocalDate required_date;
        String company_name;
        String resource_name;


        required_date=resourceAllocationEntity.getRequiredDate();
        company_ID=resourceAllocationEntity.getCompanyId();
        CompanyEntity companyEntity=companyRepository.findById(company_ID).get();
        company_name=companyEntity.getName();
        resourceEntity =resourceRepository.findById(resource_id).get();
        RateCardEntity rateCardEntity=rateCardRepository.findById(resourceEntity.getRateCardId()).get();

        usage_in_hours= Math.toIntExact(resourceAllocationEntity.getStartTime().until(resourceAllocationEntity.getEndTime(), ChronoUnit.HOURS));
        usage_in_minutes= Math.toIntExact(resourceAllocationEntity.getStartTime().until(resourceAllocationEntity.getEndTime(), ChronoUnit.MINUTES));
        usage_in_hours_and_minutes=Integer.toString(usage_in_hours)+" h "+Integer.toString(usage_in_minutes)+" min " ;

        units=Math.ceil((double) usage_in_minutes/rateCardEntity.getUnit());
        allocated_cost=(rateCardEntity.getHourRate()/rateCardEntity.getUnit())*units;

        resourceAllocatedCostEntity.setCalculated_cost(allocated_cost);

        if(resourceAllocationEntity.getConditionEntity().getId()!=null){

            ConditionEntity conditionEntity=resourceAllocationEntity.getConditionEntity();
            discount_rate=conditionEntity.getDiscount_rate();
            discount=discount_rate*allocated_cost/100;
            final_cost=allocated_cost-discount;

            resourceAllocatedCostEntity.setFinal_cost(final_cost);
            resourceAllocatedCostEntity.setDiscount(discount);


        }
        else {


            resourceAllocatedCostEntity.setFinal_cost(allocated_cost);
            resourceAllocatedCostEntity.setDiscount(0.0);
        }

        resourceAllocatedCostEntity.setResourceAllocationEntity(resourceAllocationEntity);
        resourceAllocatedCostRepository.save(resourceAllocatedCostEntity);

    }
    */

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
        RateCardEntity rateCardEntity = rateCardRepository.findById(resourceEntity.getRateCardId()).get();
        usage_in_minutes = Math.toIntExact(resourceAllocationEntity.getStartTime().until(resourceAllocationEntity.getEndTime(), ChronoUnit.MINUTES));


        units = Math.ceil((double) usage_in_minutes / rateCardEntity.getUnit());
        allocated_cost = (rateCardEntity.getHourRate() / rateCardEntity.getUnit()) * units;

        resourceAllocatedCostEntity.setCalculated_cost(allocated_cost);

        if (resourceAllocationEntity.getConditionEntity() != null) {

            ConditionEntity conditionEntity = resourceAllocationEntity.getConditionEntity();
            discount_rate = conditionEntity.getDiscount_rate();
            discount = discount_rate * allocated_cost / 100;
            final_cost = allocated_cost - discount;

            resourceAllocatedCostEntity.setFinal_cost(final_cost);
            resourceAllocatedCostEntity.setDiscount(discount);
            resourceAllocatedCostEntity.setDiscount_rate(discount_rate);

        } else {


            resourceAllocatedCostEntity.setFinal_cost(allocated_cost);
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
            String resource_name;


            ResourceAllocationEntity resourceAllocationEntity = resourceAllocationEntities.get(i);
            resource_id = resourceAllocationEntity.getResourceId();
            ResourceAllocatedCostEntity resourceAllocatedCostEntity = resourceAllocationEntity.getResourceAllocatedCostEntity();
            resourceEntity = resourceRepository.findById(resource_id).get();


            requester_id = resourceAllocationEntity.getRequesterUserId();
            required_date = resourceAllocationEntity.getRequiredDate();
            usage_in_hours = Math.toIntExact(resourceAllocationEntity.getStartTime().until(resourceAllocationEntity.getEndTime(), ChronoUnit.HOURS));
            usage_in_minutes = Math.toIntExact(resourceAllocationEntity.getStartTime().until(resourceAllocationEntity.getEndTime(), ChronoUnit.MINUTES));
            usage_in_hours_and_minutes = Integer.toString(usage_in_hours) + " h " + Integer.toString(usage_in_minutes) + " min ";
            discount_rate = resourceAllocatedCostEntity.getDiscount_rate();
            discount = resourceAllocatedCostEntity.getDiscount();
            allocated_cost = resourceAllocatedCostEntity.getCalculated_cost();
            final_cost = resourceAllocatedCostEntity.getFinal_cost();

            RateCardEntity rateCardEntity = rateCardRepository.findById(resourceEntity.getRateCardId()).get();
            units = Math.ceil((double) usage_in_minutes / rateCardEntity.getUnit());

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

   /* @Override
    public Integer generateGeneralReport(Integer company_id,LocalDate from_date,LocalDate to_date) throws JRException, FileNotFoundException {
        List<ResourceAllocationEntity> resourceAllocationEntities = resourceAllocationRepository.findAllResourceAllocationsByCompanyIdAndDatesPlusSortByCompantID(company_id,from_date,to_date);
        reportRepository.deleteAll();
        String company_name=companyRepository.findById(company_id).get().getName();
        Double total_cost_per_company=0.0;
        for (int i = 0; i < resourceAllocationEntities.size(); i++) {
            ReportEntity reportEntity=new ReportEntity();

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
            String resource_name;


            ResourceAllocationEntity resourceAllocationEntity=resourceAllocationRepository.findById(i+1).get();
            resource_id = resourceAllocationEntity.getResourceId();
            ResourceAllocatedCostEntity resourceAllocatedCostEntity=resourceAllocationEntity.getResourceAllocatedCostEntity();
            resourceEntity =resourceRepository.findById(resource_id).get();


            requester_id=resourceAllocationEntity.getRequesterUserId();
            required_date=resourceAllocationEntity.getRequiredDate();
            usage_in_hours= Math.toIntExact(resourceAllocationEntity.getStartTime().until(resourceAllocationEntity.getEndTime(), ChronoUnit.HOURS));
            usage_in_minutes= Math.toIntExact(resourceAllocationEntity.getStartTime().until(resourceAllocationEntity.getEndTime(), ChronoUnit.MINUTES));
            usage_in_hours_and_minutes=Integer.toString(usage_in_hours)+" h "+Integer.toString(usage_in_minutes)+" min " ;
            discount_rate=resourceAllocatedCostEntity.getDiscount_rate();
            discount=resourceAllocatedCostEntity.getDiscount();
            allocated_cost=resourceAllocatedCostEntity.getCalculated_cost();
            final_cost=resourceAllocatedCostEntity.getFinal_cost();

            RateCardEntity rateCardEntity=rateCardRepository.findById(resourceEntity.getRateCardId()).get();
            units=Math.ceil((double) usage_in_minutes/rateCardEntity.getUnit());

            total_cost_per_company+=final_cost;


            reportEntity.setRequester_id(requester_id);
            reportEntity.setResource_id(resource_id);
            reportEntity.setRequired_date(required_date);
            reportEntity.setUnits(units);
            reportEntity.setCompany_name(company_name);
            reportEntity.setUsage(usage_in_minutes);
            reportEntity.setAllocated_cost(allocated_cost);


            reportRepository.save(reportEntity);
        }
        exportReport();
        return 200;
    }*/

    @Override
    public Integer generateGeneralReport(LocalDate from_date, LocalDate to_date) throws JRException, FileNotFoundException {
        List<ResourceAllocationEntity> resourceAllocationEntities = resourceAllocationRepository.findAllResourceAllocationsByDatesPlusSortByCompantID(from_date, to_date);
        companyWiseReportRepository.deleteAll();
        Double total_cost_per_company = 0.0;
        String old_company_name = companyRepository.findById(resourceAllocationEntities.get(0).getCompanyId()).get().getName();

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
        String resource_name;
        List<GeneralReportSubReport> generalReportSubReportList = new ArrayList<>();

        for (int i = 0; i < resourceAllocationEntities.size(); i++) {
            String current_company_name = companyRepository.findById(resourceAllocationEntities.get(i).getCompanyId()).get().getName();


            if (old_company_name != current_company_name) {
                GeneralReportSubReport generalReportSubReport = new GeneralReportSubReport(resourceAllocationEntities.get(i).getCompanyId(), current_company_name, total_cost_per_company);
                generalReportSubReportList.add(generalReportSubReport);
                total_cost_per_company = 0.0;
            }
            CompanyWiseReport reportEntity = new CompanyWiseReport();


            ResourceAllocationEntity resourceAllocationEntity = resourceAllocationEntities.get(i);
            resource_id = resourceAllocationEntity.getResourceId();
            ResourceAllocatedCostEntity resourceAllocatedCostEntity = resourceAllocationEntity.getResourceAllocatedCostEntity();
            resourceEntity = resourceRepository.findById(resource_id).get();


            requester_id = resourceAllocationEntity.getRequesterUserId();
            required_date = resourceAllocationEntity.getRequiredDate();
            usage_in_hours = Math.toIntExact(resourceAllocationEntity.getStartTime().until(resourceAllocationEntity.getEndTime(), ChronoUnit.HOURS));
            usage_in_minutes = Math.toIntExact(resourceAllocationEntity.getStartTime().until(resourceAllocationEntity.getEndTime(), ChronoUnit.MINUTES));
            usage_in_hours_and_minutes = Integer.toString(usage_in_hours) + " h " + Integer.toString(usage_in_minutes) + " min ";
            discount_rate = resourceAllocatedCostEntity.getDiscount_rate();
            discount = resourceAllocatedCostEntity.getDiscount();
            allocated_cost = resourceAllocatedCostEntity.getCalculated_cost();
            final_cost = resourceAllocatedCostEntity.getFinal_cost();

            RateCardEntity rateCardEntity = rateCardRepository.findById(resourceEntity.getRateCardId()).get();
            units = Math.ceil((double) usage_in_minutes / rateCardEntity.getUnit());

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
            reportEntity.setCompany_name(current_company_name);
            reportEntity.setTotal_allocated_cost(total_cost_per_company);

            companyWiseReportRepository.save(reportEntity);

            old_company_name = current_company_name;

        }

        String last_company_name = companyRepository.findById(resourceAllocationEntities.get(resourceAllocationEntities.size() - 1).getCompanyId()).get().getName();
        GeneralReportSubReport generalReportSubReport = new GeneralReportSubReport(resourceAllocationEntities.get(resourceAllocationEntities.size() - 1).getCompanyId(), last_company_name, total_cost_per_company);
        generalReportSubReportList.add(generalReportSubReport);


        exportGeneralReport(generalReportSubReportList);
        return 200;
    }

    public String exportReport() throws FileNotFoundException, JRException {
        List<CompanyWiseReport> reportEntities = companyWiseReportRepository.findAll();
        File file = ResourceUtils.getFile("classpath:company_wise_report3.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportEntities);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Java Techie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);


        JasperExportManager.exportReportToPdfFile(jasperPrint, "D:\\report" + "\\report4.pdf");

        return "Report Generated";
    }

    public String exportGeneralReport(List<GeneralReportSubReport> generalReportSubReportList) throws FileNotFoundException, JRException {
        List<CompanyWiseReport> reportEntities = companyWiseReportRepository.findAll();
        File file = ResourceUtils.getFile("classpath:general_report_1.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource1 = new JRBeanCollectionDataSource(reportEntities);
        JRBeanCollectionDataSource dataSource2 = new JRBeanCollectionDataSource(generalReportSubReportList);


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("resourceAllocationListDataset", dataSource1);
        parameters.put("companyWiseTotalCostAllocationDataset", dataSource2);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());


        JasperExportManager.exportReportToPdfFile(jasperPrint, "D:\\report" + "\\report6.pdf");

        return "Report Generated";
    }

}