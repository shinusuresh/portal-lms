package com.tryzens.portal.lms.mail.request;

import java.util.ArrayList;
import java.util.List;
import com.tryzens.portal.PersistentObject;
import com.tryzens.portal.lms.mail.RequestNotificationEmail;
import com.tryzens.portal.user.User;
import com.tryzens.portal.user.dao.UserDao;
import com.tryzens.portal.user.request.leaverequest.LeaveRequest;

public class LeaveRequestNotification extends RequestNotificationEmail{
	
	LeaveRequest leaveRequest;
	UserDao userDao;
	User sender;
	User recipient;

	@Override
	public void setData(PersistentObject dao) {
		leaveRequest= (LeaveRequest)dao;
		sender= userDao.findById(leaveRequest.getUser().getId());
		recipient= userDao.findById(leaveRequest.getApprover().getId());
		getDataContext().setVariable("name", sender.getFirstName());
		getDataContext().setVariable("id", leaveRequest.getDays());
		getDataContext().setVariable("startDate", leaveRequest.getStartDate());
	}
	@Override
	public String recepient() {
		return recipient.getEmail();
	}

	@Override
	public String subject() {
		return "Leave request from"+sender.getFirstName();
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
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

}
