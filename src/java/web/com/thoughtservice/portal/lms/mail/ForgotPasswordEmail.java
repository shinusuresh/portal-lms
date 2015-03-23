package com.thoughtservice.portal.lms.mail;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.thoughtservice.portal.PersistentObject;
import com.thoughtservice.portal.user.User;

public class ForgotPasswordEmail extends EmailBase {

	private static final Log LOGGER = LogFactory
			.getLog(ForgotPasswordEmail.class.getName());

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
		return "forgot-password.html";
	}

	@Override
	public String subject() {
		return "thoughtservice portal - Forgot Password";
	}

	@Override
	public List<String> recepients() {
		return null;
	}

}
