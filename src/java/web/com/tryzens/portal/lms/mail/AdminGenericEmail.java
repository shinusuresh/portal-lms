package com.tryzens.portal.lms.mail;

import java.util.List;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tryzens.portal.PersistentObject;
import com.tryzens.portal.user.User;

public class AdminGenericEmail extends EmailBase {

	private static final Log LOGGER = LogFactory.getLog(AdminGenericEmail.class
			.getName());

	private User user;
	private List<String> adminEmails;
	private String content;

	@Override
	public void setData(PersistentObject dao) {
		LOGGER.debug("Entering setData() for generic admin email.");
		getDataContext().setVariable("content", content);

	}

	@Override
	public String recepient() {
		LOGGER.info("Set email as " + user.getEmail());
		return user.getEmail();
	}

	@Override
	public String template() {
		return "generic-admin.html";
	}

	@Override
	public String subject() {
		return "Tryzens portal";
	}

	@Override
	public List<String> recepients() {
		return adminEmails;
	}

	public void sendAdminEmail(List<String> adminEmails, String content) {
		this.adminEmails = adminEmails;
		this.content = content;
		setData(null);
		try {
			sendEmail();
		} catch (MessagingException e) {
			LOGGER.error(
					"An error occured while sending email to admins. Error is ",
					e);
		}
	}

}
