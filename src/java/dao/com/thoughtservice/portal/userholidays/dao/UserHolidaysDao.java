package com.thoughtservice.portal.userholidays.dao;

import java.util.List;

import com.thoughtservice.portal.userholidays.UserHolidays;

public interface UserHolidaysDao {
	Long addUserHolidays(UserHolidays userHolidays);
	UserHolidays findByUserIdAndYear(Long userId, int year);
	void updateUserHolidays(UserHolidays userHolidays);
	List<UserHolidays> listAllUserHolidays();
}
