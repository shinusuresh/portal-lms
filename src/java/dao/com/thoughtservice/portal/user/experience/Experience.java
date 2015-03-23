package com.thoughtservice.portal.user.experience;

import java.util.Date;

import com.thoughtservice.portal.PersistentObject;
import com.thoughtservice.portal.user.User;

public class Experience extends PersistentObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6518577624998391548L;
	private String companyName;	
	private String designation;
	private Date startDate;
	private Date endDate;
	
	private User user;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
	
}
