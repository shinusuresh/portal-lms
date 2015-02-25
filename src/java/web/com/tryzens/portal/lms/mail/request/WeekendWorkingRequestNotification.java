package com.tryzens.portal.lms.mail.request;

import java.util.ArrayList;
import java.util.List;

import com.tryzens.portal.PersistentObject;
import com.tryzens.portal.lms.mail.RequestNotificationEmail;
import com.tryzens.portal.user.User;
import com.tryzens.portal.user.dao.UserDao;
import com.tryzens.portal.user.request.weekendworking.WeekendWorking;

public class WeekendWorkingRequestNotification extends RequestNotificationEmail{
	WeekendWorking weekendWorking;
	UserDao userDao;
	User sender;
	User recipient;


	public void setData(PersistentObject dao) {
		weekendWorking= (WeekendWorking)dao;
		sender= userDao.findById(weekendWorking.getUser().getId());
		recipient= userDao.findById(weekendWorking.getApprover().getId());
		getDataContext().setVariable("from", sender.getFirstName());
		getDataContext().setVariable("days", weekendWorking.getDays());
		getDataContext().setVariable("startDate", weekendWorking.getStartDate());
	}
	@Override
	public String recepient() {
		return recipient.getEmail();
	}


	@Override
	public String subject() {
		return "Weekend working  request from "+sender.getFirstName();
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
