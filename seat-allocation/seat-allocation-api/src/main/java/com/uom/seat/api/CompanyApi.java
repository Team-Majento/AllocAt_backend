package com.uom.seat.api;

import com.uom.seat.company.dto.CompanyRequest;
import com.uom.seat.company.dto.CompanyResponse;

/**
 * The company API will provide methods to manage company.
 * 
 * @author rangalal
 *
 */
public interface CompanyApi {

	/**
	 * Register company.
	 * 
	 * @param accessToken the access token
	 * @param company
	 * @return the company id
	 */
	public Integer createCompany(String accessToken, CompanyRequest company);

	/**
	 * Get company detail without tenant data.
	 * 
	 * @param accessToken the access token
	 * @param xid         the company/tenant xid
	 * @return the company details @see CompanyResponse
	 */
	public CompanyResponse getCompany(String accessToken, Integer id);

	/**
	 * Get company detail without tenant data.
	 * 
	 * @param accessToken the access token
	 * @param xid         the company/tenant xid
	 * @return the company details @see CompanyResponse
	 */
	public CompanyResponse getCompany(String accessToken, String xid);

	/**
	 * Update company by access token
	 * 
	 * @param accessToken the access token
	 * @param company     the company detail to update
	 */
	public void updateOrganizationByAccessToken(String accessToken, CompanyRequest company);
	
	public CompanyResponse updateCompany(Integer id, CompanyRequest company);

	

}
