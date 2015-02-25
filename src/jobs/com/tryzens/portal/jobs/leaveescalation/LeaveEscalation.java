package com.tryzens.portal.jobs.leaveescalation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tryzens.portal.lms.common.date.FormatDate;
import com.tryzens.portal.user.User;
import com.tryzens.portal.user.dao.UserDao;
import com.tryzens.portal.user.request.bankholidayworking.dao.BankHolidayWorkingDao;
import com.tryzens.portal.user.request.leaverequest.LeaveRequest;
import com.tryzens.portal.user.request.leaverequest.dao.LeaveRequestDao;
import com.tryzens.portal.workfromhome.dao.WorkFromHomeDao;
//add code if escalation is applicable for work from home and auto approval
public class LeaveEscalation {
	private LeaveRequestDao leaveRequestDao;
	private BankHolidayWorkingDao bankHolidayWorkingDao;
	private WorkFromHomeDao workFromHomeDao;
	private UserDao userDao;

	public void escalateToAdmin() throws Exception {
		Date currentDate = new Date();
		List<LeaveRequest> requestList = new ArrayList<LeaveRequest>();
		List<User> allAdmin = userDao.findAllUserByRole("ROLE_ADMIN");
		requestList = leaveRequestDao.listPendingLeaveRequestForEscalation();
		for (LeaveRequest leaveRequest : requestList) {
			if(!leaveRequest.getLmsInternal().equals("ESCALATED_TO_ADMIN"))
			if (leaveRequest.getDateOfEscalation().compareTo(currentDate) == 0) {

				leaveRequest.setLmsInternal("ESCALATED_TO_ADMIN");
				// function for auto approval date
				leaveRequest.setDateOfEscalation(new FormatDate().autoApprovalDate(leaveRequest));
				leaveRequestDao.updateStatus(leaveRequest);
				// notification
				notifyAllAdmin(leaveRequest, allAdmin);
			}
		}
	}
	public void autoApprovalJob(){
		Date currentDate = new Date();
		List<LeaveRequest> requestList = new ArrayList<LeaveRequest>();
		requestList = leaveRequestDao.findPendingLeaveRequestForAutoApproval();
		for (LeaveRequest leaveRequest : requestList) {
			if (leaveRequest.getDateOfEscalation().compareTo(currentDate) == 0) {
				leaveRequest.setStatus("Approved");
				leaveRequest.setLmsInternal("ESCALATED_TO_AUTO_APPROVAL");
				leaveRequestDao.updateStatus(leaveRequest);
				// notification
			}
	}
	}
	
	protected void notifyAllAdmin(LeaveRequest leaveRequest, List<User> allAdmin)
	{
		// logic to send mail to multiple recipients
	}

	public void setLeaveRequestDao(LeaveRequestDao leaveRequestDao) {
		this.leaveRequestDao = leaveRequestDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void setBankHolidayWorkingDao(BankHolidayWorkingDao bankHolidayWorkingDao) {
		this.bankHolidayWorkingDao = bankHolidayWorkingDao;
	}
	public void setWorkFromHomeDao(WorkFromHomeDao workFromHomeDao) {
		this.workFromHomeDao = workFromHomeDao;
	}

}
