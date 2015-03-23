package com.thoughtservice.portal.lms.admin.task;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.thoughtservice.portal.lms.admin.attendance.AttendanceParserFactory;
import com.thoughtservice.portal.user.User;
import com.thoughtservice.portal.user.attendance.Attendance;
import com.thoughtservice.portal.user.attendance.dao.AttendanceDao;
import com.thoughtservice.portal.user.attendance.dao.AttendanceDaoImpl;
import com.thoughtservice.portal.user.dao.UserDao;

public class AttendanceServiceImpl implements AttendanceService {

	private static final Log LOGGER = LogFactory
			.getLog(AttendanceServiceImpl.class);

	private AttendanceParserFactory attendanceParserFactory;
	private AttendanceDao attendanceDao;
	private UserDao userDao;
	private AdminServices adminServices;

	public void parseUpload(File attendanceFile) {
		// Process only xls at this point
		String fileType = FilenameUtils.getExtension(attendanceFile.getName());
		LOGGER.debug("File type detected is " + fileType);
		if ("xls".equals(fileType)) {
			List<Attendance> attendances = attendanceParserFactory.getParser(
					attendanceFile).parseAttendance(attendanceFile);
			if(attendances != null && attendances.size() > 0) {							
				
				Iterator<Attendance> attendanceIterator = attendances.iterator();
				while(attendanceIterator.hasNext()) {
					Attendance attendance = attendanceIterator.next(); 
					User user = attendance.getUser();
					if(user != null) {
						//Load user from DB and see if user exist
						User loadedUser = userDao.findByEmployeeCode(user.getEmployeeCode());
						if(loadedUser != null) {
							attendance.setUser(loadedUser);
							LOGGER.debug("Populating address record for --> " + loadedUser.getFirstName());				
							LOGGER.debug("shift"
									+ attendance.getShift());
							LOGGER.debug("status"
									+ attendance.getStatus());
						} else {
							LOGGER.error("User is not found on the system. The attendance record is not parsed. Removing the attendance record for that user.");
							//Notify admin about these users
							//adminServices.sendEmailToAdmin(content);
							//Remove attendance record if user is not configured
							attendanceIterator.remove();							
						}
						
					} else {
						LOGGER.error("User could not be created from uploaded excel. Cross verify if uploaded excel "
								+ attendanceFile.getName()
								+ " contains any erraneous data.");
						//Remove attendance record for that user
						attendanceIterator.remove();
					}
					
				}
				
				attendanceDao.addAttendance(attendances);
			} else {
				LOGGER.error("Attendance object is not created. "
						+ "Reason could be user not configured in system or an errored attendance file. "
						+ "Turn on debug and monitor the scheduled job.");
			}
			
		} else {
			LOGGER.error("Uploaded file"
					+ attendanceFile.getName()
					+ " is not in a format which can be processed. "
					+ "Only files with extension xls is supported at this point. Current uploaded application file type is "+fileType);
		}

	}

	public void processUpload() {
		// TODO Auto-generated method stub

	}

	public void saveAttendances(List<Attendance> attendances) {

		AttendanceDao attendanceDao = new AttendanceDaoImpl();
		attendanceDao.addAttendance(attendances);
	}

	public void setAttendanceParserFactory(
			AttendanceParserFactory attendanceParserFactory) {
		this.attendanceParserFactory = attendanceParserFactory;
	}

	public void setAttendanceDao(AttendanceDao attendanceDao) {
		this.attendanceDao = attendanceDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setAdminServices(AdminServices adminServices) {
		this.adminServices = adminServices;
	}

}
