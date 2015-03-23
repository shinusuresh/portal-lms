package com.thoughtservice.portal.holidays.dao;

import java.util.List;

import com.thoughtservice.portal.holidays.CalendarHolidays;
import com.thoughtservice.portal.user.skills.Skill;

public interface CalendarHolidaysDao {
	CalendarHolidays holidayById(Long id);

	void addHoliday(CalendarHolidays calendarholidays);

	List<CalendarHolidays> findAllHolidays();

	List<CalendarHolidays> findCurrentYearHolidays(int year);

	void deleteHoliday(CalendarHolidays calendarHolidays);

	void updateHoliday(CalendarHolidays calendarHoliday);

}
