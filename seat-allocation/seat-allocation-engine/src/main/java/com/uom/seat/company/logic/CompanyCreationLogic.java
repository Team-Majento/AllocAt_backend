package com.uom.seat.company.logic;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uom.seat.company.dto.CompanyRequest;
import com.uom.seat.company.service.CompanyService;
import com.uom.seat.company.validator.CompanyValidator;

@Component
public class CompanyCreationLogic {

	private static final Logger logger = Logger.getLogger(CompanyCreationLogic.class);

	@Autowired
	private CompanyValidator companyValidator;
	
	@Autowired
	private CompanyService companyService;

	public Integer createCompany(String accessToken, CompanyRequest company) {

		// 1. validate company request
		// 2. create company
		
		companyValidator.validateCompany(company);
		logger.info("The company is validated.");
		
		Integer id = companyService.createCompany(company);
		logger.info("The organization is created.");
		
		return id;
	}

}
