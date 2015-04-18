package com.thoughtservice.portal.user.request.workfromhome;

import java.util.Date;

import com.thoughtservice.portal.PersistentObject;
import com.thoughtservice.portal.user.User;

public class WorkFromHome extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7379774214141780436L;
	private Date requestDate;
	private Date startDate;
	private Date endDate;
	private int days;
	private int year;
	private String markedBy;
	private String status;
	private User approver;
	private String hdFd;
	private String isVerified;
	private String description;
	private String lmsInternal;
	private String typeOfRequest;
	User user;
	private String approversComment;
	
	public String getApproversComment() {
		return approversComment;
	}
	public void setApproversComment(String approversComment) {
		this.approversComment = approversComment;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(String isVerified) {
		this.isVerified = isVerified;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getMarkedBy() {
		return markedBy;
	}

	public void setMarkedBy(String markedBy) {
		this.markedBy = markedBy;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHdFd() {
		return hdFd;
	}

	public void setHdFd(String hdFd) {
		this.hdFd = hdFd;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLmsInternal() {
		return lmsInternal;
	}

	public void setLmsInternal(String lmsInternal) {
		this.lmsInternal = lmsInternal;
	}

	public User getApprover() {
		return approver;
	}

	public void setApprover(User approver) {
		this.approver = approver;
	}

	public String getTypeOfRequest() {
		return typeOfRequest;
	}

	public void setTypeOfRequest(String typeOfRequest) {
		this.typeOfRequest = typeOfRequest;
	}

}
