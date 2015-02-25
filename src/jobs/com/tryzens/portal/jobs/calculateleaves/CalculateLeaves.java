package com.tryzens.portal.jobs.calculateleaves;

import java.util.Calendar;

import org.springframework.security.core.context.SecurityContextHolder;

import com.tryzens.portal.login.service.PortalUserDetails;
import com.tryzens.portal.user.User;
import com.tryzens.portal.user.dao.UserDao;
import com.tryzens.portal.user.request.bankholidayworking.BankHolidayWorking;
import com.tryzens.portal.user.request.leaverequest.LeaveRequest;
import com.tryzens.portal.user.request.weekendworking.WeekendWorking;
import com.tryzens.portal.user.request.workfromhome.WorkFromHome;
import com.tryzens.portal.userholidays.UserHolidays;
import com.tryzens.portal.userholidays.dao.UserHolidaysDao;

public class CalculateLeaves {
	private UserHolidaysDao userHolidaysDao;
	private UserDao userDao;

	// a batch to put a new entry on each year
	public void addLeave(LeaveRequest leaveRequest) throws Exception {
		UserHolidays userHolidays = new UserHolidays();
		double totalLeaves;
		double remainingLeaves;
		userHolidays = userHolidaysDao.findByUserIdAndYear(leaveRequest
				.getUser().getId(), leaveRequest.getYear());
		//What if there are no userHolidays available
		if(userHolidays != null) {
			if (leaveRequest.getHdFd().equalsIgnoreCase("full Day")) {
				totalLeaves = userHolidays.getLeavesTaken()
						+ leaveRequest.getDays();
				remainingLeaves = userHolidays.getLeavesRemaining()
						- leaveRequest.getDays();
			} else {
				double days = (double) (leaveRequest.getDays()) / 2;
	
				totalLeaves = (userHolidays.getLeavesTaken() + days);
				remainingLeaves = (userHolidays.getLeavesRemaining() - days);
			}
		} else {
			totalLeaves = leaveRequest.getDays();
			User user = userDao.findById(leaveRequest.getUser().getId());
			int annualLeaves = user.getAnnualLeaves();
			remainingLeaves = annualLeaves - leaveRequest.getDays();
			
			//Initialize userHolidays
			userHolidays = new UserHolidays();
			userHolidays.setUser(user);
			userHolidaysDao.addUserHolidays(userHolidays);
		}
		userHolidays.setLeavesTaken(totalLeaves);
		userHolidays.setLeavesRemaining(remainingLeaves);
		userHolidaysDao.updateUserHolidays(userHolidays);
	}

	public void addLeave(WorkFromHome workFromHome) throws Exception {
		UserHolidays userHolidays = userHolidaysDao.findByUserIdAndYear(
				workFromHome.getUser().getId(), workFromHome.getYear());
		double totalDays = userHolidays.getWorkFromHome();
		if (workFromHome.getHdFd().equalsIgnoreCase("full Day")) {
			totalDays += (double) workFromHome.getDays();
		} else {
			double days = (double) workFromHome.getDays() / 2;
			totalDays += days;
		}
		// Long remainingDays=
		// userHolidays.getLeavesRemaining()-workFromHome.getDays();
		userHolidays.setWorkFromHome(totalDays);
		// userHolidays.setLeavesRemaining(remainingDays);
		userHolidaysDao.updateUserHolidays(userHolidays);
	}

	public void addLeave(WeekendWorking weekendWorking) {

		UserHolidays userHolidays = userHolidaysDao.findByUserIdAndYear(
				weekendWorking.getUser().getId(), weekendWorking.getYear());
		double weekendWorkingPaid = userHolidays.getWeekendWorkingPaid();
		double weekendWorkingCompoff = userHolidays.getWeekendWorkingCompoff();
		if ((weekendWorking.getHdFd().equalsIgnoreCase("full Day"))
				|| (weekendWorking.getHdFd().equalsIgnoreCase("fullDay"))) {
			if (weekendWorking.getType().equalsIgnoreCase("compoff")) {
				weekendWorkingCompoff += weekendWorking.getDays();
			} else if (weekendWorking.getType().equalsIgnoreCase("paid")) {
				weekendWorkingPaid += weekendWorking.getDays();
			}

		} else {
			double temp = (double) weekendWorking.getDays() / 2;
			if (weekendWorking.getType().equalsIgnoreCase("compoff")) {
				weekendWorkingCompoff += temp;
			} else if (weekendWorking.getType().equalsIgnoreCase("paid")) {
				weekendWorkingPaid += temp;
			}

		}
		userHolidays.setWeekendWorkingCompoff(weekendWorkingCompoff);
		userHolidays.setWeekendWorkingPaid(weekendWorkingPaid);
		userHolidaysDao.updateUserHolidays(userHolidays);

	}

	public void addLeave(BankHolidayWorking bankHolidayWorking) {

		UserHolidays userHolidays = userHolidaysDao.findByUserIdAndYear(
				bankHolidayWorking.getUser().getId(),
				bankHolidayWorking.getYear());
		double bankHolidayPaid = userHolidays.getBankHolidayWorkingPaid();
		double bankHolidayCompoff = userHolidays.getBankHolidayWorkingCompoff();
		if ((bankHolidayWorking.getHdFd().equalsIgnoreCase("full Day"))
				|| (bankHolidayWorking.getHdFd().equalsIgnoreCase("fullDay"))) {
			if (bankHolidayWorking.getType().equalsIgnoreCase("compoff")) {
				bankHolidayCompoff += bankHolidayWorking.getDays();
			} else if (bankHolidayWorking.getType().equalsIgnoreCase("paid")) {
				bankHolidayPaid += bankHolidayWorking.getDays();
			}

		} else {
			double temp = (double) bankHolidayWorking.getDays() / 2;
			if (bankHolidayWorking.getType().equalsIgnoreCase("compoff")) {
				bankHolidayCompoff += temp;
			} else if (bankHolidayWorking.getType().equalsIgnoreCase("paid")) {
				bankHolidayPaid += temp;
			}

		}
		userHolidays.setBankHolidayWorkingCompoff(bankHolidayCompoff);
		userHolidays.setBankHolidayWorkingPaid(bankHolidayPaid);
		userHolidaysDao.updateUserHolidays(userHolidays);

	}

	public void substractLeave(LeaveRequest leaveRequest) throws Exception {
		double totalLeaves;
		double remainingLeaves;
		UserHolidays userHolidays = new UserHolidays();
		userHolidays = userHolidaysDao.findByUserIdAndYear(leaveRequest
				.getUser().getId(), leaveRequest.getYear());
		if (leaveRequest.getHdFd().equalsIgnoreCase("full Day")) {
			totalLeaves = userHolidays.getLeavesTaken()
					- leaveRequest.getDays();
			remainingLeaves = userHolidays.getLeavesRemaining()
					+ leaveRequest.getDays();
		} else {
			double days = (double) (leaveRequest.getDays()) / 2;

			totalLeaves = (userHolidays.getLeavesTaken() - days);
			remainingLeaves = (userHolidays.getLeavesRemaining() + days);
		}
		userHolidays.setLeavesTaken(totalLeaves);
		userHolidays.setLeavesRemaining(remainingLeaves);
		userHolidaysDao.updateUserHolidays(userHolidays);
	}

	public void substractLeave(WorkFromHome workFromHome) throws Exception {
		UserHolidays userHolidays = userHolidaysDao.findByUserIdAndYear(
				workFromHome.getUser().getId(), workFromHome.getYear());
		double totalDays = userHolidays.getWorkFromHome();
		if (workFromHome.getHdFd().equalsIgnoreCase("full Day")) {
			totalDays -= workFromHome.getDays();
		} else {
			double days = (double) workFromHome.getDays() / 2;
			totalDays -= days;
		}
		// Long remainingDays=
		// userHolidays.getLeavesRemaining()-workFromHome.getDays();
		userHolidays.setWorkFromHome(totalDays);
		// userHolidays.setLeavesRemaining(remainingDays);
		userHolidaysDao.updateUserHolidays(userHolidays);
	}

	// function to return remaining leaves to user
	public UserHolidays remainingLeaves() throws Exception {

		PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		Calendar now = Calendar.getInstance();
		UserHolidays userHolidays = userHolidaysDao
				.findByUserIdAndYear(Long.parseLong(sessionUser.getUserId()),
						now.get(Calendar.YEAR));

		return userHolidays;

	}

	public void substractLeave(BankHolidayWorking bankHolidayWorking) {
		UserHolidays userHolidays = userHolidaysDao.findByUserIdAndYear(
				bankHolidayWorking.getUser().getId(),
				bankHolidayWorking.getYear());
		double bankHolidayPaid = userHolidays.getBankHolidayWorkingPaid();
		double bankHolidayCompoff = userHolidays.getBankHolidayWorkingCompoff();
		if (bankHolidayWorking.getHdFd().equalsIgnoreCase("full Day")) {
			if (bankHolidayWorking.getType().equalsIgnoreCase("compoff")) {
				bankHolidayCompoff -= (double) bankHolidayWorking.getDays();
			} else if (bankHolidayWorking.getType().equalsIgnoreCase("paid")) {
				bankHolidayPaid -= (double) bankHolidayWorking.getDays();
			}

		} else {
			double temp = (double) bankHolidayWorking.getDays() / 2;
			if (bankHolidayWorking.getType().equalsIgnoreCase("compoff")) {
				bankHolidayCompoff -= (double) temp;
			} else if (bankHolidayWorking.getType().equalsIgnoreCase("paid")) {
				bankHolidayPaid -= (double) temp;
			}

		}
		userHolidays.setBankHolidayWorkingCompoff(bankHolidayCompoff);
		userHolidays.setBankHolidayWorkingPaid(bankHolidayPaid);
		userHolidaysDao.updateUserHolidays(userHolidays);

	}

	public void substractLeave(WeekendWorking weekendWorking)

	{
		UserHolidays userHolidays = userHolidaysDao.findByUserIdAndYear(
				weekendWorking.getUser().getId(), weekendWorking.getYear());
		double weekendWorkingPaid = userHolidays.getWeekendWorkingPaid();
		double weekendWorkingCompoff = userHolidays.getWeekendWorkingCompoff();
		if (weekendWorking.getHdFd().equalsIgnoreCase("full Day")) {
			if (weekendWorking.getType().equalsIgnoreCase("compoff")) {
				weekendWorkingCompoff -= (double) weekendWorking.getDays();
			} else if (weekendWorking.getType().equalsIgnoreCase("paid")) {
				weekendWorkingPaid -= (double) weekendWorking.getDays();
			}

		} else {
			double temp = (double) weekendWorking.getDays() / 2;
			if (weekendWorking.getType().equalsIgnoreCase("compoff")) {
				weekendWorkingCompoff -= (double) temp;
			} else if (weekendWorking.getType().equalsIgnoreCase("paid")) {
				weekendWorkingPaid -= (double) temp;
			}

		}
		userHolidays.setWeekendWorkingCompoff(weekendWorkingCompoff);
		userHolidays.setWeekendWorkingPaid(weekendWorkingPaid);
		userHolidaysDao.updateUserHolidays(userHolidays);

	}

	public void setUserHolidaysDao(UserHolidaysDao userHolidaysDao) {
		this.userHolidaysDao = userHolidaysDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
