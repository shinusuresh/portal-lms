package com.tryzens.portal.user.attendance;

import java.sql.Time;
import java.util.Date;

import com.tryzens.portal.PersistentObject;
import com.tryzens.portal.user.User;

public class Attendance extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1352319792675874126L;

	private User user;
	private Date attendanceDate;
	private String shift;
	private Time shiftInTime;
	private Time shiftOutTime;
	private Time actualInTime;
	private Time actualOutTime;
	private Time workDuration;
	// TODO - Check what is OT in excel
	private Time ot;
	private Time totalDuration;
	private Time lateBy;
	private Time earlyGoingBy;
	private String status;
	private String punchRecords;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public Time getShiftInTime() {
		return shiftInTime;
	}

	public void setShiftInTime(Time shiftInTime) {
		this.shiftInTime = shiftInTime;
	}

	public Time getShiftOutTime() {
		return shiftOutTime;
	}

	public void setShiftOutTime(Time shiftOutTime) {
		this.shiftOutTime = shiftOutTime;
	}

	public Time getActualInTime() {
		return actualInTime;
	}

	public void setActualInTime(Time actualInTime) {
		this.actualInTime = actualInTime;
	}

	public Time getActualOutTime() {
		return actualOutTime;
	}

	public void setActualOutTime(Time actualOutTime) {
		this.actualOutTime = actualOutTime;
	}

	public Time getWorkDuration() {
		return workDuration;
	}

	public void setWorkDuration(Time workDuration) {
		this.workDuration = workDuration;
	}

	public Time getOt() {
		return ot;
	}

	public void setOt(Time ot) {
		this.ot = ot;
	}

	public Time getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(Time totalDuration) {
		this.totalDuration = totalDuration;
	}

	public Time getLateBy() {
		return lateBy;
	}

	public void setLateBy(Time lateBy) {
		this.lateBy = lateBy;
	}

	public Time getEarlyGoingBy() {
		return earlyGoingBy;
	}

	public void setEarlyGoingBy(Time earlyGoingBy) {
		this.earlyGoingBy = earlyGoingBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPunchRecords() {
		return punchRecords;
	}

	public void setPunchRecords(String punchRecords) {
		this.punchRecords = punchRecords;
	}

	public Date getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

}
