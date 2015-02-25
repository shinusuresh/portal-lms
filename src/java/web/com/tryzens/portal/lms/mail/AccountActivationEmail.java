package com.tryzens.portal.lms.mail;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tryzens.portal.PersistentObject;
import com.tryzens.portal.user.User;

public class AccountActivationEmail extends EmailBase {

	private static final Log LOGGER = LogFactory
			.getLog(AccountActivationEmail.class.getName());

	private User user;

	@Override
	public void setData(PersistentObject dao) {
		LOGGER.debug("Entering setData() for "+dao);
		this.user = (User) dao;
		getDataContext().setVariable("name", user.getFirstName());
		getDataContext().setVariable("id", user.getId());
		getDataContext().setVariable("email", user.getEmail());
		getDataContext().setVariable("password", user.getPlainPassword());
				
	}

	@Override
	public String recepient() {
		LOGGER.info("Set email as " + user.getEmail());
		return user.getEmail();
	}

	@Override
	public String template() {
		return "account-created.html";
	}

	@Override
	public String subject() {
		return "Tryzens portal account created";
	}

	@Override
	public List<String> recepients() {
		return null;
	}

}
