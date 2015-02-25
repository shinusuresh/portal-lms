package com.tryzens.portal.user.attendance.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tryzens.portal.user.User;
import com.tryzens.portal.user.attendance.Attendance;
import com.tryzens.portal.user.request.leaverequest.LeaveRequest;

public class AttendanceDaoImpl extends HibernateDaoSupport implements
		AttendanceDao {
      public int i=1;
	
      public void addAttendance(List<Attendance> attendances) {
		
		 getHibernateTemplate().saveOrUpdateAll(attendances);
	}

	public void updateAttendance(Attendance attendance) {		
		getHibernateTemplate().update(attendance);
	}

	public void deleteAttendance(Long id) {
		getHibernateTemplate().delete(id);

	}

	@SuppressWarnings("unchecked")
	public List<Attendance> getAllAttendance() {
    return getHibernateTemplate().find("from Attendance");
	}
		

	public Attendance findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Attendance> findByEmployee(Long userId) {		
		return getHibernateTemplate().find("from Attendance where user.id=?", userId);
	}

	public Attendance findByEmployeeAndDay(Long userId, Date date) {
		// TODO Auto-generated method stub
		return null;
	}



}
