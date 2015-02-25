package com.tryzens.portal.lms.mail;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalDate;
import org.joda.time.Years;

import com.tryzens.portal.PersistentObject;
import com.tryzens.portal.user.User;


public abstract class RequestNotificationEmail extends EmailBase{
	private static final Log LOGGER = LogFactory
			.getLog(AnniversaryEmail.class.getName());

	private User user;
	private int years;

	@Override
	public String template() {
		return "new-request.html";
		
	}
	
	public void setData(PersistentObject dao) {
		LOGGER.debug("Entering setData() for "+dao);
		this.user = (User) dao;
		getDataContext().setVariable("name", user.getFirstName());
		getDataContext().setVariable("id",user.getId());
		getDataContext().setVariable("email", user.getEmail());		
		
		Date joinedDate = user.getDateOfJoining();
		//Figure out the experience at Tryzens		
		this.years = Years.yearsBetween(new LocalDate(joinedDate), new LocalDate(new Date())).getYears();
		LOGGER.debug("Experience is "+years);
		getDataContext().setVariable("years", years);			
	}

	public List<String> recepients()
	{
		return null;
	}
	
}
	