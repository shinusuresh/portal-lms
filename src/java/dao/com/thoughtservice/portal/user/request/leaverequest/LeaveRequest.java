package com.thoughtservice.portal.user.request.leaverequest;

import java.util.Date;

import com.thoughtservice.portal.PersistentObject;
import com.thoughtservice.portal.user.User;

public class LeaveRequest extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4081659554235792666L;
	private Date requestDate;
	private Date startDate;
	private Date endDate;
	private int year;
	private String status;
	private int days;
	private Date dateOfEscalation;
	private String markedBy;
	private String hdFd;
	private String description;
	private String lmsInternal;
	private User approver;
	private String typeOfRequest;
	User user;
	private String approversComment;
	
	public String getApproversComment() {
		return approversComment;
	}
	public void setApproversComment(String approversComment) {
		this.approversComment = approversComment;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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

	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateOfEscalation() {
		return dateOfEscalation;
	}

	public void setDateOfEscalation(Date dateOfEscalation) {
		this.dateOfEscalation = dateOfEscalation;
	}

	public String getMarkedBy() {
		return markedBy;
	}

	public void setMarkedBy(String markedBy) {
		this.markedBy = markedBy;
	}

	public String getHdFd() {
		return hdFd;
	}

	public void setHdFd(String hdFd) {
		this.hdFd = hdFd;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLmsInternal() {
		return lmsInternal;
	}
	public void setLmsInternal(String lmsInternal) {
		this.lmsInternal = lmsInternal;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
