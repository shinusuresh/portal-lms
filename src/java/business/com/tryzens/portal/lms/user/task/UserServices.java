package com.tryzens.portal.lms.user.task;

import javax.mail.MessagingException;

import com.tryzens.portal.user.User;
import com.tryzens.portal.user.request.bankholidayworking.BankHolidayWorking;
import com.tryzens.portal.user.request.leaverequest.LeaveRequest;
import com.tryzens.portal.user.request.weekendworking.WeekendWorking;
import com.tryzens.portal.user.request.workfromhome.WorkFromHome;

public interface UserServices {
	void bankHolidayWorkingRequest(BankHolidayWorking bankHolidayWorking)
			throws Exception;

	User findUserByUserName(String userName);

	/**
	 * Used for sending new generated password to user
	 * 
	 * @param user
	 * @return
	 */
	public boolean forgotPassword(User user);

	void updateUser(User user) throws Exception;

	void updateWeekendWorkingRequest(WeekendWorking weekendWorking)
			throws MessagingException, Exception;

	void updatebankHolidayWorkingRequest(BankHolidayWorking bankHolidayWorking)
			throws MessagingException, Exception;

	void updateLeaveRequest(LeaveRequest leaveRequest) throws Exception;

	void updateWorkFromHomeRequest(WorkFromHome workFromHome)
			throws MessagingException;

	BankHolidayWorking getBankHolidayRequest(Long requestId) throws Exception;

	LeaveRequest getLeaveRequest(Long requestId) throws Exception;;

	WorkFromHome getWorkFromHomeRequest(Long requestId)
			throws MessagingException;

	WeekendWorking getWeekendWorkingRequest(Long requestId) throws Exception;
}
