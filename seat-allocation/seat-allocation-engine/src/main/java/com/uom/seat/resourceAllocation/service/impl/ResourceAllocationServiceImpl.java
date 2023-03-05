package com.uom.seat.resourceAllocation.service.impl;
import com.uom.seat.bookingRequest.entity.BookingRequestEntity;
import com.uom.seat.bookingRequest.repository.BookingRequestRepository;
import com.uom.seat.company.repository.CompanyRepository;
import com.uom.seat.condition.service.ConditionService;
import com.uom.seat.rateCard.entity.RateCardEntity;
import com.uom.seat.rateCard.repository.RateCardRepository;
import com.uom.seat.resourceAllocatedCost.entity.ResourceAllocatedCostEntity;
import com.uom.seat.resourceAllocatedCost.service.ResourceAllocatedCostService;
import com.uom.seat.resourceAllocation.dto.ResourceAllocationRequest;
import com.uom.seat.resourceAllocation.dto.ResourceAllocationResponse;
import com.uom.seat.resourceAllocation.entity.ResourceAllocationEntity;
import com.uom.seat.resourceAllocation.repository.ResourceAllocationRepository;
import com.uom.seat.resourceAllocation.service.ResourceAllocationService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
public class ResourceAllocationServiceImpl implements ResourceAllocationService {

    @Autowired
    private BookingRequestRepository bookingRequestRepository;
    @Autowired
    private ResourceAllocationRepository resourceAllocationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RateCardRepository rateCardRepository;

    @Autowired
    private CompanyRepository companyRepository;


    @Autowired
    private ConditionService conditionService;

    @Autowired
    private ResourceAllocatedCostService resourceAllocatedCostService;

    @Override
    public Integer createResourceAllocation(ResourceAllocationRequest resourceAllocation) {
        return resourceAllocationRepository.save(convertToResourceAllocationEntity(resourceAllocation)).getId();
    }

    private ResourceAllocationEntity convertToResourceAllocationEntity(ResourceAllocationRequest resourceAllocation) {

        ResourceAllocationEntity entity = null;
        entity = modelMapper.map(resourceAllocation, ResourceAllocationEntity.class);
        // entity.setXid(UUID.randomUUID().toString());

        return entity;
    }





    private ResourceAllocationResponse convertToResourceAllocationResponse(ResourceAllocationEntity entity) {

        ResourceAllocationResponse dto=null;
        dto=modelMapper.map(entity, ResourceAllocationResponse.class);

        return dto;
    }


    /////////////////////// ACCEPT RESOURCE BOOKING REQUEST ////////////////////////////////
    @Override
    public Integer createReleventResourceAllocation(Integer bookingRequestID) {

        BookingRequestEntity releventBookingRequest=bookingRequestRepository.findById(bookingRequestID).orElse(null);
        releventBookingRequest.setStatus("Accepted");
        ResourceAllocationEntity resourceAllocationEntity=new ResourceAllocationEntity();
        resourceAllocationEntity.setResourceId(releventBookingRequest.getResourceId());
        resourceAllocationEntity.setRequesterUserId(releventBookingRequest.getRequesterUserId());
        resourceAllocationEntity.setRequestersManagersUserId(releventBookingRequest.getRequestersManagersUserId());
        resourceAllocationEntity.setCompanyId(releventBookingRequest.getCompanyId());
        resourceAllocationEntity.setRequiredDate(releventBookingRequest.getRequiredDate());
        resourceAllocationEntity.setStartTime(releventBookingRequest.getStartTime());
        resourceAllocationEntity.setEndTime(releventBookingRequest.getEndTime());
        resourceAllocationEntity.setActualEndTime(null);
        resourceAllocationEntity.setBookingRequestEntity(releventBookingRequest);

        //   setsetConditionFkInResourceAllocationTable
        conditionService.setConditionFkInResourceAllocationTable(resourceAllocationEntity);

        resourceAllocatedCostService.createResourceAllocatedCost(resourceAllocationEntity,releventBookingRequest.getResourceId());

        return  resourceAllocationRepository.save(resourceAllocationEntity).getId();
    }

   /* public void createResourceAllocatedCost(ResourceAllocationEntity resourceAllocationEntity,Integer resource_id){
        ResourceAllocatedCostEntity resourceAllocatedCostEntity=new ResourceAllocatedCostEntity();


        Double units;
        Double allocated_cost;
        Integer usage_in_minutes;

        RateCardEntity rateCardEntity=rateCardRepository.findRateCardEntityByResource_id(resource_id);

        usage_in_minutes= Math.toIntExact(resourceAllocationEntity.getStartTime().until(resourceAllocationEntity.getEndTime(), ChronoUnit.MINUTES));
        units=Math.ceil((double) usage_in_minutes/rateCardEntity.getUnit());
        allocated_cost=(rateCardEntity.getHourRate()/rateCardEntity.getUnit())*units;

        resourceAllocatedCostEntity.setCalculated_cost();
        resourceAllocatedCostEntity.setFinal_cost();
        resourceAllocatedCostEntity.setDiscount();
    }
*/
    //////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public Page<ResourceAllocationResponse> getALlResourceAllocations(Integer page, Integer size) {
        PageRequest pageable= PageRequest.of(page,size);
        Page<ResourceAllocationEntity> pageEntities=resourceAllocationRepository.findAll(pageable);

        List<ResourceAllocationEntity> entityList= pageEntities.getContent();
        List<ResourceAllocationResponse> dtoList = new ArrayList<ResourceAllocationResponse>();

        entityList.forEach(entity -> dtoList.add(convertToResourceAllocationResponse(entity)));

        return new PageImpl<ResourceAllocationResponse>(dtoList,pageable,pageEntities.getTotalElements());
    }
    @Override
    public Page<ResourceAllocationResponse> getALlResourceAllocationsByRequstersUserId(Integer requesterUserId, Integer page, Integer size) {
        PageRequest pageable= PageRequest.of(page,size);
        Page<ResourceAllocationEntity> pageEntities=resourceAllocationRepository.findAllByRequesterUserId(requesterUserId,pageable);

        List<ResourceAllocationEntity> entityList= pageEntities.getContent();
        List<ResourceAllocationResponse> dtoList = new ArrayList<ResourceAllocationResponse>();

        entityList.forEach(entity -> dtoList.add(convertToResourceAllocationResponse(entity)));

        return new PageImpl<ResourceAllocationResponse>(dtoList,pageable,pageEntities.getTotalElements());
    }

    ///////////////////////////////// GENERATE REPORT //////////////////////////////////////////


   /* public Integer generateReport(Integer company_id,LocalDate from_date,LocalDate to_date) throws JRException, FileNotFoundException {
        List<ResourceAllocationEntity> resourceAllocationEntities = resourceAllocationRepository.findAllResourceAllocationsByCompanyIdAndDates(company_id,from_date,to_date);
        reportRepository.deleteAll();
        String company_name=companyRepository.findById(company_id).get().getName();
        for (int i = 0; i < resourceAllocationEntities.size(); i++) {
            ReportEntity reportEntity=new ReportEntity();

            ResourceAllocationEntity resourceAllocationEntity=resourceAllocationRepository.findById(i+1).get();
            Integer resource_id = resourceAllocationEntity.getResourceId();
            Integer requester_id=resourceAllocationEntity.getRequesterUserId();
            LocalDate required_date=resourceAllocationEntity.getRequiredDate();

          *//*  Double units;
            Double allocated_cost;
            Integer usage_in_minutes;

            RateCardEntity rateCardEntity=rateCardRepository.findRateCardEntityByResource_id(resource_id);

            usage_in_minutes= Math.toIntExact(resourceAllocationEntity.getStartTime().until(resourceAllocationEntity.getEndTime(), ChronoUnit.MINUTES));
            units=Math.ceil((double) usage_in_minutes/rateCardEntity.getUnit());
            allocated_cost=(rateCardEntity.getHour_rate()/60.0)*usage_in_minutes;*//*


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
    }

    public String exportReport() throws FileNotFoundException, JRException {
        List<ReportEntity> reportEntities=reportRepository.findAll();
        File file= ResourceUtils.getFile("classpath:report1.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(reportEntities);
        Map<String,Object> parameters=new HashMap<>();
        parameters.put("createdBy","Java Techie");
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parameters,dataSource);


        JasperExportManager.exportReportToPdfFile(jasperPrint,"C:\\Users\\Nishath\\Desktop\\Reports"+"\\report1.pdf");

        return "Report Generated";
    }
*/
    ////////////////////////////////////////////////////////////////////




}
