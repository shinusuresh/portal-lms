package com.thoughtservice.portal.lms.mail;

import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.thoughtservice.portal.PersistentObject;

public abstract class EmailBase {

	private static final Log LOGGER = LogFactory.getLog(EmailBase.class);

	private JavaMailSender mailSender;
	private TemplateEngine templateEngine;
	private ReloadableResourceBundleMessageSource messageSource;
	// Prepare the evaluation context
	private final Context dataContext = new Context();

	public void sendEmail() throws MessagingException {
		try {

			// Set default properties to context
			setDefaultProperties();

			// Prepare message using a Spring helper
			final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
			final MimeMessageHelper message = new MimeMessageHelper(
					mimeMessage, "UTF-8");
			message.setSubject(subject());
			message.setFrom("thoughtservice@thoughtservice.com");

			message.setTo(recepient());

			// Create the HTML body using Thymeleaf
			final String htmlContent = this.templateEngine.process(template(),
					dataContext);
			message.setText(htmlContent, true);
			if (recepients() != null) {
				List<String> emails = recepients();
				for (String string : emails) {
					message.addCc(string);
				}
			}

			// Send email
			this.mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			LOGGER.error("Error in sending email ", e);
			throw e;
		}
	}

	private void setDefaultProperties() {
		dataContext.setVariable("facebook_img", messageSource
				.getMessage("serverHost",
						new Object[] { "images/facebook.png" }, Locale.US));
		dataContext
				.setVariable("twitter_img", messageSource.getMessage(
						"serverHost", new Object[] { "images/twitter.png" },
						Locale.US));
		dataContext.setVariable("linkedin_img", messageSource
				.getMessage("serverHost",
						new Object[] { "images/linkedin.png" }, Locale.US));
	}

	// to add cc with multiple address
	public abstract List<String> recepients();

	public abstract void setData(PersistentObject dao);

	public abstract String recepient();

	public abstract String subject();

	public abstract String template();

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setTemplateEngine(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	protected Context getDataContext() {
		return dataContext;
	}

	public void setMessageSource(
			ReloadableResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
	}

}
