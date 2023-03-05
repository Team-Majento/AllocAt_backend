package com.uom.seat.company.api.impl;

import com.uom.seat.company.logic.CompanyDeletionLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.uom.seat.api.CompanyApi;
import com.uom.seat.company.dto.CompanyRequest;
import com.uom.seat.company.dto.CompanyResponse;
import com.uom.seat.company.logic.CompanyCreationLogic;
import com.uom.seat.company.logic.CompanyRetrivalLogic;
import com.uom.seat.company.logic.CompanyUpdateLogic;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class CompanyApiImpl implements CompanyApi {
	
	@Autowired
	private CompanyCreationLogic companyCreationLogic;
	
	@Autowired
	private CompanyRetrivalLogic companyRetrivalLogic;
	
	@Autowired
	private CompanyUpdateLogic companyUpdateLogic;


	@Autowired
	private CompanyDeletionLogic companyDeletionLogic;

	@Override
	public Integer createCompany(String accessToken, CompanyRequest company) {
		return companyCreationLogic.createCompany(accessToken, company);
	}

	
	@Override
	public void updateOrganizationByAccessToken(String accessToken, CompanyRequest company) {
		// TODO Auto-generated method stub

	}


	@Override
	public CompanyResponse getCompany(String accessToken, Integer id) {
		return companyRetrivalLogic.getCompany(accessToken, id);
	}


	@Override
	public CompanyResponse getCompany(String accessToken, String xid) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public CompanyResponse updateCompany(Integer id, CompanyRequest company) {
		return companyUpdateLogic.updateCompany(id, company);
		
	}

	@Override
	public Boolean deleteCompany(String authorization, Integer companyId) {
		return companyDeletionLogic.deleteCompany(authorization,companyId);
	}

}
