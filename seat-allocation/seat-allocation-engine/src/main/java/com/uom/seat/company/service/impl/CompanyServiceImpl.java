package com.uom.seat.company.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.uom.seat.resource.entity.ResourceEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uom.seat.company.dto.CompanyRequest;
import com.uom.seat.company.dto.CompanyResponse;
import com.uom.seat.company.entity.CompanyEntity;
import com.uom.seat.company.repository.CompanyRepository;
import com.uom.seat.company.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public Integer createCompany(CompanyRequest company) {

		if (companyRepository.existsByName(company.getName()) ) {
			System.out.println("company already exist");
			return -1;
		}
		return companyRepository.save(convertToCompanyEntity(company)).getId();
	}

	@Override
	public CompanyResponse getCompany(Integer id) {
		return convertToCompanyResponse(companyRepository.findById(id).get());
	}

	@Override
	public CompanyResponse updateCompany(Integer id, CompanyRequest company) {
		CompanyEntity entity = companyRepository.saveAndFlush(updateCompanyEntity(companyRepository.findById(id).get(), company));
		return convertToCompanyResponse(entity);
	}

	@Override
	public Boolean deleteCompany(Integer companyId) {
		CompanyEntity entity = companyRepository.saveAndFlush(deleteCompany(companyRepository.findById(companyId).get()));
		return  !entity.getActiveStatus();
	}

	private CompanyEntity deleteCompany(CompanyEntity companyEntity) {
		companyEntity.setActiveStatus(false);
		return  companyEntity;
	}


	private CompanyEntity convertToCompanyEntity(CompanyRequest company) {

		CompanyEntity entity = null;
		entity = modelMapper.map(company, CompanyEntity.class);
		entity.setXid(UUID.randomUUID().toString());

		return entity;
	}

	private CompanyResponse convertToCompanyResponse(CompanyEntity entity) {

		CompanyResponse dto = null;
		dto = modelMapper.map(entity, CompanyResponse.class);

		return dto;
	}

	private CompanyEntity updateCompanyEntity(CompanyEntity entity, CompanyRequest company) {

		entity.setName(company.getName());
		entity.setAddress(company.getAddress());
		entity.setContactNumber(company.getContactNumber());
		entity.setEmail(company.getEmail());
		entity.setMobile(company.getMobile());
		entity.setFax(company.getFax());
		
		return entity;
	}

	@Override
	public List<CompanyResponse> getAllCompanies() {
		List<CompanyResponse> companyResponseList=new ArrayList<>();

		for (int i=0;i<companyRepository.findAll().size();i++){
			CompanyResponse companyResponse=convertToCompanyResponse(companyRepository.findById(i+1).orElse(null));
			companyResponseList.add(companyResponse);
		}
		return companyResponseList;
	}
}
