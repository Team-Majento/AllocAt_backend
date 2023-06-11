package com.uom.seat.resourceAllocation.service.impl;

import com.uom.seat.bookingRequest.entity.BookingRequestEntity;
import com.uom.seat.bookingRequest.repository.BookingRequestRepository;
import com.uom.seat.condition.service.ConditionService;
import com.uom.seat.resourceAllocatedCost.service.ResourceAllocatedCostService;
import com.uom.seat.resourceAllocation.dto.ResourceAllocationRequest;
import com.uom.seat.resourceAllocation.dto.ResourceAllocationResponse;
import com.uom.seat.resourceAllocation.entity.ResourceAllocationEntity;
import com.uom.seat.resourceAllocation.repository.ResourceAllocationRepository;
import com.uom.seat.resourceAllocation.service.ResourceAllocationService;
import com.uom.seat.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class ResourceAllocationServiceImpl implements ResourceAllocationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private BookingRequestRepository bookingRequestRepository;
    @Autowired
    private ResourceAllocationRepository resourceAllocationRepository;

    @Autowired
    private ModelMapper modelMapper;


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

        return entity;
    }


    private ResourceAllocationResponse convertToResourceAllocationResponse(ResourceAllocationEntity entity) {

        ResourceAllocationResponse dto = null;
        dto = modelMapper.map(entity, ResourceAllocationResponse.class);

        return dto;
    }


    // ACCEPT RESOURCE BOOKING REQUEST
    @Override
    public Integer createRelevantResourceAllocation(Integer bookingRequestID, String conditionName) {

        BookingRequestEntity relevantBookingRequest = bookingRequestRepository.findById(bookingRequestID).orElse(null);
        relevantBookingRequest.setStatus("Accepted");
        ResourceAllocationEntity resourceAllocationEntity = new ResourceAllocationEntity();
        resourceAllocationEntity.setResourceId(relevantBookingRequest.getResourceId());
        resourceAllocationEntity.setRequesterUserId(relevantBookingRequest.getRequesterUserId());
        resourceAllocationEntity.setRequestersManagersUserId(relevantBookingRequest.getRequestersManagersUserId());
        resourceAllocationEntity.setCompanyId(relevantBookingRequest.getCompanyId());
        resourceAllocationEntity.setRequiredDate(relevantBookingRequest.getRequiredDate());
        resourceAllocationEntity.setStartTime(relevantBookingRequest.getStartTime());
        resourceAllocationEntity.setEndTime(relevantBookingRequest.getEndTime());
        resourceAllocationEntity.setActualEndTime(null);
        resourceAllocationEntity.setBookingRequestEntity(relevantBookingRequest);

        conditionService.setConditionFkInResourceAllocationTable(resourceAllocationEntity,conditionName);

        resourceAllocatedCostService.createResourceAllocatedCost(resourceAllocationEntity, relevantBookingRequest.getResourceId());

        return resourceAllocationRepository.save(resourceAllocationEntity).getId();
    }


    @Override
    public Page<ResourceAllocationResponse> getALlResourceAllocations(Integer page, Integer size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<ResourceAllocationEntity> pageEntities = resourceAllocationRepository.findAll(pageable);

        List<ResourceAllocationEntity> entityList = pageEntities.getContent();
        List<ResourceAllocationResponse> dtoList = new ArrayList<ResourceAllocationResponse>();

        entityList.forEach(entity -> dtoList.add(convertToResourceAllocationResponse(entity)));

        return new PageImpl<ResourceAllocationResponse>(dtoList, pageable, pageEntities.getTotalElements());
    }

    @Override
    public Page<ResourceAllocationResponse> getALlResourceAllocationsByRequstersUserId(Integer requesterUserId, Integer page, Integer size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<ResourceAllocationEntity> pageEntities = resourceAllocationRepository.findAllByRequesterUserId(requesterUserId, pageable);

        List<ResourceAllocationEntity> entityList = pageEntities.getContent();
        List<ResourceAllocationResponse> dtoList = new ArrayList<ResourceAllocationResponse>();

        entityList.forEach(entity -> dtoList.add(convertToResourceAllocationResponse(entity)));

        return new PageImpl<ResourceAllocationResponse>(dtoList, pageable, pageEntities.getTotalElements());
    }






    private void createEmail(String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom("husen2000nisath@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        this.javaMailSender.send(simpleMailMessage);

    }




    @Override
    public Integer sendNotificationEmail(Integer userId, Integer resourceManagerId, Integer status, LocalDate requiredDate, LocalTime startTime, LocalTime endTime, Integer resourceId) {
        String userEmail=userRepository.findEmailByUserId(userId);
        String resourceManagerEmail=userRepository.findEmailByUserId(resourceManagerId);
        String userName=userRepository.findUserNameByUserId(userId);
        String resourceManagerName=userRepository.findUserNameByUserId(resourceManagerId);

        //status==1 means accepted
        if(status==1){
            createEmail(userEmail,"Resource Booking Request Accepted","Dear " +userName +",\n\n" +
                                                                                     "We are pleased to inform you that your resource booking request has been accepted.\n\n" +
                                                                                       "Booking Details:\n" +
                                                                                       "•Resource id: "+resourceId+"\n"+
                                                                                       "•Required date: "+requiredDate+"\n"+
                                                                                       "•Time: "+startTime+"-"+endTime+"\n\n"+
                                                                                       "If you have any questions or need further assistance, please feel free to contact your resource manager.\n\n" +
                                                                                       "Thank you for using our resource allocation system.\n\n" +
                                                                                        "Best regards,\n" +
                                                                                        "Alloc@ Team\n" +
                                                                                         "\n"
                                                                                        );
            createEmail(resourceManagerEmail,"Resource Booking Request Accepted","Dear " +resourceManagerName +",\n\n" +
                                                                                        "We would like to inform you that the resource booking request made by "+userName+" has been accepted.\n\n" +
                                                                                        "Booking Details:\n" +
                                                                                        "•Resource id: "+resourceId+"\n"+
                                                                                        "•Required date: "+requiredDate+"\n"+
                                                                                        "•Time: "+startTime+"-"+endTime+"\n\n"+
                                                                                        "As the relevant resource manager, we kindly request your attention to ensure the availability and proper arrangements for the approved booking. Please coordinate with "+userName+" to address any specific requirements or provide any additional instructions.\n\n" +
                                                                                        "Thank you for your cooperation.\n\n" +
                                                                                        "Best regards,\n" +
                                                                                        "Alloc@ Team\n" +
                                                                                        "\n"
                                                                                         );
        }
        //status==-1 means rejected
        else if(status==-1){
            createEmail(userEmail,"Resource Booking Request Rejected","Dear "+userName+",\n\n" +
                                                                                    "We regret to inform you that your resource booking request has been rejected.\n\n" +
                                                                                    "Booking Details:\n" +
                                                                                    "•Resource id: "+resourceId+"\n"+
                                                                                    "•Required date: "+requiredDate+"\n"+
                                                                                    "•Time: "+startTime+"-"+endTime+"\n\n" +
                                                                                    "Please contact your resource manager for more information or to discuss alternative arrangements.\n\n" +
                                                                                    "Thank you for your understanding.\n\n" +
                                                                                    "Best regards,\n" +
                                                                                    "Alloc@ Team");

            createEmail(resourceManagerEmail,"Resource Booking Request Rejected","Dear " +resourceManagerName +",\n\n" +
                                                                                                "We regret to inform you that the resource booking request made by "+userName+" has been rejected.\n\n" +
                                                                                                "Booking Details:\n" +
                                                                                                "•Resource id: "+resourceId+"\n"+
                                                                                                "•Required date: "+requiredDate+"\n"+
                                                                                                "•Time: "+startTime+"-"+endTime+"\n\n" +
                                                                                                "After careful consideration and evaluation, we have determined that the requested resource is unavailable or not suitable for the given booking parameters.\n\n" +
                                                                                                "We understand that this may cause inconvenience, and we apologize for any disruption caused. We encourage you to explore alternative options or coordinate with "+userName+" to find a suitable arrangement for the required resource\n\n"+
                                                                                                "Thank you for your cooperation.\n\n" +
                                                                                                "Best regards,\n" +
                                                                                                "Alloc@ Team\n" +
                                                                                                "\n"
            );
        }

        return 1;
    }

    @Override
    public Integer rejectRelevantBookingRequest(Integer bookingRequestID) {
        BookingRequestEntity relevantBookingRequest = bookingRequestRepository.findById(bookingRequestID).orElse(null);
        relevantBookingRequest.setStatus("Rejected");
        return 1;
    }

    @Override
    public List<ResourceAllocationResponse> getAllResourceAllocationsByResourceId(Integer resourceId) {

        List<ResourceAllocationEntity> entityList = resourceAllocationRepository. getAllResourceAllocationsByResourceId(resourceId);
        List<ResourceAllocationResponse> dtoList = new ArrayList<ResourceAllocationResponse>();
        entityList.forEach(entity -> dtoList.add(convertToResourceAllocationResponse(entity)));
        return  dtoList;
    }

    @Override
    public Integer getAllResourceAllocationsByCurrentMonth() {
        Integer count = resourceAllocationRepository.getAllResourceAllocationsByCurrentMonth();
        return  count;
    }

    @Override
    public LocalTime getTotalAllocationHoursUserWise(Integer userId) {
        LocalTime count = resourceAllocationRepository.getTotalAllocationHoursUserWise(userId);
        return count;
    }

    @Override
    public List<ResourceAllocationResponse> getAllCurrentOngoingAllocations() {
        System.out.println("****");
        List<ResourceAllocationEntity> entityList = resourceAllocationRepository.getAllCurrentOngoingAllocations();

        List<ResourceAllocationResponse> dtoList = new ArrayList<ResourceAllocationResponse>();
        entityList.forEach(entity -> dtoList.add(convertToResourceAllocationResponse(entity))
        );
        System.out.println("****");
        return dtoList;
    }
}
