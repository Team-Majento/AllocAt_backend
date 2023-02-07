package com.uom.seat.company.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uom.seat.company.dto.CompanyRequest;
import com.uom.seat.company.dto.CompanyResponse;
import com.uom.seat.company.service.CompanyService;
import com.uom.seat.company.validator.CompanyValidator;

@Component
public class CompanyUpdateLogic {

	@Autowired
	private CompanyService service;

	@Autowired
	private CompanyValidator companyValidator;

	

	public CompanyResponse updateCompany(Integer id, CompanyRequest company) {
		// 1. find company by id
		// 2. validate company request
		// 3. update company
		
		
		companyValidator.validateCompany(company);
		
		return service.updateCompany(id, company);
	}

}
