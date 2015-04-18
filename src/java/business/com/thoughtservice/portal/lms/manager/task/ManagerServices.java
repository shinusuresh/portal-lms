package com.thoughtservice.portal.lms.manager.task;

import java.util.List;

import com.thoughtservice.portal.user.request.bankholidayworking.BankHolidayWorking;
import com.thoughtservice.portal.user.request.leaverequest.LeaveRequest;
import com.thoughtservice.portal.user.request.weekendworking.WeekendWorking;
import com.thoughtservice.portal.user.request.workfromhome.WorkFromHome;

public interface ManagerServices {
	List<BankHolidayWorking> listPendingBankHoidayWorkingRequest(Long approverId);

	List<LeaveRequest> listPendingLeaveRequest(Long approverId);

	List<WorkFromHome> listPendingWorkFromHomeRequest(Long approverId);

	List<LeaveRequest> listPendingLeaveRequestForCancellation(Long approverId);

	List<WorkFromHome> listPendingWorkFromHomeRequestForCancellation(
			Long approverId);

	List<BankHolidayWorking> listPendingBankHolidayWorkingRequestForCancellation(
			Long approverId);

	List<WeekendWorking> listPendingWeekendWorkingRequestForCancellation(
			Long approverId);

	List<WeekendWorking> listPendingWeekendWorkingRequest(Long approverId);

}
