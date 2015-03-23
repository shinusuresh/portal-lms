package com.thoughtservice.portal.workfromhome.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.thoughtservice.portal.user.request.leaverequest.LeaveRequest;
import com.thoughtservice.portal.user.request.workfromhome.WorkFromHome;

@Transactional
public class WorkFromHomeDaoImpl extends HibernateDaoSupport implements
		WorkFromHomeDao {

	public void addRequest(WorkFromHome workfromhome) {
		getHibernateTemplate().save(workfromhome);

	}

	public List<WorkFromHome> findAllApprovedWorkFromRequestByUserId(long userId) {

		String sqlQuery = "select * from work_from_home where status=:status and user_id=:user_id";

		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(WorkFromHome.class).setParameter("user_id", userId)
				.setParameter("status", "Approved");
		List<WorkFromHome> listApprovedLeaves = query.list();
		String sqlQuery1 = "select * from work_from_home where status=:status and user_id=:user_id";
		Query query1 = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery1)
				.addEntity(WorkFromHome.class).setParameter("user_id", userId)
				.setParameter("status", "CancellationRejected");
		listApprovedLeaves.addAll(query1.list());
		return listApprovedLeaves;

	}

	public List<WorkFromHome> findPendingWorkFromHomeRequest(Long approverId) {
		String sqlQuery = "select * from work_from_home where status=:status and approver_id=:approver_id";
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(WorkFromHome.class).setParameter("approver_id", approverId)
				.setParameter("status", "pending");
		List<WorkFromHome> pendingRequest = query.list();
		return pendingRequest;
	}

	public WorkFromHome findRequestByRequestId(Long requestId) {
		WorkFromHome workFromHome = new WorkFromHome();
		List<WorkFromHome> workFromHomeList = (List<WorkFromHome>) getHibernateTemplate()
				.find("from WorkFromHome where id=?", requestId);
		if (workFromHomeList != null) {
			workFromHome = (WorkFromHome) workFromHomeList.get(0);
			return workFromHome;
		} else
			return null;
	}

	public void updateStatus(WorkFromHome workFromHome) {
		Query updateQuery = getSession()
				.createQuery(
						"update WorkFromHome set status=:status, approversComment=:approversComment where id=:requestId");
		updateQuery.setString("status", workFromHome.getStatus());
		updateQuery.setLong("requestId", workFromHome.getId());
		updateQuery.setString("approversComment", workFromHome.getApproversComment());
		updateQuery.setString("approversComment", workFromHome.getApproversComment());
		updateQuery.executeUpdate();
	}

	public List<WorkFromHome> listPendingWorkFromHomeRequestForCancellation(
			Long approverId) {
		String sqlQuery = "select * from work_from_home where status=:status and approver_id=:approver_id";
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(WorkFromHome.class).setParameter("approver_id", approverId)
				.setParameter("status", "waitingCancellation");
		List<WorkFromHome> pendingWorkFromHomeRequestListForCancellationList = query.list();
		return pendingWorkFromHomeRequestListForCancellationList;

	}

	// list all work from home request by year
	public List<WorkFromHome> findAllWorkFromHomeRequestByYear(int year) {
		List<WorkFromHome> workFromHomeRequest = (List<WorkFromHome>) getHibernateTemplate()
				.find("from WorkFromHome where year=?", year);
		return workFromHomeRequest;
	}

	// List all pending work from home request for user
	public List<WorkFromHome> findAllPendingWorkFromRequestByUserId(Long userId) {
		String sqlQuery = "select * from work_from_home where status=:status and user_id=:user_id ORDER BY request_Id DESC" ;
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(WorkFromHome.class).setParameter("user_id", userId)
				.setParameter("status", "pending");
		List<WorkFromHome> pendingRequest = query.list();
		return pendingRequest;
	}

	public List<WorkFromHome> listPendingWorkFromHomeRequestForCancellationToAdmin() {
		List<WorkFromHome> pendingLeaveRequestListForCancellationList = getHibernateTemplate()
				.find("from WorkFromHome where status='waitingCancellation'");
		return pendingLeaveRequestListForCancellationList;
	}

	public List<WorkFromHome> findAllWorkFromHomeRequestByUserId(long userId) {
		String sqlQuery = "select * from work_from_home where user_id=:user_id ORDER BY request_Id DESC";
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(WorkFromHome.class).setParameter("user_id", userId);
		List<WorkFromHome> workFromHomeRequests = query.list();
		return workFromHomeRequests;
	}
	
	public List<WorkFromHome> findAllWorkFromHomeRequest(){
		List<WorkFromHome> workFromHome = getHibernateTemplate().find("from WorkFromHome");
		return workFromHome;
	}

	public List<WorkFromHome> findAllWorkFormHomeRequestsOfLastTwoYearsByUserId(
			long userId, int year) {
		int year2=year+1;
		String sqlQuery = "select * from work_from_home where user_id=:user_id and (year=:year or year=:year2)";
		
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(WorkFromHome.class).setParameter("user_id", userId)
				.setParameter("year", year)
				.setParameter("year2",year2);
		List<WorkFromHome> workFromHomeRequests=query.list();
		return workFromHomeRequests;
	}

	public List<WorkFromHome> findAllWorkFormHomeRequestsOfUserByMonthAndYear(Long id,
			int month,int year) {
		String sqlQuery = "select * from work_from_home where  month(start_Date)=:month and year=:year and user_id=:id and (status='approved' or status='cancellationRejected')";
		
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery).addEntity(WorkFromHome.class)
				.setParameter("month", month)
				.setParameter("year", year)
				.setParameter("id",id);
		List<WorkFromHome> workFromHomeRequest = query.list();
		return workFromHomeRequest;
	}


}
