package com.uom.seat.company.entity;

import javax.persistence.*;

import com.uom.seat.resource.entity.ResourceEntity;
import org.hibernate.annotations.NaturalId;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
public class CompanyEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// unique reference xid
	@NaturalId
	@Column(name = "xid", nullable = false, unique = true)
	private String xid;

	// mandatory
	@Column(name = "name", nullable = false)
	private String name;

	// mandatory
	@Column(name = "address", nullable = false)
	private String address;

	// mandatory
	@Column(name = "contact_number", nullable = false)
	private String contactNumber;

	// mandatory
	@Column(name = "email", nullable = false)
	private String email;

	// optional
	@Column(name = "mobile", nullable = true)
	private String mobile;

	// optional
	@Column(name = "fax", nullable = true)
	private String fax;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "company")
	private List<ResourceEntity> resources= new ArrayList<>();

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the xid
	 */
	public String getXid() {
		return xid;
	}

	/**
	 * @param xid the xid to set
	 */
	public void setXid(String xid) {
		this.xid = xid;
	}

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

	public List<ResourceEntity> getResources() {
		return resources;
	}

	public void setResources(List<ResourceEntity> resources) {
		this.resources = resources;
	}
}
