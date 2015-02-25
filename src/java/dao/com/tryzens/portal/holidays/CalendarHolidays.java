package com.tryzens.portal.holidays;

import java.util.Date;

import com.tryzens.portal.PersistentObject;

public class CalendarHolidays extends PersistentObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 592172886319529306L;
	private Date date;
	private String holidayType;
	private String description;
	private int year;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getHolidayType() {
		return holidayType;
	}

	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
