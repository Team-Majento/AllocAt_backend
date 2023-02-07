package com.uom.seat.server.rest.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.uom.seat.api.CompanyApi;
import com.uom.seat.company.dto.CompanyRequest;
import com.uom.seat.company.dto.CompanyResponse;
import com.uom.seat.util.AccessTokenUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/companies")
@Api(description = "The company API for company management tasks")
public class CompanyController {
	
	private static final Logger logger = Logger.getLogger(CompanyController.class);
	
	@Autowired
	private CompanyApi companyApi;
	
	@ApiOperation(value = "Register company.", response = Integer.class, consumes = "application/json")
	@ApiResponses(value = {
			// @formatter:off
			@ApiResponse(code = 201, message = "The resource is created and return resource id."),
			@ApiResponse(code = 400, message = "Invalid API argument.")
			// @formatter:on
	})
	@PostMapping()
	public ResponseEntity<Integer> registerOrganization(
//			@ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
			@ApiParam(value = "JSON format of the company request.", required = true) @RequestBody final CompanyRequest company,
			UriComponentsBuilder builder) {

		ResponseEntity<Integer> responseEntity = null;
		logger.info("Register company request is received.");
		logger.debug("Register company request" + company.toString());

		Integer organizationId = companyApi.createCompany(AccessTokenUtil.getBearerToken("authorization"),
				company);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("companies/{companyId}").buildAndExpand(organizationId).toUri());

		responseEntity = new ResponseEntity<Integer>(organizationId, headers, HttpStatus.CREATED);
		return responseEntity;
	}
	
	@ApiOperation(value = "update organization by id.", response = CompanyResponse.class, produces = "application/json")
	@GetMapping("{companyId}")
	public ResponseEntity<CompanyResponse> getCompany(
		//	@ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
			@PathVariable("companyId") final Integer companyId) {

		ResponseEntity<CompanyResponse> responseEntity = null;
		logger.info("Get company by id request is received.");
		CompanyResponse company = companyApi
				.getCompany(AccessTokenUtil.getBearerToken("authorization"), companyId);

		responseEntity = new ResponseEntity<CompanyResponse>(company, HttpStatus.OK);
		return responseEntity;
	}
	
	@ApiOperation(value = "Get organization by id.", response = CompanyResponse.class, produces = "application/json")
	@PutMapping("{companyId}")
	public ResponseEntity<CompanyResponse> getCompany(
			//@ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
			@PathVariable("companyId") final Integer companyId, @RequestBody final CompanyRequest company) {

		ResponseEntity<CompanyResponse> responseEntity = null;
		logger.info("Get company by id request is received.");
		CompanyResponse response = companyApi.updateCompany(companyId, company);

		responseEntity = new ResponseEntity<CompanyResponse>(response, HttpStatus.OK);
		return responseEntity;
	}
	
	


}
