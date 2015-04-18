package com.thoughtservice.portal.user.attendance.dao;

import java.util.Date;
import java.util.List;

import com.thoughtservice.portal.user.attendance.Attendance;

public interface AttendanceDao {

	public void addAttendance(List<Attendance> attendance);

	public void updateAttendance(Attendance attendance);

	public void deleteAttendance(Long id);

	public List<Attendance> getAllAttendance();

	public Attendance findById(Long id);

	public List<Attendance> findByEmployee(Long userId);

	public Attendance findByEmployeeAndDay(Long userId, Date date);

}
