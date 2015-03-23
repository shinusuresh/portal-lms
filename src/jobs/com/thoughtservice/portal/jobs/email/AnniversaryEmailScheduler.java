package com.thoughtservice.portal.jobs.email;

import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.springframework.scheduling.annotation.Scheduled;

import com.thoughtservice.portal.lms.mail.AnniversaryEmail;
import com.thoughtservice.portal.user.User;
import com.thoughtservice.portal.user.dao.UserDao;

public class AnniversaryEmailScheduler {

	private UserDao userDao;
	private AnniversaryEmail anniversaryEmail;

	private static final Log LOGGER = LogFactory
			.getLog(AnniversaryEmailScheduler.class);

	@Scheduled(cron = "${anniversary.cron.schedule}")
	public void trigger() {
		// Get current month and year
		LocalDate date = new LocalDate();

		List<User> users = userDao.findUsersByJoiningDayAndMonth(
				date.getDayOfMonth(), date.getMonthOfYear());
		for (User user : users) {

			Date joinedDate = user.getDateOfJoining();
			// Figure out the experience at thoughtservice
			Years years = Years.yearsBetween(new LocalDate(joinedDate),
					new LocalDate(new Date()));
			if (years.getYears() > 0) {
				LOGGER.debug(user.getEmail() + " joined on "
						+ user.getDateOfJoining()
						+ ". Triggering Anniversary email.");

				anniversaryEmail.setData(user);
				try {
					anniversaryEmail.sendEmail();
				} catch (MessagingException e) {
					LOGGER.error("Error occured while sending email ", e);
				}
			}
		}
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setAnniversaryEmail(AnniversaryEmail anniversaryEmail) {
		this.anniversaryEmail = anniversaryEmail;
	}

}
