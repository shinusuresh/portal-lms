package com.tryzens.portal.user;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.tryzens.portal.PersistentObject;
import com.tryzens.portal.role.Role;
import com.tryzens.portal.user.experience.Experience;
import com.tryzens.portal.user.skills.UserSkills;

public class User extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6068062883644788742L;
	private int status;
	private Long employeeCode;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	private Date dateOfBirth;
	private String mobile;
	private String department;
	private String pan;
	private String password;
	transient private String plainPassword;
	private Date dateOfJoining;
	private Date createdDate;
	private int annualLeaves;
	private String address;

	@JsonIgnore
	private Set<Role> roles = new HashSet<Role>();
	@JsonIgnore
	private Set<Experience> experience = new HashSet<Experience>();
	@JsonIgnore
	private Set<UserSkills> userSkills = new HashSet<UserSkills>();
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Experience> getExperience() {
		return experience;
	}

	public void setExperience(Set<Experience> experience) {
		this.experience = experience;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Long getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(Long employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	public int getAnnualLeaves() {
		return annualLeaves;
	}
	public void setAnnualLeaves(int annualLeaves) {
		this.annualLeaves = annualLeaves;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public Set<UserSkills> getUserSkills() {
		return userSkills;
	}

	public void setUserSkills(Set<UserSkills> userSkills) {
		this.userSkills = userSkills;
	}

	
	
	
	

}
