package com.tryzens.portal.lms.user.task.action;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.mail.MailSendException;

import com.tryzens.portal.lms.common.details.ListDetails;
import com.tryzens.portal.lms.common.notifications.Notification;
import com.tryzens.portal.lms.common.notifications.Notification.STATUS;
import com.tryzens.portal.lms.user.task.UserServices;
import com.tryzens.portal.user.request.bankholidayworking.BankHolidayWorking;
import com.tryzens.portal.user.request.leaverequest.LeaveRequest;
import com.tryzens.portal.user.request.weekendworking.WeekendWorking;
import com.tryzens.portal.user.request.workfromhome.WorkFromHome;

public class LeaveCancellationRequest extends DispatchAction {
	private static final Log LOGGER = LogFactory
			.getLog(LeaveCancellationRequest.class);
	private ListDetails listDetails;
	private UserServices userServices;

	
	public ActionForward cancellationRequest(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if(request.getParameter("type_input").equalsIgnoreCase("LeaveRequest"))
		{
			leaveCancellationRequest(mapping, form, request, response);
		}else if(request.getParameter("type_input").equalsIgnoreCase("WorkFromHomeRequest"))
		{
			workFromHomeCancellationRequest(mapping, form, request, response);
		}else if(request.getParameter("type_input").equalsIgnoreCase("WeekendWorkingRequest"))
		{
			weekendWorkingCancellationRequest(mapping, form, request, response);
		}
		else if(request.getParameter("type_input").equalsIgnoreCase("BankHolidayWorkingRequest"))
		{
			bankHolidayWorkingCancellationRequest(mapping, form, request, response);
		}
		return mapping.findForward("newRequest");
	}
	public ActionForward leaveCancellationRequest(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			LeaveRequest leaveRequest;
			leaveRequest = userServices.getLeaveRequest(Long.parseLong(request
					.getParameter("requestId")));
			if(leaveRequest.getStatus().equalsIgnoreCase("pending"))
			{
				leaveRequest.setStatus("cancelled");
				userServices.updateLeaveRequest(leaveRequest);
				Notification statusNotification = new Notification(
						"Your request is cancelled", "000",
						STATUS.SUCCESS);
				request.setAttribute("notification", statusNotification);
			}
			else
			{
			leaveRequest.setStatus("waitingCancellation");
			try {
				userServices.updateLeaveRequest(leaveRequest);
			} catch (MessagingException e) {
				LOGGER.error(e);
			} catch (MailSendException e) {
				LOGGER.error(e);
			}
			Notification statusNotification = new Notification(
					"Your request is waiting for confirmation", "000",
					STATUS.SUCCESS);
			request.setAttribute("notification", statusNotification);
		}
			}catch (Exception e) {
			LOGGER.error(e);
			Notification statusNotification = new Notification("Try again",
					"999", STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}
		listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form,
				request, response);
		if ((request.getParameter("from") != null)
				&& request.getParameter("from").equalsIgnoreCase("calendar")) {
			listDetails.listApprovers(mapping, form, request, response);
			listDetails.listHolidays(mapping, form, request, response);
			return mapping.findForward("newRequest");
		}
		return mapping.findForward("success");
	}

	public ActionForward workFromHomeCancellationRequest(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			WorkFromHome workFromHome;
			workFromHome = userServices.getWorkFromHomeRequest(Long
					.parseLong(request.getParameter("requestId")));
			if(workFromHome.getStatus().equalsIgnoreCase("pending"))
			{
				workFromHome.setStatus("cancelled");
				userServices.updateWorkFromHomeRequest(workFromHome);
				Notification statusNotification = new Notification(
						"Your request is cancelled", "000",
						STATUS.SUCCESS);
				request.setAttribute("notification", statusNotification);
			}
			else
			{
			workFromHome.setStatus("waitingCancellation");
			try {
				userServices.updateWorkFromHomeRequest(workFromHome);// thrown
																		// exception
																		// from
																		// user
																		// services
																		// have
																		// to
																		// hamdle
																		// msg
																		// exception
																		// and
																		// jdbc
																		// exception
																		// here
			} catch (MessagingException e) {
				LOGGER.error(e);
			} catch (MailSendException e) {
				LOGGER.error(e);
			}
			Notification statusNotification = new Notification(
					"Your request is waiting for confirmation", "000",
					STATUS.SUCCESS);
			request.setAttribute("notification", statusNotification);
		} 
		}catch (Exception e) {
			LOGGER.error(e);
			Notification statusNotification = new Notification("Try again",
					"999", STATUS.WARNING);
			request.setAttribute("notification", statusNotification);
		}
		listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form,
				request, response);
		if ((request.getParameter("from") != null)
				&& request.getParameter("from").equalsIgnoreCase("calendar")) {
			listDetails.listApprovers(mapping, form, request, response);
			listDetails.listHolidays(mapping, form, request, response);
			return mapping.findForward("newRequest");
		}
		
		return mapping.findForward("success");
	}

	public ActionForward bankHolidayWorkingCancellationRequest(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			BankHolidayWorking bankHolidayWorking = null;
			try {
				bankHolidayWorking = userServices.getBankHolidayRequest(Long
						.parseLong(request.getParameter("requestId")));
			} catch (Exception e) {
				LOGGER.error(e);

			}
			if(bankHolidayWorking.getStatus().equalsIgnoreCase("pending"))
			{
				bankHolidayWorking.setStatus("cancelled");
				userServices.updatebankHolidayWorkingRequest(bankHolidayWorking);
				Notification statusNotification = new Notification(
						"Your request is cancelled", "000",
						STATUS.SUCCESS);
				request.setAttribute("notification", statusNotification);
			}
			else
			{
			bankHolidayWorking.setStatus("waitingCancellation");
			try {
				userServices
						.updatebankHolidayWorkingRequest(bankHolidayWorking);
			} catch (MessagingException e) {
				LOGGER.error(e);
			} catch (MailSendException e) {
				LOGGER.error(e);
			}
			Notification statusNotification = new Notification(
					"Your request is waiting for confirmation", "000",
					STATUS.SUCCESS);
			request.setAttribute("notification", statusNotification);
		} 
		}catch (Exception e) {
			LOGGER.error(e);
			Notification statusNotification = new Notification("Try again",
					"999", STATUS.WARNING);
			request.setAttribute("notification", statusNotification);

		} 
		
		listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form,
				request, response);
		if ((request.getParameter("from") != null)
				&& request.getParameter("from").equalsIgnoreCase("calendar")) {
			listDetails.listApprovers(mapping, form, request, response);
			listDetails.listHolidays(mapping, form, request, response);
			return mapping.findForward("newRequest");
		}
		return mapping.findForward("success");
	}

	public ActionForward weekendWorkingCancellationRequest(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			WeekendWorking weekendWorking;
			weekendWorking = userServices.getWeekendWorkingRequest(Long
					.parseLong(request.getParameter("requestId")));
			if(weekendWorking.getStatus().equalsIgnoreCase("pending"))
			{
				weekendWorking.setStatus("cancelled");
				userServices.updateWeekendWorkingRequest(weekendWorking);
				Notification statusNotification = new Notification(
						"Your request is cancelled", "000",
						STATUS.SUCCESS);
				request.setAttribute("notification", statusNotification);
			}
			else
			{
			weekendWorking.setStatus("waitingCancellation");
			try {
				userServices.updateWeekendWorkingRequest(weekendWorking);// thrown
																			// exception
																			// from
																			// user
																			// services
																			// have
																			// to
																			// hamdle
																			// msg
																			// exception
																			// and
																			// jdbc
																			// exception
																			// here
			} catch (MessagingException e) {
				LOGGER.error(e);
			} catch (MailSendException e) {
				LOGGER.error(e);
			}
			Notification statusNotification = new Notification(
					"Your request is waiting for confirmation", "000",
					STATUS.SUCCESS);
			request.setAttribute("notification", statusNotification);
		} 
		}catch (Exception e) {
			LOGGER.error(e);
			Notification statusNotification = new Notification("Try again",
					"999", STATUS.WARNING);
			request.setAttribute("notification", statusNotification);
		}

		listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form,
				request, response);
		if ((request.getParameter("from") != null)
				&& request.getParameter("from").equalsIgnoreCase("calendar")) {
			listDetails.listApprovers(mapping, form, request, response);
			listDetails.listHolidays(mapping, form, request, response);
			return mapping.findForward("newRequest");
		}

		return mapping.findForward("success");
	}

	public void setListDetails(ListDetails listDetails) {
		this.listDetails = listDetails;
	}

	public void setUserServices(UserServices userServices) {
		this.userServices = userServices;
	}

}
