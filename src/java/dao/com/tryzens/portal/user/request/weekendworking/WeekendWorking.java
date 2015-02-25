package com.tryzens.portal.user.request.weekendworking;

import java.util.Date;

import com.tryzens.portal.PersistentObject;
import com.tryzens.portal.user.User;

public class WeekendWorking extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8048216267602235425L;
	private String requestType = "WeekendWorking";
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
	private User user;
	private String markedBy;
	private String lmsInternal;
	private String typeOfRequest;
	private String approversComment;

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
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

	public User getApprover() {
		return approver;
	}

	public void setApprover(User approver) {
		this.approver = approver;
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

	public String getMarkedBy() {
		return markedBy;
	}

	public void setMarkedBy(String markedBy) {
		this.markedBy = markedBy;
	}

	public void setApproversComment(String approversComment) {
		this.approversComment = approversComment;
	}

	public String getApproversComment() {
		return approversComment;
	}

	public String getTypeOfRequest() {
		return typeOfRequest;
	}

	public void setTypeOfRequest(String typeOfRequest) {
		this.typeOfRequest = typeOfRequest;
	}

}
