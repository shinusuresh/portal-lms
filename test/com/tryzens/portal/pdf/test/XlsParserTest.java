package com.tryzens.portal.pdf.test;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.tryzens.portal.lms.admin.attendance.AttendanceParser;
import com.tryzens.portal.lms.admin.attendance.XlsAttendanceParser;
import com.tryzens.portal.lms.admin.task.AttendanceService;
import com.tryzens.portal.lms.admin.task.AttendanceServiceImpl;
import com.tryzens.portal.user.attendance.Attendance;

@RunWith(value = BlockJUnit4ClassRunner.class)
public class XlsParserTest {
	@Test
	public void parseTestXlsData() {
		AttendanceServiceImpl attendanceService = new AttendanceServiceImpl();
		AttendanceParser parser = new XlsAttendanceParser();
	List<Attendance> attendances=parser.parseAttendance(new File("C:/attendance/archive/attendance test1.xls"));
	//attendanceService.print();
	//if(attendances.isEmpty())
//	attendanceService.saveAttendances(attendances);
	}

	
}
