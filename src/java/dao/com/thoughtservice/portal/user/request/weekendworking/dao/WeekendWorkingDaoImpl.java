package com.thoughtservice.portal.user.request.weekendworking.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.thoughtservice.portal.user.request.bankholidayworking.BankHolidayWorking;
import com.thoughtservice.portal.user.request.leaverequest.LeaveRequest;
import com.thoughtservice.portal.user.request.weekendworking.WeekendWorking;

@Transactional
public class WeekendWorkingDaoImpl extends HibernateDaoSupport implements
		WeekendWorkingDao {
	public void addWeekendRequest(WeekendWorking weekendWorking) {
		getHibernateTemplate().save(weekendWorking);
	}

	public List<WeekendWorking> findAllApprovedWeekendWorkingRequestByUserId(
			Long userId) {

		String sqlQuery = "select * from weekend_working where status=:status and user_id=:user_id";

		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(WeekendWorking.class)
				.setParameter("user_id", userId)
				.setParameter("status", "Approved");
		List<WeekendWorking> listApprovedLeaves = query.list();
		String sqlQuery1 = "select * from  weekend_working where status=:status and user_id=:user_id";
		Query query1 = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery1)
				.addEntity(WeekendWorking.class)
				.setParameter("user_id", userId)
				.setParameter("status", "CancellationRejected");
		listApprovedLeaves.addAll(query1.list());
		return listApprovedLeaves;
	}

	public List<WeekendWorking> findPendingWeekendWorkingRequest(Long approverId) {
		String sqlQuery = "select * from weekend_working where status=:status and approver_id=:approver_id";
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(WeekendWorking.class)
				.setParameter("approver_id", approverId)
				.setParameter("status", "pending");
		List<WeekendWorking> pendingRequest = query.list();
		return pendingRequest;
	}

	public WeekendWorking findRequestByRequestId(Long requestId) {
		WeekendWorking weekendWorking = new WeekendWorking();
		List<WeekendWorking> weekendWorkingList = (List<WeekendWorking>) getHibernateTemplate()
				.find("from WeekendWorking where id=?", requestId);
		if (weekendWorkingList != null) {
			weekendWorking = (WeekendWorking) weekendWorkingList.get(0);
			return weekendWorking;
		} else
			return null;
	}

	public void updateStatus(WeekendWorking weekendWorking) {
		Query updateQuery = getSession()
				.createQuery(
						"update WeekendWorking set status=:status,  lmsInternal=:lmsInternal, lastUpdate=:lastUpdate , approversComment=:approversComment where id=:requestId");
		updateQuery.setString("status", weekendWorking.getStatus());
		updateQuery.setLong("requestId", weekendWorking.getId());
		updateQuery.setString("lmsInternal", weekendWorking.getLmsInternal());
		updateQuery.setDate("lastUpdate", weekendWorking.getLastUpdate());
		updateQuery.setString("approversComment",
				weekendWorking.getApproversComment());
		updateQuery.executeUpdate();
	}

	public List<WeekendWorking> listPendingWeekendWorkingRequestForCancellation(
			Long approverId) {
		String sqlQuery = "select * from weekend_working where status=:status and approver_id=:approver_id";
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(WeekendWorking.class)
				.setParameter("approver_id", approverId)
				.setParameter("status", "waitingCancellation");
		List<WeekendWorking> pendingWeekendWorkingRequestListForCancellationList = query
				.list();
		return pendingWeekendWorkingRequestListForCancellationList;

	}

	// List all pending weekend working request for user
	public List<WeekendWorking> findAllPendingWeekendWorkingRequestByUserId(
			Long userId) {
		String sqlQuery = "select * from weekend_working where status=:status and user_id=:user_id ORDER BY request_Id DESC";
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(WeekendWorking.class)
				.setParameter("user_id", userId)
				.setParameter("status", "pending");
		List<WeekendWorking> pendingRequest = query.list();
		return pendingRequest;
	}

	public List<WeekendWorking> listPendingWeekendWorkingRequestForCancellationToAdmin() {
		List<WeekendWorking> pendingWeekendWorkingListForCancellationList = getHibernateTemplate()
				.find("from WeekendWorking where status='waitingCancellation'");
		return pendingWeekendWorkingListForCancellationList;
	}

	public List<WeekendWorking> findAllWeekendWorkingRequestByUserId(long userId) {
		String sqlQuery = "select * from weekend_working where user_id=:user_id ORDER BY request_Id DESC";
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(WeekendWorking.class)
				.setParameter("user_id", userId);
		List<WeekendWorking> weekendWorkingRequests = query.list();
		return weekendWorkingRequests;
	}

	public List<WeekendWorking> findAllWeekendWorkingRequest() {
		List<WeekendWorking> weekendWorking = getHibernateTemplate().find(
				"from WeekendWorking");
		return weekendWorking;
	}

	public List<WeekendWorking> findAllWeekendWorkingRequestByYear(int year) {

		List<WeekendWorking> weekendWorking = (List<WeekendWorking>) getHibernateTemplate()
				.find("from WeekendWorking where year=? ORDER BY id DESC", year);
		return weekendWorking;
	}
	
	
	

	public List<WeekendWorking> findAllWeekendWorkingRequestsOfLastTwoYearsByUserId(
			Long userId, int year) {
		int year2=year+1;
		String sqlQuery = "select * from weekend_working where user_id=:user_id and (year=:year or year=:year2)";
		
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(WeekendWorking.class).setParameter("user_id", userId)
				.setParameter("year", year)
				.setParameter("year2",year2);
		List<WeekendWorking> weekendWorkingRequests=query.list();
		return weekendWorkingRequests;
	}

	public List<WeekendWorking> findAllWeekendWorkingRequestOfUserByMonthAndYear(
			Long id,int month,int year) {
		String sqlQuery = "select * from weekend_working where  month(start_Date)=:month and year=:year and user_id=:id and (status='approved' or status='cancellationRejected')";
		
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery).addEntity(WeekendWorking.class)
				.setParameter("month", month)
				.setParameter("year", year)
				.setParameter("id",id);
		List<WeekendWorking> weekendWorkingRequests = query.list();
			return weekendWorkingRequests;
	
	}
	
}
