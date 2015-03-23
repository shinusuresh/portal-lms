package com.thoughtservice.portal.lms.admin.attendance;

import java.io.File;
import java.util.List;

import com.thoughtservice.portal.user.attendance.Attendance;

public interface AttendanceParser {

	public List<Attendance> parseAttendance(File attendaFile);
}
