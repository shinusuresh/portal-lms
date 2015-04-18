package com.thoughtservice.portal.lms.mail;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalDate;
import org.joda.time.Years;

import com.thoughtservice.portal.PersistentObject;
import com.thoughtservice.portal.user.User;

public class AnniversaryEmail extends EmailBase {

	private static final Log LOGGER = LogFactory
			.getLog(AnniversaryEmail.class.getName());

	private User user;
	private int years;

	@Override
	public void setData(PersistentObject dao) {
		LOGGER.debug("Entering setData() for "+dao);
		this.user = (User) dao;
		getDataContext().setVariable("name", user.getFirstName());
		getDataContext().setVariable("id", user.getId());
		getDataContext().setVariable("email", user.getEmail());		
		
		Date joinedDate = user.getDateOfJoining();
		//Figure out the experience at thoughtservice		
		this.years = Years.yearsBetween(new LocalDate(joinedDate), new LocalDate(new Date())).getYears();
		LOGGER.debug("Experience is "+years);
		getDataContext().setVariable("years", years);			
	}

	@Override
	public String recepient() {
		LOGGER.info("Set email as " + user.getEmail());
		return user.getEmail();
	}

	@Override
	public String template() {
		return "anniversary.html";
	}

	@Override
	public String subject() {
		return "Congratulations on completing "+ years +" years at thoughtservice.";
	}

	@Override
	public List<String> recepients() {
		return null;
	}
}
