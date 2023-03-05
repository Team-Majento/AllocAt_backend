package com.uom.seat.company.logic;


import com.uom.seat.company.service.CompanyService;
import com.uom.seat.resource.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyDeletionLogic {

    @Autowired
    private CompanyService companyService;

    public Boolean deleteCompany(String accessToken, Integer companyId) {
        return  companyService.deleteCompany(companyId);
    }
}
