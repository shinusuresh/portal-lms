package com.tryzens.portal.lms.mail.request;

import java.util.ArrayList;
import java.util.List;

import com.tryzens.portal.PersistentObject;
import com.tryzens.portal.lms.mail.RequestNotificationEmail;
import com.tryzens.portal.user.User;
import com.tryzens.portal.user.dao.UserDao;
import com.tryzens.portal.user.request.bankholidayworking.BankHolidayWorking;

public class BankHolidayWorkingCancellationRequestNotification extends RequestNotificationEmail {

	BankHolidayWorking bankHolidayWorking;
	UserDao userDao;
	User sender;
	User recipient;

	@Override
	public void setData(PersistentObject dao) {
		bankHolidayWorking= (BankHolidayWorking)dao;
		sender= userDao.findById(bankHolidayWorking.getUser().getId());
		recipient= userDao.findById(bankHolidayWorking.getApprover().getId());
		getDataContext().setVariable("from", sender.getFirstName());
		getDataContext().setVariable("days", bankHolidayWorking.getDays());
		getDataContext().setVariable("startDate", bankHolidayWorking.getStartDate());
	}
	@Override
	public String recepient() {
		return recipient.getEmail();
	}

	

	@Override
	public String subject() {
		return "Bank holiday working request from"+ sender.getFirstName();
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
