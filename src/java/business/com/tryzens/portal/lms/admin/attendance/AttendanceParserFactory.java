package com.tryzens.portal.lms.admin.attendance;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

public class AttendanceParserFactory {

	private AttendanceParser xlsxAttendanceParser;
	private AttendanceParser xlsAttendanceParser;

	public AttendanceParser getParser(File attendanceFile) {
		if (attendanceFile == null) {
			return null;
		}

		if (FilenameUtils.isExtension(attendanceFile.getName(), "xlsx")) {
			return xlsxAttendanceParser;
		} else if (FilenameUtils.isExtension(attendanceFile.getName(), "xls")) {
			return xlsAttendanceParser;
		}
		return null;
	}

	public void setXlsxAttendanceParser(AttendanceParser xlsxAttendanceParser) {
		this.xlsxAttendanceParser = xlsxAttendanceParser;
	}

	public void setXlsAttendanceParser(AttendanceParser xlsAttendanceParser) {
		this.xlsAttendanceParser = xlsAttendanceParser;
	}

}
