package com.tryzens.portal.lms.common.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.tryzens.portal.holidays.CalendarHolidays;
import com.tryzens.portal.user.request.leaverequest.LeaveRequest;

public class FormatDate {
	String selectedDate;

	public List<Date> dates(String selectedDate) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		this.selectedDate = selectedDate.trim();
		// To split date
		String[] segments = this.selectedDate.split("to");
		List<Date> leaveDates = new ArrayList<Date>();

		for (String segment : segments) {
			leaveDates.add(formatter.parse(segment));
		}
		return leaveDates;

	}

	// Function to calculate days in between two dates
	/*
	 * public int calculateDays(List<Date> leaveDates) { long diffInMillisec =
	 * 0; long diffInDays = 0; Calendar firstDate = null; Calendar secondDate =
	 * null; try { // Create two calendars instances firstDate =
	 * Calendar.getInstance(); secondDate = Calendar.getInstance();
	 * 
	 * // Set the dates
	 * 
	 * firstDate.setTime(leaveDates.get(1));
	 * 
	 * secondDate.setTime(leaveDates.get(0));
	 * 
	 * // Get the difference between two dates in milliseconds diffInMillisec =
	 * firstDate.getTimeInMillis() - secondDate.getTimeInMillis();
	 * 
	 * // Get difference between two dates in days diffInDays = diffInMillisec /
	 * (24 * 60 * 60 * 1000);
	 * 
	 * } catch (Exception ex) { ex.printStackTrace(); } return (int) diffInDays;
	 * 
	 * }
	 */

	/*
	 * public int workingDays(List<Date> leaveDates) { Calendar startCal =
	 * Calendar.getInstance(); startCal.setTime(leaveDates.get(0));
	 * 
	 * Calendar endCal = Calendar.getInstance();
	 * endCal.setTime(leaveDates.get(1));
	 * 
	 * int workDays = 0;
	 * 
	 * // Return 0 if start and end are the same if (startCal.getTimeInMillis()
	 * == endCal.getTimeInMillis()) { return 0; }
	 * 
	 * if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
	 * startCal.setTime(leaveDates.get(1)); endCal.setTime(leaveDates.get(0)); }
	 * 
	 * do { // excluding start date startCal.add(Calendar.DAY_OF_MONTH, 1); if
	 * (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY &&
	 * startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) { ++workDays; } }
	 * while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //
	 * excluding // end // date
	 * 
	 * return workDays; }
	 */
	public int calculateDays(List<Date> leaveDate, String requestType) {
		List<Date> result = new ArrayList<Date>();
		Calendar start = Calendar.getInstance();
		start.setTime(leaveDate.get(0));
		Calendar end = Calendar.getInstance();
		end.setTime(leaveDate.get(1));
		end.add(Calendar.DAY_OF_YEAR, 1); // Add 1 day to endDate to make sure
		// endDate is included into the final list
		while (start.before(end)) {
			result.add(start.getTime());
			start.add(Calendar.DAY_OF_YEAR, 1);
		}
		int workingDays = 0;
		int bankHolidayWorkingDays = 0;
		for (Date date : result) {
			bankHolidayWorkingDays++;
			Calendar temp = Calendar.getInstance();
			temp.setTime(date);
			if ((temp.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
					|| (temp.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) {
				continue;
			} else
				workingDays++;

		}
		if (requestType.equalsIgnoreCase("bankHolidayWorking"))
			return bankHolidayWorkingDays;
		return workingDays;
	}

	public Date escalateDate(LeaveRequest leaveRequest) throws Exception {
		Date escalateDate = null;
		Calendar start = Calendar.getInstance();
		List<Date> dates = new ArrayList<Date>();
		dates.add(leaveRequest.getRequestDate());
		dates.add(leaveRequest.getStartDate());
		int days = calculateDays(dates, "leaveRequest");
		if (days >= 22) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			start.setTime(leaveRequest.getRequestDate());
			int count = 1;
			while (count <= 3) {

				if ((start.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
						|| start.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)// avoid
																				// sundays
																				// and
																				// saturday.
																				// request
																				// will
																				// be
																				// in
																				// hands
																				// of
																				// manager
																				// for
																				// three
																				// working
																				// days
				{
					start.add(Calendar.DAY_OF_YEAR, 1);
					continue;
				} else {
					start.add(Calendar.DAY_OF_YEAR, 1);
					count++;
				}
			}
			String output = sdf.format(start.getTime());
			escalateDate = sdf.parse(output);
		} else
			return null;

		return escalateDate;

	}

	public Date autoApprovalDate(LeaveRequest leaveRequest) throws Exception {
		Date autoApprovalDate;
		Calendar start = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		start.setTime(leaveRequest.getDateOfEscalation());
		int count = 2;
		while (count <= 2) {
			if ((start.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
					|| start.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)

			{
				start.add(Calendar.DAY_OF_YEAR, 1);
				continue;
			} else {
				start.add(Calendar.DAY_OF_YEAR, 1);
				count++;
			}
		}
		String output = sdf.format(start.getTime());
		autoApprovalDate = sdf.parse(output);
		return autoApprovalDate;

	}

	public boolean calendarHolidayCheck(List<Date> requestDates,

			List<CalendarHolidays> calendarHolidaysList, String requestType)throws Exception {


		int flag = 0;
		int count = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(requestDates.get(1));
		requestDates.set(1, calendar.getTime());
		List<Date> result = new ArrayList<Date>();
		Calendar start = Calendar.getInstance();
		result.add(requestDates.get(0));
		start.setTime(requestDates.get(0));
		while (start.getTime().before(requestDates.get(1))) {
			Date date = start.getTime();
			start.add(Calendar.DATE, 1);
			result.add(date);
		}
		// Logic to check if all dates comes with in calendar holidays for bank
		// holiday working request
		// and to check any one date comes in calendar holidays for leave
		// request
		// returns true if all dates comes in between calendar holiday for bank
		// holiday working request
		for (Date date : result) {
			for (CalendarHolidays calendarHolidays : calendarHolidaysList) {
				if (calendarHolidays != null) {
					if (requestType.equalsIgnoreCase("bankHolidayWorking")) { // check
						if (date.compareTo(calendarHolidays.getDate()) == 0) {
							flag = 1;
							count++;
							break;
						} else
							flag = 0;
					}

					else if (date.compareTo(calendarHolidays.getDate()) == 0) {

						return true;
					}
				}
			}
			if (count == result.size())
				return true;
		}
		if (flag == 1)
			return true;

		return false;
	}
	}



