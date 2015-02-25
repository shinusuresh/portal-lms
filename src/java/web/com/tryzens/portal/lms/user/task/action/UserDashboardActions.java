package com.tryzens.portal.lms.user.task.action;

import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.security.core.context.SecurityContextHolder;

import com.tryzens.portal.jobs.calculateleaves.CalculateLeaves;
import com.tryzens.portal.lms.common.details.ListDetails;
import com.tryzens.portal.lms.common.notifications.Notification;
import com.tryzens.portal.lms.common.notifications.Notification.STATUS;
import com.tryzens.portal.lms.user.task.UserServices;
import com.tryzens.portal.login.service.PortalUserDetails;
import com.tryzens.portal.user.User;
import com.tryzens.portal.user.dao.UserDao;
import com.tryzens.portal.userholidays.UserHolidays;

public class UserDashboardActions extends DispatchAction {

	private UserDao userDao;
	private UserServices userServices;
	private ListDetails listDetails;
	private CalculateLeaves calculateLeaves;
	private static final Log LOGGER = LogFactory
			.getLog(UserDashboardActions.class);

	/**
	 * Resend activation email to user
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward forgotPassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			User user = userDao.findByEmail(request.getParameter("email"));
			// Send activation email by calling AdminServices
			boolean status = userServices.forgotPassword(user);

			if (status) {
				// Create the notification object to JSP status display
				Notification statusNotification = new Notification(
						"Password send to " + user.getEmail(), "000",
						STATUS.SUCCESS);
				request.setAttribute("notification", statusNotification);
			} else {
				// Create the notification object to JSP status display
				Notification statusNotification = new Notification(
						"An error occured in sending email to "
								+ user.getEmail(), "999", STATUS.ERROR);
				request.setAttribute("notification", statusNotification);
			}

		} catch (Exception e) {
			LOGGER.equals(e);
			Notification statusNotification = new Notification(
					"An error occured in sending email to user. Please try again. Contact Administrator if issue exists",
					"999", STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}

		return mapping.findForward("forgotPasswordSuccess");
	}

	public ActionForward load(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("forgotPasswordLoad");
	}

	public ActionForward updateProfile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();

			User user = userServices.findUserByUserName(sessionUser
					.getUsername());
			user.setFirstName(request.getParameter("firstName"));
			user.setLastName(request.getParameter("lastName"));
			try {
				LOGGER.debug("Date of birth is "
						+ request.getParameter("dateOfBirth"));
				Date date = DateUtils.parseDate(
						request.getParameter("dateOfBirth"),
						new String[] { "yyyy-MM-dd" });
				user.setDateOfBirth(date);
			} catch (ParseException ex) {
				LOGGER.error("Exception " + ex);
			}
			user.setAddress(request.getParameter("address"));
			user.setMobile(request.getParameter("mobile"));
			userServices.updateUser(user);
			Notification statusNotification = new Notification(
					"Profile updated successfully", "000", STATUS.SUCCESS);
			request.setAttribute("notification", statusNotification);

		} catch (Exception e) {
			LOGGER.equals(e);
			Notification statusNotification = new Notification(
					"Error updating profile", "999", STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}
		listDetails.listUserDetails(mapping, form, request, response);
		return mapping.findForward("success");
	}

	public ActionForward listHolidaysAndLeaves(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			UserHolidays userHolidays = calculateLeaves.remainingLeaves();

			if(userHolidays != null) {
				double leavesTaken = userHolidays.getLeavesTaken();
				double leavesRemaining = userHolidays.getLeavesRemaining()
						+ userHolidays.getCarryForwardedLeaves()
						+ userHolidays.getBankHolidayWorkingCompoff();
				double totalLeaves = userHolidays.getLeavesTaken()
						+ userHolidays.getLeavesRemaining()
						+ userHolidays.getBankHolidayWorkingCompoff();
				if (leavesRemaining < 0)
					leavesRemaining = 0;
				double totalWorkFromHome = userHolidays.getWorkFromHome();
				double bankHolidayWorkingPaid = userHolidays
						.getBankHolidayWorkingPaid();
				request.setAttribute("totalLeaves", totalLeaves);
				request.setAttribute("leavesTaken", leavesTaken);
				request.setAttribute("leavesRemaining", leavesRemaining);
				request.setAttribute("totalWorkFromHome", totalWorkFromHome);
				request.setAttribute("bankHolidayWorkingPaid",
						bankHolidayWorkingPaid);
			} else {
				PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
						.getContext().getAuthentication().getPrincipal();
				User user = userServices.findUserByUserName(sessionUser
						.getUsername());
				
				request.setAttribute("totalLeaves", 0);
				request.setAttribute("leavesTaken", 0);
				request.setAttribute("leavesRemaining", user.getAnnualLeaves());
				request.setAttribute("totalWorkFromHome", 0);
				request.setAttribute("bankHolidayWorkingPaid",
						0);
			}
			listDetails.loadUserSkills(mapping, form, request, response);
			listDetails.listHolidays(mapping, form, request, response);
			// To set leave taken and remaining leave to user
			return mapping.findForward("success");

		} catch (Exception e) {
			Notification statusNotification = new Notification(
					"error occured! please login once again", "999",
					STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
			return mapping.findForward("success");

		}
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setUserServices(UserServices userServices) {
		this.userServices = userServices;
	}

	public void setListDetails(ListDetails listDetails) {
		this.listDetails = listDetails;
	}

	public void setCalculateLeaves(CalculateLeaves calculateLeaves) {
		this.calculateLeaves = calculateLeaves;
	}

}
