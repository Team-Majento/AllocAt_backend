package com.uom.seat.user.dto;

import java.io.Serializable;

public class UserRequest implements Serializable {

	private static final long serialVersionUID = 651550977567953017L;

	private Integer userId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String userName;
	private String password;
	private String email;
	private String contactNo ;
	private String address;
	private Character gender;
	private String imageURL ;
	private Integer userType;
	private Integer managersEID ;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getManagersEID() {
		return managersEID;
	}

	public void setManagersEID(Integer managersEID) {
		this.managersEID = managersEID;
	}

	@Override
	public String toString() {
		return "UserRequest{" +
				"userId=" + userId +
				", firstName='" + firstName + '\'' +
				", middleName='" + middleName + '\'' +
				", lastName='" + lastName + '\'' +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", contactNo='" + contactNo + '\'' +
				", address='" + address + '\'' +
				", gender=" + gender +
				", imageURL='" + imageURL + '\'' +
				", userType=" + userType +
				", managersEID=" + managersEID +
				'}';
	}
}
