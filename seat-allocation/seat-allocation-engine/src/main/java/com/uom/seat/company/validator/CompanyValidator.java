package com.uom.seat.company.validator;

import org.springframework.stereotype.Component;

import com.uom.seat.company.dto.CompanyRequest;

@Component
public class CompanyValidator {
	
	public void validateCompany(CompanyRequest company) {
		validateMandotoryFields(company);
		validateOptionalFields(company);
	}
	
	private void validateMandotoryFields(CompanyRequest company) {
		// TODO

	}

	private void validateOptionalFields(CompanyRequest company) {
		// TODO
	}

}
