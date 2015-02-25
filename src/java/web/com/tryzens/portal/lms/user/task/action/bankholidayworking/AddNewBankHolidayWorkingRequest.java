package com.tryzens.portal.lms.user.task.action.bankholidayworking;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.mail.MailSendException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.tryzens.portal.holidays.CalendarHolidays;
import com.tryzens.portal.holidays.dao.CalendarHolidaysDao;
import com.tryzens.portal.lms.common.date.FormatDate;
import com.tryzens.portal.lms.common.details.ListDetails;
import com.tryzens.portal.lms.common.notifications.Notification;
import com.tryzens.portal.lms.common.notifications.Notification.STATUS;
import com.tryzens.portal.lms.user.task.UserServices;
import com.tryzens.portal.login.service.PortalUserDetails;
import com.tryzens.portal.user.User;
import com.tryzens.portal.user.dao.UserDao;
import com.tryzens.portal.user.request.bankholidayworking.BankHolidayWorking;

public class AddNewBankHolidayWorkingRequest extends DispatchAction {
	private UserServices userServices;
	private ListDetails listDetails;
	private CalendarHolidaysDao calendarHolidaysDao;
	private UserDao userDao;

	public ActionForward addNewRequest(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		try {
			if (request.getParameter("RadioGroup1") != null) {
				FormatDate formatDate = new FormatDate();
				// to get start date and end date
				List<Date> dates = formatDate.dates(request
						.getParameter("leaveDate"));
				// check if start date and end date are in same year
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dates.get(0));
				int startYear = calendar.get(Calendar.YEAR);
				calendar.setTime(dates.get(1));
				calendar.add(Calendar.DATE, -1);
				int endYear = calendar.get(Calendar.YEAR);
				if (startYear != endYear) {
					Notification statusNotification = new Notification(
							"PLEASE PROVIDE DIFFRENT REQUEST FOR EACH YEAR!",
							"000", STATUS.WARNING);
					request.setAttribute("notification", statusNotification);
					listDetails.listAllApprovedRequestOfUser(mapping, form,
							request, response);
					return mapping.findForward("success");

				}
				PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
						.getContext().getAuthentication().getPrincipal();
				com.tryzens.portal.user.User lmsUser = new com.tryzens.portal.user.User();
				lmsUser = userServices.findUserByUserName(sessionUser
						.getUsername());
				BankHolidayWorking bankholidayworking = new BankHolidayWorking();
				bankholidayworking.setUser(lmsUser);
				bankholidayworking.setRequestDate(new Date());
				bankholidayworking.setStatus("pending");
				bankholidayworking.setType(request
						.getParameter("bankHolidayWorkingType"));
				// checks if half day or full day
				if (request.getParameter("RadioGroup1").equalsIgnoreCase(
						"FullDay")) {
					bankholidayworking.setHdFd("full Day");
				} else if (request.getParameter("RadioGroup1")
						.equalsIgnoreCase("HalfDay")) {
					if (request.getParameter("RadioGroup2").equalsIgnoreCase(
							"fornoon"))
						bankholidayworking.setHdFd("HalfDay-fornoon");
					else if (request.getParameter("RadioGroup2")
							.equalsIgnoreCase("Afternoon"))
						bankholidayworking.setHdFd("HalfDay-Afternoon");

				}
				bankholidayworking.setTypeOfRequest("BankHolidayWorking");
				bankholidayworking.setDescription(request
						.getParameter("description"));
				User approver = userDao.findById(Long.parseLong(request
						.getParameter("approver")));
				bankholidayworking.setApprover(approver);
				bankholidayworking.setStartDate(((java.util.List<Date>) dates)
						.get(0));
				Date enddate = ((java.util.List<Date>) dates).get(1);
				// reduce a day from end date to calculate number of days
				Calendar cal = Calendar.getInstance();
				cal.setTime(enddate);
				cal.add(Calendar.DATE, -1);
				enddate = cal.getTime();
				dates.set(1, enddate);
				bankholidayworking.setYear(cal.get(Calendar.YEAR));
				int days = formatDate
						.calculateDays(dates, "bankHolidayWorking");
				bankholidayworking.setDays(days);
				bankholidayworking.setEndDate(cal.getTime());
				bankholidayworking.setLastUpdate(new Date());
				// add a day to end date to include last day to check for
				// calendar holiday
				cal.add(Calendar.DATE, 1);
				dates.set(1, cal.getTime());
				List<CalendarHolidays> calendarHolidaysList = calendarHolidaysDao
						.findAllHolidays();
				// returns true if all dates are marked as calendar holidays
				boolean check = formatDate.calendarHolidayCheck(dates,
						calendarHolidaysList, "bankHolidayWorking");
				if (check == false) {
					Notification statusNotification = new Notification(
							"Selected date is not concidered as bank holiday for Tryzens",
							"999", STATUS.WARNING);
					request.setAttribute("notification", statusNotification);
					listDetails.listAllApprovedRequestOfUser(mapping, form,
							request, response);
					listDetails.listHolidays(mapping, form, request, response);
					return mapping.findForward("success");
				}
				bankholidayworking.setLmsInternal("raised_by"
						+ sessionUser.getUsername());
				bankholidayworking.setLastUpdate(new Date());
				try {
					userServices.bankHolidayWorkingRequest(bankholidayworking);
				} catch (MailSendException e) {
					Notification statusNotification = new Notification(
							"Error sending email, please notify admin manually=",
							"000", STATUS.SUCCESS);
					request.setAttribute("notification", statusNotification);
					listDetails.listAllApprovedRequestOfUser(mapping, form,
							request, response);
					listDetails.listHolidays(mapping, form, request, response);
					return mapping.findForward("success");

				} catch (MessagingException e) {
					Notification statusNotification = new Notification(
							"Error sending email, please notify admin manually=",
							"000", STATUS.SUCCESS);
					request.setAttribute("notification", statusNotification);
					listDetails.listAllApprovedRequestOfUser(mapping, form,
							request, response);
					listDetails.listHolidays(mapping, form, request, response);
					return mapping.findForward("success");
				}
				Notification statusNotification = new Notification(
						"YOUR REQUEST IS WAITING FOR APPROVAL", "000",
						STATUS.SUCCESS);
				request.setAttribute("notification", statusNotification);
			} else {
				Notification statusNotification = new Notification(
						"Pleae select a type!", "999", STATUS.ERROR);
				request.setAttribute("notification", statusNotification);
			}
		} catch (Exception e) {
			Notification statusNotification = new Notification(
					e.toString(), "999",
					STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		} finally {

			listDetails.listAllApprovedRequestOfUser(mapping, form, request,
					response);
			listDetails.listHolidays(mapping, form, request, response);

		}

		return mapping.findForward("success");
	}

	public void setUserServices(UserServices userServices) {
		this.userServices = userServices;
	}

	public void setListDetails(ListDetails listDetails) {
		this.listDetails = listDetails;
	}

	public void setCalendarHolidaysDao(CalendarHolidaysDao calendarHolidaysDao) {
		this.calendarHolidaysDao = calendarHolidaysDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
