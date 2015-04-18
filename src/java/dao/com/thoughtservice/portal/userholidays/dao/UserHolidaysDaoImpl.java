package com.thoughtservice.portal.userholidays.dao;


import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.thoughtservice.portal.user.request.leaverequest.LeaveRequest;
import com.thoughtservice.portal.userholidays.UserHolidays;
@Transactional
public class UserHolidaysDaoImpl extends HibernateDaoSupport implements UserHolidaysDao {
	public Long addUserHolidays(UserHolidays userHolidays){
		return (Long) getHibernateTemplate().save(userHolidays);
		
	}
	@SuppressWarnings("unchecked")
	public UserHolidays findByUserIdAndYear(Long userId, int year)
	{
		UserHolidays userHolidays= new UserHolidays();
		//UserHolidays userHolidays= (UserHolidays) getHibernateTemplate().find("select u from UserHolidays u join u.User r where r.id=? and u.year=?", userId, year);
		String sqlQuery = "select * from user_holidays where user_id=:user_id and year=:year ";
		
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(UserHolidays.class).setParameter("user_id", userId).setParameter("year", year).setCacheable(true);
		
		List<UserHolidays> result= query.list();
		
		if(result.isEmpty())
		{
			return null;
		}
		else 
			userHolidays= (UserHolidays)result.get(0);
		return userHolidays;
			
		//return userHolidays;
		
	}
	public void updateUserHolidays(UserHolidays userHolidays) {
		Query updateQuery = getSession()
				.createQuery(
						"update UserHolidays set leavesTaken=:leavesTaken, leavesRemaining=:leavesRemaining, bankHolidayWorkingPaid=:bankHolidayWorkingPaid, bankHolidayWorkingCompoff=:bankHolidayWorkingCompoff, weekendWorkingCompoff=:weekendWorkingCompoff, weekendWorkingPaid=:weekendWorkingPaid, workFromHome=:workFromHome, carryForwardedLeaves=:carryForwardedLeaves  where id=:id");
		updateQuery.setDouble("leavesTaken", userHolidays.getLeavesTaken());
		updateQuery.setDouble("leavesRemaining", userHolidays.getLeavesRemaining());
		updateQuery.setDouble("bankHolidayWorkingPaid", userHolidays.getBankHolidayWorkingPaid());
		updateQuery.setDouble("bankHolidayWorkingCompoff", userHolidays.getBankHolidayWorkingCompoff());
		updateQuery.setDouble("weekendWorkingCompoff",userHolidays.getWeekendWorkingCompoff());
		updateQuery.setDouble("weekendWorkingPaid",userHolidays.getWeekendWorkingPaid());
		updateQuery.setDouble("workFromHome", userHolidays.getWorkFromHome());
		updateQuery.setDouble("carryForwardedLeaves", userHolidays.getCarryForwardedLeaves());
		updateQuery.setLong("id", userHolidays.getId());
		updateQuery.executeUpdate();

	}
	public List<UserHolidays> listAllUserHolidays() {
		List<UserHolidays> userHolidays = (List<UserHolidays>) getHibernateTemplate()
				.find("from UserHolidays");
		return userHolidays;
	}

}
