package com.thoughtservice.portal.lms.manager.task;

import java.util.List;

import com.thoughtservice.portal.lms.manager.task.ManagerServices;
import com.thoughtservice.portal.user.request.bankholidayworking.BankHolidayWorking;
import com.thoughtservice.portal.user.request.bankholidayworking.dao.BankHolidayWorkingDao;
import com.thoughtservice.portal.user.request.leaverequest.LeaveRequest;
import com.thoughtservice.portal.user.request.leaverequest.dao.LeaveRequestDao;
import com.thoughtservice.portal.user.request.weekendworking.WeekendWorking;
import com.thoughtservice.portal.user.request.weekendworking.dao.WeekendWorkingDao;
import com.thoughtservice.portal.user.request.workfromhome.WorkFromHome;
import com.thoughtservice.portal.workfromhome.dao.WorkFromHomeDao;

public class ManagerServicesImpl implements ManagerServices {
	private BankHolidayWorkingDao bankHolidayWorkingDao;
	private LeaveRequestDao leaveRequestDao;
	private WorkFromHomeDao workFromHomeDao;
	private WeekendWorkingDao weekendWorkingDao;

	public void setLeaveRequestDao(LeaveRequestDao leaveRequestDao) {
		this.leaveRequestDao = leaveRequestDao;
	}

	public void setBankHolidayWorkingDao(
			BankHolidayWorkingDao bankHolidayWorkingDao) {
		this.bankHolidayWorkingDao = bankHolidayWorkingDao;
	}

	public void setWorkFromHomeDao(WorkFromHomeDao workFromHomeDao) {
		this.workFromHomeDao = workFromHomeDao;
	}

	public void setWeekendWorkingDao(WeekendWorkingDao weekendWorkingDao) {
		this.weekendWorkingDao = weekendWorkingDao;

	}

	public List<BankHolidayWorking> listPendingBankHoidayWorkingRequest(
			Long approverId) {
		List<BankHolidayWorking> pendingRequest = bankHolidayWorkingDao
				.findPendingRequest(approverId);
		return pendingRequest;
	}

	public List<LeaveRequest> listPendingLeaveRequest(Long approverId) {

		List<LeaveRequest> pendingRequest = leaveRequestDao
				.findPendingLeaveRequest(approverId);
		return pendingRequest;
	}

	public List<WorkFromHome> listPendingWorkFromHomeRequest(Long approverId) {
		List<WorkFromHome> pendingWorkFromHomeRequest = workFromHomeDao
				.findPendingWorkFromHomeRequest(approverId);
		return pendingWorkFromHomeRequest;

	}

	public List<WeekendWorking> listPendingWeekendWorkingRequest(Long approverId) {
		List<WeekendWorking> pendingWeekendWorkingRequest = weekendWorkingDao
				.findPendingWeekendWorkingRequest(approverId);
		return pendingWeekendWorkingRequest;

	}

	public List<LeaveRequest> listPendingLeaveRequestForCancellation(
			Long approverId) {
		List<LeaveRequest> pendingLeaveRequestForCancellationList = leaveRequestDao
				.listPendingLeaveRequestForCancellation(approverId);
		return pendingLeaveRequestForCancellationList;

	}

	public List<WorkFromHome> listPendingWorkFromHomeRequestForCancellation(
			Long approverId) {
		List<WorkFromHome> pendingWorkFromHomeRequestForCancellationList = workFromHomeDao
				.listPendingWorkFromHomeRequestForCancellation(approverId);
		return pendingWorkFromHomeRequestForCancellationList;

	}

	public List<WeekendWorking> listPendingWeekendWorkingRequestForCancellation(
			Long approverId) {
		List<WeekendWorking> pendingWeekendWorkingRequestForCancellationList = weekendWorkingDao
				.listPendingWeekendWorkingRequestForCancellation(approverId);
		return pendingWeekendWorkingRequestForCancellationList;

	}

	public List<BankHolidayWorking> listPendingBankHolidayWorkingRequestForCancellation(
			Long approverId) {
		List<BankHolidayWorking> pendingBankHolidayWorkingRequestForCancellationList = bankHolidayWorkingDao
				.listPendingBankHolidayWorkingRequestForCancellation(approverId);
		return pendingBankHolidayWorkingRequestForCancellationList;

	}

}
