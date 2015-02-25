package com.tryzens.portal.lms.user.task;

import java.util.List;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.JDBCException;
import org.springframework.mail.MailSendException;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import com.tryzens.portal.lms.admin.task.AdminServicesImpl;
import com.tryzens.portal.lms.admin.utils.AdminUtils;
import com.tryzens.portal.lms.mail.AccountActivationEmail;
import com.tryzens.portal.lms.mail.ForgotPasswordEmail;
import com.tryzens.portal.lms.mail.request.BankHolidayWorkingCancellationRequestNotification;
import com.tryzens.portal.lms.mail.request.BankHolidayWorkingRequestNotification;
import com.tryzens.portal.lms.mail.request.LeaveCancellationRequestNotification;
import com.tryzens.portal.lms.mail.request.WeekendWorkingCancellationRequestNotification;
import com.tryzens.portal.lms.mail.request.WorkFromHomeCancellationRequestNotification;
//import com.tryzens.portal.lms.mail.MailAPI;
import com.tryzens.portal.user.User;
import com.tryzens.portal.user.dao.RoleDao;
import com.tryzens.portal.user.dao.UserDao;
import com.tryzens.portal.user.request.bankholidayworking.BankHolidayWorking;
import com.tryzens.portal.user.request.bankholidayworking.dao.BankHolidayWorkingDao;
import com.tryzens.portal.user.request.leaverequest.LeaveRequest;
import com.tryzens.portal.user.request.leaverequest.dao.LeaveRequestDao;
import com.tryzens.portal.user.request.weekendworking.WeekendWorking;
import com.tryzens.portal.user.request.weekendworking.dao.WeekendWorkingDao;
import com.tryzens.portal.user.request.workfromhome.WorkFromHome;
import com.tryzens.portal.workfromhome.dao.WorkFromHomeDao;

public class UserServicesImpl implements UserServices {
	private static final Log LOGGER = LogFactory.getLog(UserServicesImpl.class);
	private BankHolidayWorkingDao bankHolidayWorkingDao;
	private UserDao userDao;
	private LeaveRequestDao leaveRequestDao;
	private ForgotPasswordEmail forgotPasswordEmail;
	private PasswordEncoder passwordEncoder;
	private WorkFromHomeDao workFromHomeDao;
	private WeekendWorkingDao weekendWorkingDao;
	
	private BankHolidayWorkingRequestNotification bankHolidayWorkingRequestNotification;
	private LeaveCancellationRequestNotification leaveCancellationRequestNotification;
	private WorkFromHomeCancellationRequestNotification workFromHomeCancellationRequestNotification;
	private BankHolidayWorkingCancellationRequestNotification bankHolidayWorkingCancellationRequestNotification;
    private WeekendWorkingCancellationRequestNotification weekendWorkingCancellationRequestNotification;
	// private MailAPI email;
	public void bankHolidayWorkingRequest(BankHolidayWorking bankHolidayWorking) throws Exception {
		bankHolidayWorkingDao.saveRequest(bankHolidayWorking);
		bankHolidayWorkingRequestNotification.setData(bankHolidayWorking);
		bankHolidayWorkingRequestNotification.sendEmail();

	}
	
	// find request by request id
	public BankHolidayWorking getBankHolidayRequest(Long requestId)throws Exception {
		BankHolidayWorking bankHolidayWorking = bankHolidayWorkingDao
				.findRequestByRequestId(requestId);
		return bankHolidayWorking;
	}

	// find request by request id
	public  WeekendWorking getWeekendWorkingRequest(Long requestId) {
		 WeekendWorking  weekendWorking =  weekendWorkingDao
				.findRequestByRequestId(requestId);
		return  weekendWorking;
	}

	
	// find request by request id
	public WorkFromHome getWorkFromHomeRequest(Long requestId) {
		WorkFromHome workFromHome = workFromHomeDao
				.findRequestByRequestId(requestId);
		return workFromHome;
	
	}
	// find request by request id
	public LeaveRequest getLeaveRequest(Long requestId) {
		LeaveRequest leaveRequest = leaveRequestDao
				.findRequestByRequestId(requestId);
		return leaveRequest;
	}

	// update a bankHolidayworking request
	public void updatebankHolidayWorkingRequest(
			BankHolidayWorking bankHolidayWorking) throws Exception {
		bankHolidayWorkingDao.updateStatus(bankHolidayWorking);
		if(bankHolidayWorking.getStatus().equalsIgnoreCase("waitingCancellation"))
		{
		bankHolidayWorkingCancellationRequestNotification.setData(bankHolidayWorking);
		bankHolidayWorkingCancellationRequestNotification.sendEmail();
		}

	}

	// update a work from home request

	public void updateWeekendWorkingRequest(
			 WeekendWorking  weekendWorking) throws MessagingException {

		weekendWorkingDao.updateStatus(weekendWorking);
		if(weekendWorking.getStatus().equalsIgnoreCase("waitingCancellation"))
		{
		weekendWorkingCancellationRequestNotification.setData(weekendWorking);
		weekendWorkingCancellationRequestNotification.sendEmail();
		}
	}
	
	
	
	public void updateWorkFromHomeRequest(
			WorkFromHome workFromHome) throws MessagingException {

		workFromHomeDao.updateStatus(workFromHome);
		if(workFromHome.getStatus().equalsIgnoreCase("waitingCancellation"))
		{
		workFromHomeCancellationRequestNotification.setData(workFromHome);
		workFromHomeCancellationRequestNotification.sendEmail();
		}
	}

	// update a leave request

	public void updateLeaveRequest(
			LeaveRequest leaveRequest) throws Exception {
		leaveRequestDao.updateStatus(leaveRequest);
		if(leaveRequest.getStatus().equalsIgnoreCase("waitingCancellation"))
		{
		leaveCancellationRequestNotification.setData(leaveRequest);
		leaveCancellationRequestNotification.sendEmail();
		}
		

	}
	
	public boolean forgotPassword(User user) {
		if (user != null) {
			try {
				// Generate a random password and encode it
				String plainPassword = AdminUtils.generateRandomPassword();
				String password = passwordEncoder.encodePassword(plainPassword,
						null);

				user.setPassword(password);
				user.setPlainPassword(plainPassword);

				// Update new password to table
				userDao.updateUser(user);

				forgotPasswordEmail.setData(user);
				forgotPasswordEmail.sendEmail();
			} catch (MessagingException e) {
				LOGGER.error(e);
			} catch (MailSendException me) {
				LOGGER.error(me);
			}
			return true;
		}
		return false;
	}

	public User findUserByUserName(String userName) {
		return userDao.findByEmail(userName);
	}

	public void updateUser(User user)throws Exception {
		userDao.updateUser(user);
	}

	public void setBankHolidayWorkingDao(
			BankHolidayWorkingDao bankHolidayWorkingDao) {
		this.bankHolidayWorkingDao = bankHolidayWorkingDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setLeaveRequestDao(LeaveRequestDao leaveRequestDao) {
		this.leaveRequestDao = leaveRequestDao;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public void setForgotPasswordEmail(ForgotPasswordEmail forgotPasswordEmail) {
		this.forgotPasswordEmail = forgotPasswordEmail;
	}

	public void setWorkFromHomeDao(WorkFromHomeDao workFromHomeDao) {
		this.workFromHomeDao = workFromHomeDao;
	}

	public void setWeekendWorkingDao(WeekendWorkingDao weekendWorkingDao) {
		this.weekendWorkingDao = weekendWorkingDao;
	}


	public void setBankHolidayWorkingRequestNotification(
			BankHolidayWorkingRequestNotification bankHolidayWorkingRequestNotification) {
		this.bankHolidayWorkingRequestNotification = bankHolidayWorkingRequestNotification;
	}

	public void setLeaveCancellationRequestNotification(
			LeaveCancellationRequestNotification leaveCancellationRequestNotification) {
		this.leaveCancellationRequestNotification = leaveCancellationRequestNotification;
	}

	public void setWorkFromHomeCancellationRequestNotification(
			WorkFromHomeCancellationRequestNotification workFromHomeCancellationRequestNotification) {
		this.workFromHomeCancellationRequestNotification = workFromHomeCancellationRequestNotification;
	}

	public void setBankHolidayWorkingCancellationRequestNotification(
			BankHolidayWorkingCancellationRequestNotification bankHolidayWorkingCancellationRequestNotification) {
		this.bankHolidayWorkingCancellationRequestNotification = bankHolidayWorkingCancellationRequestNotification;
	}



	

	public void setWeekendWorkingCancellationRequestNotification(
			WeekendWorkingCancellationRequestNotification weekendWorkingCancellationRequestNotification) {
		this.weekendWorkingCancellationRequestNotification = weekendWorkingCancellationRequestNotification;
	}







}
