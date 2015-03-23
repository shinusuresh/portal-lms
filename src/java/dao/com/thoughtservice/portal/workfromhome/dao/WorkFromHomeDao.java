package com.thoughtservice.portal.workfromhome.dao;

import java.util.List;

import com.thoughtservice.portal.user.request.workfromhome.WorkFromHome;

public interface WorkFromHomeDao {

	void addRequest(WorkFromHome workfromhome);

	List<WorkFromHome> findAllApprovedWorkFromRequestByUserId(long userId);

	List<WorkFromHome> findPendingWorkFromHomeRequest(Long approverId);

	List<WorkFromHome> findAllWorkFromHomeRequestByUserId(long userId);

	void updateStatus(WorkFromHome workFromHome);

	List<WorkFromHome> listPendingWorkFromHomeRequestForCancellation(
			Long approverId);

	WorkFromHome findRequestByRequestId(Long requestId);
	
	List<WorkFromHome> findAllWorkFromHomeRequestByYear(int year);

	List<WorkFromHome> findAllPendingWorkFromRequestByUserId(Long userId);

	List<WorkFromHome> listPendingWorkFromHomeRequestForCancellationToAdmin();
	
	List<WorkFromHome> findAllWorkFromHomeRequest();
	
	List<WorkFromHome> findAllWorkFormHomeRequestsOfLastTwoYearsByUserId(long userId,int year);

	List<WorkFromHome> findAllWorkFormHomeRequestsOfUserByMonthAndYear(Long id,
			int month,int year);
}
