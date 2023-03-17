package com.uom.seat.company.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uom.seat.company.dto.CompanyResponse;
import com.uom.seat.company.service.CompanyService;
import java.util.List;


@Component
public class CompanyRetrivalLogic {
	
	@Autowired
	private CompanyService service;
	
	public CompanyResponse getCompany(String accessToken, Integer id) {
		return service.getCompany(id);
	}
	public List<CompanyResponse> getAllCompanies(String accessToken) {
		return service.getAllCompanies();
	}


	private void validateServiceParam(Integer id) {
		//ValidationUtil.validateNotEmpty(id, "The id is mandatory");
	}

}
