package com.tryzens.portal.holidays.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.tryzens.portal.holidays.CalendarHolidays;
import com.tryzens.portal.user.skills.Skill;

@Transactional
public class CalendarHolidaysDaoImpl extends HibernateDaoSupport implements
		CalendarHolidaysDao {
	public void addHoliday(CalendarHolidays calendarholidays) {
		getHibernateTemplate().save(calendarholidays);

	}

	@SuppressWarnings("unchecked")
	public List<CalendarHolidays> findAllHolidays() {
		return getHibernateTemplate().find("from CalendarHolidays");
	}

	public void deleteHoliday(CalendarHolidays calendarHolidays) {

		getHibernateTemplate().delete(calendarHolidays);

	}

	public List<CalendarHolidays> findCurrentYearHolidays(int year) {
		return getHibernateTemplate().find(
				"from CalendarHolidays where year = ?", year);
	}

	public CalendarHolidays holidayById(Long id) {
		return getHibernateTemplate().get(CalendarHolidays.class, id);
	}
	
	public void updateHoliday(CalendarHolidays calendarHoliday) {
		getHibernateTemplate().update(calendarHoliday);
	}

}
