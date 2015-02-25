package com.tryzens.portal.lms.admin.task;

import java.io.File;
import java.util.List;

import com.tryzens.portal.user.attendance.Attendance;

public interface AttendanceService {
     public int i=8;
	public void parseUpload(File attendanceFile);
	
	public void processUpload();

	public void saveAttendances(List<Attendance> attendances);

}
