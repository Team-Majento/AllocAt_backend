package com.uom.seat.company.service;

import com.uom.seat.company.dto.CompanyRequest;
import com.uom.seat.company.dto.CompanyResponse;

public interface CompanyService {

	public Integer createCompany(CompanyRequest company);
	public CompanyResponse getCompany(Integer id);
	public CompanyResponse updateCompany(Integer id, CompanyRequest company);

}
