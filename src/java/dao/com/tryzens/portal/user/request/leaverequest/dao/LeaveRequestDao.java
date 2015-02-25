package com.tryzens.portal.user.request.leaverequest.dao;

import java.util.List;

import com.tryzens.portal.user.request.leaverequest.LeaveRequest;

public interface LeaveRequestDao {

	void addLeaveRequest(LeaveRequest leaveRequest);

	List<LeaveRequest> ListAllLeave();

	List<LeaveRequest> findAllApprovedLeaveRequestByUserId(Long userId);
	
    List<LeaveRequest>findAllLeaveRequestByUserId(Long userId);
    
    List<LeaveRequest> findLeaveRequestByRequestId(Long requestId);
    
    LeaveRequest  findRequestByRequestId(Long requestId);
   
    List<LeaveRequest> findPendingLeaveRequest(Long approverId);

	void updateStatus(LeaveRequest leaveRequest);
	
	List<LeaveRequest> findAllLeaveRequestByYear(int year);
    
	List<LeaveRequest> findAllPendingLeaveRequestByUserId(Long id);
	
	List<LeaveRequest> listPendingLeaveRequestForCancellation(Long approverId);

	
	List<LeaveRequest> listPendingLeaveRequestForEscalation();
	
	//public void updateLmsInternal(LeaveRequest leaveRequest) ;
	
	List<LeaveRequest> listPendingLeaveRequestForCancellationToAdmin();
	
	List<LeaveRequest> escalatedLeaveList();
	
	List<LeaveRequest> findPendingLeaveRequestForAutoApproval();
	
	List<LeaveRequest> findAllLeaveRequestsOfLastTwoYearsByUserId(Long userId,int year);

	List<LeaveRequest> findAllLeaveRequestsOfUserByMonthAndYear(Long id, int month,int year);
	
}
