package com.uom.seat.company.dto;

import java.io.Serializable;

public class CompanyRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6959163024793906977L;

	// mandatory
	private String name;
	// mandatory
	private String address;
	// mandatory
	private String contactNumber;
	// mandatory
	private String email;
	// optional
	private String mobile;
	// optional
	private String fax;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber the contactNumber to set
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	@Override
	public String toString() {
		return "Company [name=" + name + ", address=" + address + ", contactNumber=" + contactNumber + ", email="
				+ email + ", mobile=" + mobile + ", fax=" + fax + "]";
	}

}
