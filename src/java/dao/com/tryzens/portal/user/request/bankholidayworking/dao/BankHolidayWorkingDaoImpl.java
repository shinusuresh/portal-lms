package com.tryzens.portal.user.request.bankholidayworking.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.tryzens.portal.user.request.bankholidayworking.BankHolidayWorking;
import com.tryzens.portal.user.request.leaverequest.LeaveRequest;
import com.tryzens.portal.user.request.workfromhome.WorkFromHome;

@Transactional
public class BankHolidayWorkingDaoImpl extends HibernateDaoSupport implements
		BankHolidayWorkingDao {

	public void saveRequest(BankHolidayWorking bankHolidayWorking) {
		getHibernateTemplate().save(bankHolidayWorking);
	}

	public List<BankHolidayWorking> findPendingRequest(Long approverId) {
		String sqlQuery = "select * from bank_holiday_working where status=:status and approver_id=:approver_id";
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(BankHolidayWorking.class)
				.setParameter("approver_id", approverId)
				.setParameter("status", "pending");
		List<BankHolidayWorking> pendingRequest = query.list();
		return pendingRequest;
	}

	public List<BankHolidayWorking> findAllBankHolidayWorkingRequestByUserId(
			long userId) {

		String sqlQuery = "select * from bank_holiday_working where user_id=:user_id  ORDER BY request_Id DESC";

		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(BankHolidayWorking.class)
				.setParameter("user_id", userId);
		List<BankHolidayWorking> bankHolidayWorkingRequests = query.list();
		return bankHolidayWorkingRequests;
	}

	public BankHolidayWorking findRequestByRequestId(Long requestId) {
		BankHolidayWorking bankHolidayWorking = new BankHolidayWorking();
		List bankHolidayWorkingList = (List<BankHolidayWorking>) getHibernateTemplate()
				.find("from BankHolidayWorking where id=?", requestId);

		if (bankHolidayWorkingList != null) {
			bankHolidayWorking = (BankHolidayWorking) bankHolidayWorkingList
					.get(0);
			return bankHolidayWorking;
		} else
			return null;
	}

	public void updateStatus(BankHolidayWorking bankHolidayWorking) {
		Query updateQuery = getSession()
				.createQuery(
						"update BankHolidayWorking set status=:status, approversComment=:approversComment where id=:requestId");
		updateQuery.setString("status", bankHolidayWorking.getStatus());
		updateQuery.setLong("requestId", bankHolidayWorking.getId());
		updateQuery.setString("approversComment", bankHolidayWorking.getApproversComment());
		updateQuery.setString("approversComment", bankHolidayWorking.getApproversComment());
		updateQuery.executeUpdate();

	}

	// List all approved bank holiday working requests by user id
	public List<BankHolidayWorking> findAllApprovedBankHolidayWorkingRequestByUserId(
			Long userId) {
		String sqlQuery = "select * from bank_holiday_working where status=:status and user_id=:user_id";
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(BankHolidayWorking.class)
				.setParameter("user_id", userId)
				.setParameter("status", "Approved");
		List<BankHolidayWorking> listApprovedLeaves = query.list();
		String sqlQuery1 = "select * from bank_holiday_working where status=:status and user_id=:user_id";
		Query query1 = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery1)
				.addEntity(BankHolidayWorking.class)
				.setParameter("user_id", userId)
				.setParameter("status", "CancellationRejected");
		listApprovedLeaves.addAll(query1.list());
		return listApprovedLeaves;

	}

	public List<BankHolidayWorking> findAllPendingBankHolidayWorkingRequestByUserId(
			Long userId) {
		String sqlQuery = "select * from bank_holiday_working where status=:status and user_id=:user_id ORDER BY request_Id DESC";

		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(BankHolidayWorking.class)
				.setParameter("user_id", userId)
				.setParameter("status", "pending");
		List<BankHolidayWorking> pendingRequest = query.list();

		return pendingRequest;
	}

	public List<BankHolidayWorking> listPendingBankHolidayWorkingRequestForCancellation(
			Long approverId) {
		String sqlQuery = "select * from bank_holiday_working where status=:status and approver_id=:approver_id";
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(BankHolidayWorking.class)
				.setParameter("approver_id", approverId)
				.setParameter("status", "waitingCancellation");
		List<BankHolidayWorking> pendingBankHolidayRequestListForCancellationList = query
				.list();
		return pendingBankHolidayRequestListForCancellationList;

	}

	public List<BankHolidayWorking> findAllBankHolidayWorkingRequestByYear(
			int year) {
		List<BankHolidayWorking> bankHolidayWorking = (List<BankHolidayWorking>) getHibernateTemplate()
				.find("from BankHolidayWorking where year=?", year);
		return bankHolidayWorking;
	}

	public List<BankHolidayWorking> listPendingBankHolidayWorkingRequestForCancellationToAdmin() {
		List<BankHolidayWorking> pendingLeaveRequestListForCancellationList = getHibernateTemplate()
				.find("from BankHolidayWorking where status='waitingCancellation'");
		return pendingLeaveRequestListForCancellationList;

	}

	public List<BankHolidayWorking> findAllBankHolidayWorkingRequestsOfLastTwoYearsByUserId(
			long userId, int year) {
		int year2=year+1;
		String sqlQuery = "select * from bank_holiday_working where user_id=:user_id and (year=:year or year=:year2)";
		
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(BankHolidayWorking.class).setParameter("user_id", userId)
				.setParameter("year", year)
				.setParameter("year2",year2);
		List<BankHolidayWorking> bankHolidayWorkingRequests=query.list();
		return bankHolidayWorkingRequests;
	}

	public List<BankHolidayWorking> findAllBankHolidayWorkingRequestsOfUserByMonthAndYear(
			Long id, int month,int year) {
	String sqlQuery = "select * from bank_holiday_working where  month(start_Date)=:month and year=:year and user_id=:id and (status='approved' or status='cancellationRejected')";
		
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery).addEntity(BankHolidayWorking.class)
				.setParameter("month", month)
				.setParameter("year", year)
				.setParameter("id",id);
		List<BankHolidayWorking> bankHolidayWorkingRequests = query.list();
		return bankHolidayWorkingRequests;
	}


}
