package com.tryzens.portal.user.request.weekendworking.dao;

import java.util.List;

import com.tryzens.portal.user.request.leaverequest.LeaveRequest;
import com.tryzens.portal.user.request.weekendworking.WeekendWorking;

public interface WeekendWorkingDao {

	public void addWeekendRequest(WeekendWorking weekendWorking);

	List<WeekendWorking> findAllApprovedWeekendWorkingRequestByUserId(
			Long userId);

	List<WeekendWorking> findPendingWeekendWorkingRequest(Long approverId);

	WeekendWorking findRequestByRequestId(Long requestId);

	void updateStatus(WeekendWorking weekendWorking);

	List<WeekendWorking> listPendingWeekendWorkingRequestForCancellation(
			Long approverId);

	List<WeekendWorking> findAllWeekendWorkingRequestByYear(int year);

	List<WeekendWorking> findAllPendingWeekendWorkingRequestByUserId(Long userId);

	List<WeekendWorking> listPendingWeekendWorkingRequestForCancellationToAdmin();

	List<WeekendWorking> findAllWeekendWorkingRequestByUserId(long userId);

	List<WeekendWorking> findAllWeekendWorkingRequest();
	
	List<WeekendWorking> findAllWeekendWorkingRequestsOfLastTwoYearsByUserId(Long userId,int year);

	public List<WeekendWorking> findAllWeekendWorkingRequestOfUserByMonthAndYear(
			Long id,int month,int year);
	
}