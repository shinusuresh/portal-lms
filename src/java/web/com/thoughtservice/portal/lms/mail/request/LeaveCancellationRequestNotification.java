package com.thoughtservice.portal.lms.mail.request;

import java.util.ArrayList;
import java.util.List;

import com.thoughtservice.portal.PersistentObject;
import com.thoughtservice.portal.lms.mail.RequestNotificationEmail;
import com.thoughtservice.portal.user.User;
import com.thoughtservice.portal.user.dao.UserDao;
import com.thoughtservice.portal.user.request.leaverequest.LeaveRequest;

public class LeaveCancellationRequestNotification extends RequestNotificationEmail{
	LeaveRequest leaveRequest;
	UserDao userDao;
	User sender;
	User recipient;

	@Override
	public void setData(PersistentObject dao) {
		leaveRequest= (LeaveRequest)dao;
		sender= userDao.findById(leaveRequest.getUser().getId());
		recipient= userDao.findById(leaveRequest.getApprover().getId());
		getDataContext().setVariable("from", sender.getFirstName());
		getDataContext().setVariable("days", leaveRequest.getDays());
		getDataContext().setVariable("startDate", leaveRequest.getStartDate());
	}
	@Override
	public String recepient() {
		return recipient.getEmail();
	}


	@Override
	public String subject() {
		return "Leave Cancellation request from"+sender.getFirstName();
	}
	@Override
	public List<String> recepients() {
		List<User> allAdmin= userDao.findAllUserByRole("ROLE_ADMIN");
		List<String> emailAddresses= new ArrayList<String>();
		for (User admin : allAdmin) {
			emailAddresses.add(admin.getEmail());
		}
		return emailAddresses;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


}
