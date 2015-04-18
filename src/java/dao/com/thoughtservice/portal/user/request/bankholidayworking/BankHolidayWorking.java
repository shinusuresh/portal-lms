package com.thoughtservice.portal.user.request.bankholidayworking;

import java.util.Date;

import com.thoughtservice.portal.PersistentObject;
import com.thoughtservice.portal.user.User;

public class BankHolidayWorking extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6585543720128004339L;

	private String requestType = "BankHolidayWorking";
	private Date requestDate;
	private Date startDate;
	private Date endDate;
	private String status;
	private User approver;
	private String type;
	private int isVerified;
	private int days;
	private String description;
	private int year;
	private String hdFd;
	private String typeOfRequest;
	User user;
	private String lmsInternal;
	private String approversComment;

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(int isVerified) {
		this.isVerified = isVerified;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getHdFd() {
		return hdFd;
	}

	public void setHdFd(String hdFd) {
		this.hdFd = hdFd;
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