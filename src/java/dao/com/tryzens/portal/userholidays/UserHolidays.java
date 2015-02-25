package com.tryzens.portal.userholidays;


import com.tryzens.portal.PersistentObject;
import com.tryzens.portal.user.User;

public class UserHolidays extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1989082612693622096L;
	private int year;
	private double leavesTaken;
	private double bankHolidayWorkingPaid;
	private double bankHolidayWorkingCompoff;
	private double workFromHome;
	private double weekendWorkingPaid;
	private double weekendWorkingCompoff;
	private double leavesRemaining;
	private double carryForwardedLeaves;

	
	private User user;
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getLeavesTaken() {
		return leavesTaken;
	}
	public void setLeavesTaken(double leavesTaken) {
		this.leavesTaken = leavesTaken;
	}
	public double getBankHolidayWorkingPaid() {
		return bankHolidayWorkingPaid;
	}
	public void setBankHolidayWorkingPaid(double bankHolidayWorkingPaid) {
		this.bankHolidayWorkingPaid = bankHolidayWorkingPaid;
	}
	public double getBankHolidayWorkingCompoff() {
		return bankHolidayWorkingCompoff;
	}
	public void setBankHolidayWorkingCompoff(double bankHolidayWorkingCompoff) {
		this.bankHolidayWorkingCompoff = bankHolidayWorkingCompoff;
	}
	public double getWorkFromHome() {
		return workFromHome;
	}
	public void setWorkFromHome(double workFromHome) {
		this.workFromHome = workFromHome;
	}
	public double getLeavesRemaining() {
		return leavesRemaining;
	}
	public void setLeavesRemaining(double leavesRemaining) {
		this.leavesRemaining = leavesRemaining;
	}
	public double getCarryForwardedLeaves() {
		return carryForwardedLeaves;
	}
	public void setCarryForwardedLeaves(double carryForwardedLeaves) {
		this.carryForwardedLeaves = carryForwardedLeaves;
	}
	public double getWeekendWorkingCompoff() {
		return weekendWorkingCompoff;
	}
	public void setWeekendWorkingCompoff(double weekendWorkingCompoff) {
		this.weekendWorkingCompoff = weekendWorkingCompoff;
	}
	public void setWeekendWorkingPaid(double weekendWorkingPaid) {
		this.weekendWorkingPaid = weekendWorkingPaid;
	}
	public double getWeekendWorkingPaid() {
		return weekendWorkingPaid;
	}

}
