package com.thoughtservice.portal.user.request.leaverequest;

import java.util.Date;

public class UserLeaveRequestVO {
	private String requestType="LeaveRequest";
	private String firstName;
	private String lastName;
	private Date requestDate;
	private Date startDate;
	private Date endDate;
	private Long requestId;
	private String status;
	private int days;
	private Date dateOfEscalation;
	private String markedBy;
	private String hdFd;
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
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
	public Long getRequestId() {
		return requestId;
	}
	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
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


}
