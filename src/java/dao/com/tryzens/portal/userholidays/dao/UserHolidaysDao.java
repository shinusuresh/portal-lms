package com.tryzens.portal.userholidays.dao;

import java.util.List;

import com.tryzens.portal.userholidays.UserHolidays;

public interface UserHolidaysDao {
	Long addUserHolidays(UserHolidays userHolidays);
	UserHolidays findByUserIdAndYear(Long userId, int year);
	void updateUserHolidays(UserHolidays userHolidays);
	List<UserHolidays> listAllUserHolidays();
}
