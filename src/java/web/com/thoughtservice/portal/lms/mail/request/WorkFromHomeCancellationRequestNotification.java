package com.thoughtservice.portal.lms.mail.request;

import java.util.ArrayList;
import java.util.List;

import com.thoughtservice.portal.PersistentObject;
import com.thoughtservice.portal.lms.mail.RequestNotificationEmail;
import com.thoughtservice.portal.user.User;
import com.thoughtservice.portal.user.dao.UserDao;
import com.thoughtservice.portal.user.request.workfromhome.WorkFromHome;

public class WorkFromHomeCancellationRequestNotification extends RequestNotificationEmail {
	WorkFromHome workFromHome;
	UserDao userDao;
	User sender;
	User recipient;

	@Override
	public void setData(PersistentObject dao) {
		workFromHome= (WorkFromHome)dao;
		sender= userDao.findById(workFromHome.getUser().getId());
		recipient= userDao.findById(workFromHome.getApprover().getId());
		getDataContext().setVariable("from", sender.getFirstName());
		getDataContext().setVariable("days", workFromHome.getDays());
		getDataContext().setVariable("startDate", workFromHome.getStartDate());
	}
	@Override
	public String recepient() {
		return recipient.getEmail();
	}


	@Override
	public String subject() {
		return "Work from home request from "+sender.getFirstName();
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
