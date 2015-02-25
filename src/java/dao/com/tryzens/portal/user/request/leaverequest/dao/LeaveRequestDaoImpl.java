package com.tryzens.portal.user.request.leaverequest.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.tryzens.portal.user.request.bankholidayworking.BankHolidayWorking;
import com.tryzens.portal.user.request.leaverequest.LeaveRequest;
import com.tryzens.portal.userholidays.UserHolidays;

@Transactional
public class LeaveRequestDaoImpl extends HibernateDaoSupport implements
		LeaveRequestDao {
	public void addLeaveRequest(LeaveRequest leaveRequest) {
		getHibernateTemplate().save(leaveRequest);
	}

	public List<LeaveRequest> ListAllLeave() {

		return getHibernateTemplate().find("from LeaveRequest");
	}

	public List<LeaveRequest> findAllApprovedLeaveRequestByUserId(Long userId) {

		String sqlQuery = "select * from Leave_request where status=:status and user_id=:user_id";

		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(LeaveRequest.class).setParameter("user_id", userId)
				.setParameter("status", "Approved");
		List<LeaveRequest> listApprovedLeaves = query.list();
		String sqlQuery1 = "select * from Leave_request where status=:status and user_id=:user_id";
		Query query1 = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery1)
				.addEntity(LeaveRequest.class).setParameter("user_id", userId)
				.setParameter("status", "CancellationRejected");
		listApprovedLeaves.addAll(query1.list());
		return listApprovedLeaves;
	}

	public List<LeaveRequest> findAllLeaveRequestByUserId(Long userId) {
		String sqlQuery = "select * from Leave_request where user_id=:user_id ORDER BY request_Id DESC";
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(LeaveRequest.class).setParameter("user_id", userId);
		List<LeaveRequest> leaveRequest = query.list();
		return leaveRequest;
	}

	public List<LeaveRequest> findPendingLeaveRequest(Long approverId) {
		String sqlQuery = "select * from Leave_request where status=:status and approver_id=:approver_id";
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(LeaveRequest.class)
				.setParameter("approver_id", approverId)
				.setParameter("status", "pending");
		List<LeaveRequest> pendingRequest = query.list();
		return pendingRequest;
	}

	// List requests by request id
	public List<LeaveRequest> findLeaveRequestByRequestId(Long requestId) {
		LeaveRequest leaveRequest;
		List<LeaveRequest> leaveRequestList = (List<LeaveRequest>) getHibernateTemplate()
				.find("from LeaveRequest where id=?", requestId);
		if (leaveRequestList != null) {
			leaveRequest = (LeaveRequest) leaveRequestList.get(0);
			return leaveRequestList;
		} else
			return null;
	}

	// Update status of leave request by manager
	public void updateStatus(LeaveRequest leaveRequest) {
		Query updateQuery = getSession()
				.createQuery(
						"update LeaveRequest set status=:status,  lmsInternal=:lmsInternal, lastUpdate= :lastUpdate , approversComment=:approversComment where id=:requestId");
		updateQuery.setString("status",
				((LeaveRequest) leaveRequest).getStatus());
		updateQuery.setLong("requestId",
				((LeaveRequest) leaveRequest).getId());
		updateQuery.setString("lmsInternal", leaveRequest.getLmsInternal());
		updateQuery.setDate("lastUpdate", leaveRequest.getLastUpdate());
		updateQuery.setString("approversComment", leaveRequest.getApproversComment());
		updateQuery.executeUpdate();
	}

	public List<LeaveRequest> listPendingLeaveRequestForCancellation(
			Long approverId) {
		String sqlQuery = "select * from Leave_request where status=:status and approver_id=:approver_id";
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(LeaveRequest.class)
				.setParameter("approver_id", approverId)
				.setParameter("status", "waitingCancellation");
		List<LeaveRequest> pendingLeaveRequestListForCancellationList = query
				.list();
		return pendingLeaveRequestListForCancellationList;

	}

	public List<LeaveRequest> findAllLeaveRequestByYear(int year) {

		List<LeaveRequest> leaveRequest = (List<LeaveRequest>) getHibernateTemplate()
				.find("from LeaveRequest where year=? ORDER BY id DESC",
						year);
		return leaveRequest;
	}

	public List<LeaveRequest> findAllPendingLeaveRequestByUserId(Long id) {
		String sqlQuery = "select * from Leave_request where status=:status and user_id=:user_id ORDER BY request_Id DESC";

		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(LeaveRequest.class).setParameter("user_id", id)
				.setParameter("status", "pending");
		List<LeaveRequest> pendingRequest = query.list();
		return pendingRequest;

	}

	public List<LeaveRequest> listPendingLeaveRequestForEscalation() {
		List<LeaveRequest> pendingLeaveRequestListForCancellationList = getHibernateTemplate()
				.find("from LeaveRequest where status='pending' and dateOfEscalation is not null");
		return pendingLeaveRequestListForCancellationList;
	}

	public void updateLmsInternal(LeaveRequest leaveRequest) {
		Query updateQuery = getSession()
				.createQuery(
						"update LeaveRequest set lmsInternal=:lmsInternal, dateOfEscalation=:dateOfEscalation where id=:requestId");
		updateQuery.setLong("requestId", leaveRequest.getId());
		updateQuery.setDate("dateOfEscalation",
				leaveRequest.getDateOfEscalation());
		updateQuery.setString("lmsInternal", leaveRequest.getLmsInternal());
		updateQuery.executeUpdate();

	}

	public LeaveRequest findRequestByRequestId(Long requestId) {
		LeaveRequest leaveRequest = new LeaveRequest();
		List<LeaveRequest> leaveRequestList = (List<LeaveRequest>) getHibernateTemplate()
				.find("from LeaveRequest where id=?", requestId);
		if (leaveRequestList != null) {
			leaveRequest = (LeaveRequest) leaveRequestList.get(0);
			return leaveRequest;
		} else
			return null;
	}

	public List<LeaveRequest> listPendingLeaveRequestForCancellationToAdmin() {
		List<LeaveRequest> pendingLeaveRequestListForCancellationList = getHibernateTemplate()
				.find("from LeaveRequest where status='waitingCancellation'");
		return pendingLeaveRequestListForCancellationList;
	}

	public List<LeaveRequest> escalatedLeaveList() {
		List<LeaveRequest> escalatedLeaveList = getHibernateTemplate()
				.find("from LeaveRequest where status= 'pending' and lmsInternal= 'ESCALETED_TO_ADMIN'");
		return escalatedLeaveList;
	}

	public List<LeaveRequest> findPendingLeaveRequestForAutoApproval() {

		List<LeaveRequest> autoApprovalList = getHibernateTemplate()
				.find("from LeaveRequest where status = 'pending' and lmsInternal = 'ESCALATED_TO_ADMIN'");
		return autoApprovalList;
	}

	public List<LeaveRequest> findAllLeaveRequestsOfLastTwoYearsByUserId(
			Long userId, int year) {
		int year2=year+1;
	String sqlQuery = "select * from Leave_request where user_id=:user_id and (year=:year or year=:year2)";
	
	Query query = getHibernateTemplate().getSessionFactory()
			.getCurrentSession().createSQLQuery(sqlQuery)
			.addEntity(LeaveRequest.class).setParameter("user_id", userId)
			.setParameter("year", year)
			.setParameter("year2",year2);
	List<LeaveRequest> leaveRequest = query.list();
		return leaveRequest;
	}

	public List<LeaveRequest> findAllLeaveRequestsOfUserByMonthAndYear(Long id,
			int month,int year) {
		String sqlQuery = "select * from Leave_request where  month(start_Date)=:month and year=:year and user_id=:id and (status='approved' or status='cancellationRejected')";
		
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery).addEntity(LeaveRequest.class)
				.setParameter("month", month)
				.setParameter("year", year)
				.setParameter("id",id);
		List<LeaveRequest> leaveRequest = query.list();
			return leaveRequest;
	}
}
