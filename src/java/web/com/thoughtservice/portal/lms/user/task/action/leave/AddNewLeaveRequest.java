package com.thoughtservice.portal.lms.user.task.action.leave;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import com.thoughtservice.portal.holidays.CalendarHolidays;
import com.thoughtservice.portal.holidays.dao.CalendarHolidaysDao;
import com.thoughtservice.portal.lms.common.date.FormatDate;
import com.thoughtservice.portal.lms.common.details.ListDetails;
import com.thoughtservice.portal.lms.common.notifications.Notification;
import com.thoughtservice.portal.lms.common.notifications.Notification.STATUS;
import com.thoughtservice.portal.lms.mail.request.LeaveRequestNotification;
import com.thoughtservice.portal.lms.mail.request.WeekendWorkingRequestNotification;
import com.thoughtservice.portal.lms.mail.request.WorkFromHomeRequestNotification;
import com.thoughtservice.portal.lms.user.task.UserServices;
import com.thoughtservice.portal.login.service.PortalUserDetails;
import com.thoughtservice.portal.user.User;
import com.thoughtservice.portal.user.dao.UserDao;
import com.thoughtservice.portal.user.request.leaverequest.LeaveRequest;
import com.thoughtservice.portal.user.request.leaverequest.dao.LeaveRequestDao;
import com.thoughtservice.portal.user.request.weekendworking.WeekendWorking;
import com.thoughtservice.portal.user.request.weekendworking.dao.WeekendWorkingDao;
import com.thoughtservice.portal.user.request.workfromhome.WorkFromHome;
import com.thoughtservice.portal.workfromhome.dao.WorkFromHomeDao;

@Transactional
public class AddNewLeaveRequest extends DispatchAction {
	private static final Log LOGGER = LogFactory
			.getLog(AddNewLeaveRequest.class);
	private LeaveRequestDao leaveRequestDao;
	private CalendarHolidaysDao calendarHolidaysDao;
	private WeekendWorkingDao weekendWorkingDao;
	private WorkFromHomeDao workFromHomeDao;
	private UserServices userServices;
	private UserDao userDao;
	private ListDetails listDetails;
	private LeaveRequestNotification leaveRequestNotification;
	private WorkFromHomeRequestNotification workFromHomeRequestNotification;
	private WeekendWorkingRequestNotification weekendWorkingRequestNotification;

	// Add new Leave request by User
	public ActionForward addLeaveRequest(ActionMapping mapping,
			ActionForm form,

			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// check if request is leave type or work from home type
		if ((request.getParameter("RequestType")) != null) {
			if ((request.getParameter("RequestType")).equalsIgnoreCase("leave"))// checking
																				// the
																				// type
																				// of
																				// leave
			{
				try {
					Date date = new Date(); // Gets the current date and time.
					int year = Calendar.getInstance().get(Calendar.YEAR);
					PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
							.getContext().getAuthentication().getPrincipal();
					com.thoughtservice.portal.user.User lmsUser = new com.thoughtservice.portal.user.User();
					lmsUser = userServices.findUserByUserName(sessionUser
							.getUsername());
					String leaveDate = request.getParameter("leaveDate");
					FormatDate formatDate = new FormatDate();
					List<Date> dateList = formatDate.dates(leaveDate);
					List<CalendarHolidays> holidayList = calendarHolidaysDao
							.findAllHolidays();
					// Check whether included date contains bank holiday
					// working, returns true if so
					boolean check = formatDate.calendarHolidayCheck(dateList,
							holidayList, "leaveRequest");
					if (check == true) {
						Notification statusNotification = new Notification(
								"You cant apply leaves on bank holidays!",
								"000", STATUS.WARNING);
						request.setAttribute("notification", statusNotification);
						listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form,
								request, response);
						return mapping.findForward("success");
					}
					// Check whether start date and end date are in same year
					Calendar cal = Calendar.getInstance();
					cal.setTime(dateList.get(0));
					int startYear = cal.get(Calendar.YEAR);
					cal.setTime(dateList.get(1));
					int endYear = cal.get(Calendar.YEAR);
					if (startYear != endYear) {
						Notification statusNotification = new Notification(
								"PLEASE PROVIDE DIFFRENT REQUEST FOR EACH YEAR!",
								"000", STATUS.WARNING);
						request.setAttribute("notification", statusNotification);
						listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form,
								request, response);
						return mapping.findForward("success");

					}
					LeaveRequest leaveRequest = new LeaveRequest();// populating
																	// leave
																	// request
					leaveRequest.setTypeOfRequest("LeaveRequest"); // table
					leaveRequest.setRequestDate((date));
					leaveRequest.setDays(formatDate.calculateDays(dateList,
							"leaveRequest"));
					if (leaveRequest.getDays() == 0) {
						Notification statusNotification = new Notification(
								"You can't apply leaves on weekends!", "000",
								STATUS.WARNING);
						request.setAttribute("notification", statusNotification);
						listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form,
								request, response);
						return mapping.findForward("success");
					}
					leaveRequest.setDescription(request
							.getParameter("holidayDescription"));
					leaveRequest.setMarkedBy(sessionUser.getUsername());
					leaveRequest.setStatus("pending");
					User approver = userDao.findById(Long.parseLong(request
							.getParameter("approver")));
					leaveRequest.setApprover(approver);
					// checks for half day or full day, if null return mapping
					// with error
					if ((request.getParameter("RadioGroup1")) != null) {
						if ((request.getParameter("RadioGroup1"))
								.equalsIgnoreCase("halfDay")) {
							leaveRequest.setHdFd(request
									.getParameter("RadioGroup2"));
						} else {
							leaveRequest.setHdFd("full day");
						}
					} else {
						Notification statusNotification = new Notification(
								"PLEASE SELECT WHETHER HALF DAY OR FULL DAY!",
								"000", STATUS.WARNING);
						request.setAttribute("notification", statusNotification);
						listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form,
								request, response);
						return mapping.findForward("success");
					}
					// checks whether forenoon or afternoon and return error if
					// null
					if ((request.getParameter("RadioGroup1"))
							.equalsIgnoreCase("halfDay")
							&& request.getParameter("RadioGroup2") == null) {
						Notification statusNotification = new Notification(
								"PLEASE SELECT WHETHER FORENOON OR AFTERNOON!",
								"000", STATUS.WARNING);
						request.setAttribute("notification", statusNotification);
						listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form,
								request, response);
						return mapping.findForward("success");
					}
					leaveRequest.setStartDate(dateList.get(0));
					leaveRequest.setEndDate(dateList.get(1));
					leaveRequest.setYear(startYear);
					leaveRequest.setUser(lmsUser);
					Date escalationDate = formatDate.escalateDate(leaveRequest);
					if (escalationDate != null)
						leaveRequest.setDateOfEscalation(escalationDate);
					leaveRequest.setLastUpdate(new Date());
					leaveRequest.setLmsInternal("raised_by"
							+ sessionUser.getUsername());
					leaveRequestDao.addLeaveRequest(leaveRequest);
					try {
						leaveRequestNotification.setData(leaveRequest);
						leaveRequestNotification.sendEmail();
					} catch (javax.mail.MessagingException e) {
						LOGGER.error(e);
					} catch (MailSendException e) {
						LOGGER.error(e);
					}
					Notification statusNotification = new Notification(
							"YOUR REQUEST IS WAITING FOR APPROVAL", "000",
							STATUS.SUCCESS);
					// if not eligible for escalation, returns notification
					// like below
					if (escalationDate == null)
						statusNotification = new Notification(
								"YOUR REQUEST IS WAITING FOR APPROVAL BUT NOT ELIGIBLE FOR ESCALATION",
								"000", STATUS.SUCCESS);
					request.setAttribute("notification", statusNotification);
					listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form,
							request, response);
					return mapping.findForward("success");
					// Exception mainly because of expired session
				} catch (Exception e) {
					LOGGER.error(e);
					System.out.println(e);
					Notification statusNotification = new Notification(
							"Error occured, please Login once again", "000",
							STATUS.ERROR);
					request.setAttribute("notification", statusNotification);
					listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form,
							request, response);
					return mapping.findForward("success");
				}
			} else if ((request.getParameter("RequestType"))
					.equalsIgnoreCase("workfromHome")) {

				Date date = new Date(); // Gets the current date and time.
				int year = Calendar.getInstance().get(Calendar.YEAR);
				PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
						.getContext().getAuthentication().getPrincipal();
				com.thoughtservice.portal.user.User lmsUser = new com.thoughtservice.portal.user.User();
				lmsUser = userServices.findUserByUserName(sessionUser
						.getUsername());
				String leaveDate = request.getParameter("leaveDate");
				FormatDate formatDate = new FormatDate();
				List<Date> dateList = formatDate.dates(leaveDate);
				// checks if start date and end date within same year
				Calendar cal = Calendar.getInstance();
				cal.setTime(dateList.get(0));
				int startYear = cal.get(Calendar.YEAR);
				cal.setTime(dateList.get(1));
				cal.add(Calendar.DATE, -1);
				int endYear = cal.get(Calendar.YEAR);
				if (startYear != endYear) {
					Notification statusNotification = new Notification(
							"PLEASE PROVIDE DIFFRENT REQUEST FOR EACH YEAR!",
							"000", STATUS.WARNING);
					request.setAttribute("notification", statusNotification);
					listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form,
							request, response);
					return mapping.findForward("success");

				}
				WorkFromHome workFromHome = new WorkFromHome();// populating
																// leave
																// request
				try {
					// table
					workFromHome.setUser(lmsUser);
					workFromHome.setTypeOfRequest("WorkFromHome");
					workFromHome.setRequestDate((date));
					List<CalendarHolidays> calendarHolidaysList = calendarHolidaysDao
							.findAllHolidays();
					// check if selected days contain bank holiday
					boolean check = formatDate.calendarHolidayCheck(dateList,
							calendarHolidaysList, "workFromHome");
					if (check) {
						Notification statusNotification = new Notification(
								"Selected dates contains of bank holiday, please exclude bank holiday date",
								"000", STATUS.WARNING);
						request.setAttribute("notification", statusNotification);
						listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form,
								request, response);
						return mapping.findForward("success");
					}
					workFromHome.setDays(formatDate.calculateDays(dateList,
							"workFromHome"));
					workFromHome.setDescription(request
							.getParameter("holidayDescription"));
					workFromHome.setMarkedBy("user");
					workFromHome.setStatus("pending");
					User approver = userDao.findById(Long.parseLong(request
							.getParameter("approver")));
					workFromHome.setApprover(approver);
					// check if full day or half day
					if ((request.getParameter("RadioGroup1")) != null) {
						if ((request.getParameter("RadioGroup1"))
								.equalsIgnoreCase("halfDay")) {
							workFromHome.setHdFd(request
									.getParameter("RadioGroup2"));
						} else {
							workFromHome.setHdFd("full day");
						}
					} else {
						Notification statusNotification = new Notification(
								"PLEASE SELECT WHETHER HALF DAY OR FULL DAY!",
								"000", STATUS.WARNING);
						request.setAttribute("notification", statusNotification);
						listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form,
								request, response);
						return mapping.findForward("success");
					}
					if ((request.getParameter("RadioGroup1"))
							.equalsIgnoreCase("halfDay")
							&& request.getParameter("RadioGroup2") == null) {
						Notification statusNotification = new Notification(
								"PLEASE SELECT WHETHER FORENOON OR AFTERNOON!",
								"000", STATUS.WARNING);
						request.setAttribute("notification", statusNotification);
						listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form,
								request, response);
						return mapping.findForward("success");
					}
					if (workFromHome.getDays() == 0) {
						Notification statusNotification = new Notification(
								"Sorry! Raise request for week days", "000",
								STATUS.WARNING);
						request.setAttribute("notification", statusNotification);
						listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form,
								request, response);
						return mapping.findForward("success");
					}
					workFromHome.setStartDate(dateList.get(0));
					workFromHome.setEndDate(dateList.get(1));
					workFromHome.setYear(year);
					workFromHome.setLastUpdate(new Date());
					try {
						workFromHomeRequestNotification.setData(workFromHome);
						workFromHomeRequestNotification.sendEmail();
					} catch (MailSendException e) {
						LOGGER.error(e);
					} catch (MessagingException e) {
						LOGGER.error(e);
					}
					workFromHome.setLmsInternal("raised_by"
							+ sessionUser.getUsername());
					workFromHome.setLastUpdate(new Date());
					workFromHomeDao.addRequest(workFromHome);
				} catch (Exception e) {
					Notification statusNotification = new Notification(
							"ERROR OCCURED, PLEASE TRY AGAIN!", "000",
							STATUS.ERROR);
					request.setAttribute("notification", statusNotification);
					listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form,
							request, response);
					return mapping.findForward("success");
				}
				if ((request.getParameter("RadioGroup1"))
						.equalsIgnoreCase("halfDay")
						&& request.getParameter("RadioGroup2") == null) {
					Notification statusNotification = new Notification(
							"PLEASE SELECT WHETHER FORENOON OR AFTERNOON!",
							"000", STATUS.WARNING);
					request.setAttribute("notification", statusNotification);
					listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form,
							request, response);
					return mapping.findForward("success");
				}
				if(workFromHome.getDays() == 0)
				{
					Notification statusNotification = new Notification(
							"Sorry! Raise request for week days",
							"000", STATUS.WARNING);
					request.setAttribute("notification", statusNotification);
					listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form,
							request, response);
					return mapping.findForward("success");
				}
			} else if ((request.getParameter("RequestType"))
					.equalsIgnoreCase("weekendwork")) {
				// /////////////////////////////////////////////////////////////////

				Date date = new Date(); // Gets the current date and time.
				int year = Calendar.getInstance().get(Calendar.YEAR);
				PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
						.getContext().getAuthentication().getPrincipal();
				com.thoughtservice.portal.user.User lmsUser = new com.thoughtservice.portal.user.User();
				lmsUser = userServices.findUserByUserName(sessionUser
						.getUsername());
				String leaveDate = request.getParameter("leaveDate");
				FormatDate formatDate = new FormatDate();
				List<Date> dateList = formatDate.dates(leaveDate);
				// checks if start date and end date within same year
				Calendar cal = Calendar.getInstance();
				cal.setTime(dateList.get(0));
				int startYear = cal.get(Calendar.YEAR);
				cal.setTime(dateList.get(1));
				cal.add(Calendar.DATE, -1);
				int endYear = cal.get(Calendar.YEAR);

				if (startYear != endYear) {
					Notification statusNotification = new Notification(
							"PLEASE PROVIDE DIFFRENT REQUEST FOR EACH YEAR!",
							"000", STATUS.WARNING);
					request.setAttribute("notification", statusNotification);
					listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form,
							request, response);
					return mapping.findForward("success");

				}
				WeekendWorking weekendWorking = new WeekendWorking();// populating
				// leave
				// request
				try {

					weekendWorking.setTypeOfRequest("WeekendWorking");
					// table
					weekendWorking.setUser(lmsUser);
					weekendWorking.setRequestDate((date));
					weekendWorking.setType(request
							.getParameter("weekendWorkingType"));
					List<CalendarHolidays> calendarHolidaysList = calendarHolidaysDao
							.findAllHolidays();

					// check if selected days contain bank holiday
					boolean check = formatDate.calendarHolidayCheck(dateList,
							calendarHolidaysList, "weekendWorking");
					if (check) {
						Notification statusNotification = new Notification(
								"Selected dates contains of bank holiday, please exclude bank holiday date",
								"000", STATUS.WARNING);
						request.setAttribute("notification", statusNotification);
						listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form,
								request, response);
						return mapping.findForward("success");
					}
					weekendWorking.setDays(formatDate.calculateDays(dateList,
							"weekendWorking"));
					weekendWorking.setDescription(request
							.getParameter("holidayDescription"));
					weekendWorking.setMarkedBy(lmsUser.getFirstName());
					weekendWorking.setStatus("pending");
					User approver = userDao.findById(Long.parseLong(request
							.getParameter("approver")));
					weekendWorking.setApprover(approver);
					// check if full day or half day
					if ((request.getParameter("RadioGroup1")) != null) {
						if ((request.getParameter("RadioGroup1"))
								.equalsIgnoreCase("halfDay")) {
							weekendWorking.setHdFd(request
									.getParameter("RadioGroup2"));
						} else {
							weekendWorking.setHdFd("full day");
						}
					} else {
						Notification statusNotification = new Notification(
								"PLEASE SELECT WHETHER HALF DAY OR FULL DAY!",
								"000", STATUS.WARNING);
						request.setAttribute("notification", statusNotification);
						listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form,
								request, response);
						return mapping.findForward("success");
					}
					if ((request.getParameter("RadioGroup1"))
							.equalsIgnoreCase("halfDay")
							&& request.getParameter("RadioGroup2") == null) {
						Notification statusNotification = new Notification(
								"PLEASE SELECT WHETHER FORENOON OR AFTERNOON!",
								"000", STATUS.WARNING);
						request.setAttribute("notification", statusNotification);
						listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form,
								request, response);
						return mapping.findForward("success");
					}
					if (weekendWorking.getDays() != 0) {
						Notification statusNotification = new Notification(
								"Sorry! Raise request for weekend  days", "000",
								STATUS.WARNING);
						request.setAttribute("notification", statusNotification);
						listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form,
								request, response);
						return mapping.findForward("success");
					}

					weekendWorking.setStartDate(dateList.get(0));
					weekendWorking.setEndDate(dateList.get(1));
					weekendWorking.setYear(year);
					weekendWorking.setLastUpdate(new Date());
					try {

						weekendWorkingRequestNotification
								.setData(weekendWorking);

						weekendWorkingRequestNotification.sendEmail();

					} catch (MailSendException e) {
						LOGGER.error(e);
					} catch (MessagingException e) {
						LOGGER.error(e);
						LOGGER.error(e);
					}
					weekendWorking.setLmsInternal("raised_by"
							+ sessionUser.getUsername());
					weekendWorking.setLastUpdate(new Date());
					weekendWorkingDao.addWeekendRequest(weekendWorking);
				} catch (Exception e) {
					System.out.println(e);
					Notification statusNotification = new Notification(
							"ERROR OCCURED, PLEASE TRY AGAIN!", "000",
							STATUS.ERROR);
					request.setAttribute("notification", statusNotification);
					listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form,
							request, response);
					return mapping.findForward("success");
				}
				// ///////////////////////////////////////////////////////////////
				Notification statusNotification = new Notification(
						"YOUR REQUEST IS WAITING FOR APPROVAL!", "000",
						STATUS.SUCCESS);
				request.setAttribute("notification", statusNotification);
				listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form,
						request, response);
				return mapping.findForward("success");
			}
			Notification statusNotification = new Notification(
					"YOUR REQUEST IS WAITING FOR APPROVAL", "000",
					STATUS.SUCCESS);
			request.setAttribute("notification", statusNotification);
		} else {

			Notification statusNotification = new Notification(
					"PLEASE SELECT HOLIDAY TYPE!", "000", STATUS.WARNING);
			request.setAttribute("notification", statusNotification);
			listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form, request,
					response);
			return mapping.findForward("success");
		}
		listDetails.listAllRequestsOfLastTwoYearsByUserId(mapping, form, request,
				response);
		return mapping.findForward("success");
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserServices getUserServices() {
		return userServices;
	}

	public void setUserServices(UserServices userServices) {
		this.userServices = userServices;
	}

	public CalendarHolidaysDao getCalendarHolidaysDao() {
		return calendarHolidaysDao;
	}

	public void setCalendarHolidaysDao(CalendarHolidaysDao calendarHolidaysDao) {
		this.calendarHolidaysDao = calendarHolidaysDao;
	}

	public LeaveRequestDao getLeaveRequestDao() {
		return leaveRequestDao;
	}

	public void setLeaveRequestDao(LeaveRequestDao leaveRequestDao) {
		this.leaveRequestDao = leaveRequestDao;
	}

	public WorkFromHomeDao getWorkFromHomeDao() {
		return workFromHomeDao;
	}

	public void setWorkFromHomeDao(WorkFromHomeDao workFromHomeDao) {
		this.workFromHomeDao = workFromHomeDao;
	}

	public void setListDetails(ListDetails listDetails) {
		this.listDetails = listDetails;
	}

	public void setLeaveRequestNotification(
			LeaveRequestNotification leaveRequestNotification) {
		this.leaveRequestNotification = leaveRequestNotification;
	}

	public void setWorkFromHomeRequestNotification(
			WorkFromHomeRequestNotification workFromHomeRequestNotification) {
		this.workFromHomeRequestNotification = workFromHomeRequestNotification;
	}

	public WeekendWorkingDao getWeekendWorkingDao() {
		return weekendWorkingDao;
	}

	public void setWeekendWorkingDao(WeekendWorkingDao weekendWorkingDao) {
		this.weekendWorkingDao = weekendWorkingDao;
	}

	public void setWeekendWorkingRequestNotification(
			WeekendWorkingRequestNotification weekendWorkingRequestNotification) {
		this.weekendWorkingRequestNotification = weekendWorkingRequestNotification;
	}

}
