package com.thoughtservice.portal.lms.common.details;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.joda.time.LocalDate;
import org.springframework.security.core.context.SecurityContextHolder;

import com.thoughtservice.portal.holidays.CalendarHolidays;
import com.thoughtservice.portal.holidays.dao.CalendarHolidaysDao;
import com.thoughtservice.portal.jobs.calculateleaves.CalculateLeaves;
import com.thoughtservice.portal.lms.admin.task.AdminServices;
import com.thoughtservice.portal.lms.common.notifications.Notification;
import com.thoughtservice.portal.lms.common.notifications.Notification.STATUS;
import com.thoughtservice.portal.lms.manager.task.ManagerServices;
import com.thoughtservice.portal.lms.user.task.UserServices;
import com.thoughtservice.portal.login.service.PortalUserDetails;
import com.thoughtservice.portal.role.Role;
import com.thoughtservice.portal.user.User;
import com.thoughtservice.portal.user.attendance.Attendance;
import com.thoughtservice.portal.user.attendance.dao.AttendanceDao;
import com.thoughtservice.portal.user.dao.RoleDao;
import com.thoughtservice.portal.user.dao.UserDao;
import com.thoughtservice.portal.user.experience.Experience;
import com.thoughtservice.portal.user.experience.dao.ExperienceDao;
import com.thoughtservice.portal.user.request.bankholidayworking.BankHolidayWorking;
import com.thoughtservice.portal.user.request.bankholidayworking.UserBankHolidayVO;
import com.thoughtservice.portal.user.request.bankholidayworking.dao.BankHolidayWorkingDao;
import com.thoughtservice.portal.user.request.leaverequest.LeaveRequest;
import com.thoughtservice.portal.user.request.leaverequest.UserLeaveRequestVO;
import com.thoughtservice.portal.user.request.leaverequest.dao.LeaveRequestDao;
import com.thoughtservice.portal.user.request.weekendworking.UserWeekendWorkingVO;
import com.thoughtservice.portal.user.request.weekendworking.WeekendWorking;
import com.thoughtservice.portal.user.request.weekendworking.dao.WeekendWorkingDao;
import com.thoughtservice.portal.user.request.workfromhome.UserWorkFromHomeVO;
import com.thoughtservice.portal.user.request.workfromhome.WorkFromHome;
import com.thoughtservice.portal.user.skills.UserSkills;
import com.thoughtservice.portal.user.skills.dao.SkillsDao;
import com.thoughtservice.portal.userholidays.UserHolidays;
import com.thoughtservice.portal.userholidays.dao.UserHolidaysDao;
import com.thoughtservice.portal.workfromhome.dao.WorkFromHomeDao;

public class ListDetails extends DispatchAction {
	
	private static final Log LOGGER = LogFactory.getLog(ListDetails.class);
	
	private CalendarHolidaysDao calendarHolidaysDao;
	private UserDao userDao;
	private AttendanceDao attendanceDao;
	private LeaveRequestDao leaveRequestDao;
	private WorkFromHomeDao workFromHomeDao;
	private ManagerServices managerServices;
	private UserServices userServices;
	private AdminServices adminServices;
	private BankHolidayWorkingDao bankHolidayWorkingDao;
	private CalculateLeaves calculateLeaves;
	private SkillsDao skillsDao;
	private UserHolidaysDao userHolidaysDao;
	private WeekendWorkingDao weekendWorkingDao;
	private ExperienceDao experienceDao;

	private RoleDao roleDao;

	public ActionForward listHolidays(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<CalendarHolidays> events = calendarHolidaysDao
				.findCurrentYearHolidays(LocalDate.now().getYear());
		// To set leave taken and remaining leave to user
		request.setAttribute("events", events);
		request.setAttribute("currentYear", LocalDate.now().getYear());
		return mapping.findForward("success");

	}

	public ActionForward listApprovers(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		List<User> approvers = new ArrayList<User>();
		Role role = roleDao.findUserRoleByUserId(Long.parseLong(sessionUser
				.getUserId()));
		if (role.getName().equalsIgnoreCase("ROLE_MANAGER")) {
			approvers = userDao.findAllUserByRole("ROLE_ADMIN");
		}

		else if (role.getName().equalsIgnoreCase("ROLE_USER")) {
			approvers = userDao.findAllUserByRoleAndDepartment("ROLE_MANAGER");
		}
		request.setAttribute("approvers", approvers);
		return mapping.findForward("success");

	}

	// To fetch attendence
	public ActionForward listAttendance(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		List<Attendance> attendances = new ArrayList<Attendance>();
		
		PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		
		//If admin is accessing the page, then load attendance for all users else load only the current customer data
		if(sessionUser.isAdmin()) {
			attendances = attendanceDao.getAllAttendance();
		} else {
			attendances = attendanceDao.findByEmployee(Long.parseLong(sessionUser.getUserId()));
		}
		
		LOGGER.debug("inside list attndce" + attendances.size());
		
		request.setAttribute("attendances", attendances);
		return mapping.findForward("success");

	}

	// To fetch leave requests
	public ActionForward listLeaveRequests(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		List<LeaveRequest> leaves = leaveRequestDao.ListAllLeave();
		request.setAttribute("leaves", leaves);
		return mapping.findForward("success");

	}

	// To fetch details from work from home
	public ActionForward listWorkFromHome(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		List<WorkFromHome> workFromHomeRequests = workFromHomeDao
				.findAllWorkFromHomeRequest();
		request.setAttribute("workFromHomeRequests", workFromHomeRequests);
		return mapping.findForward("success");

	}

	public ActionForward listWeekendWorking(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		List<WeekendWorking> weekendWorkingRequests = weekendWorkingDao
				.findAllWeekendWorkingRequest();
		request.setAttribute("weekendWorkingRequests", weekendWorkingRequests);
		return mapping.findForward("success");

	}

	// Function to set approvers,leave requests and holidays to user
	public ActionForward setDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		listApprovers(mapping, form, request, response);
		listWorkFromHome(mapping, form, request, response);
		listLeaveRequests(mapping, form, request, response);
		listHolidays(mapping, form, request, response);
		listWeekendWorking(mapping, form, request, response);

		return mapping.findForward("success");

	}

	// Function to list pending leave request to approver
	public ActionForward listPendingLeaveRequest(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			com.thoughtservice.portal.user.User lmsUser = new com.thoughtservice.portal.user.User();
			lmsUser = userServices
					.findUserByUserName(sessionUser.getUsername());
			List<LeaveRequest> pendingLeaveList = managerServices
					.listPendingLeaveRequest(lmsUser.getId());
			Role sessionUserRole = roleDao.findUserRoleByUserId(Long
					.parseLong(sessionUser.getUserId()));
			if (sessionUserRole.getName().equalsIgnoreCase("ROLE_ADMIN")) {
				List<LeaveRequest> escalatedLeaves = leaveRequestDao
						.listPendingLeaveRequestForEscalation();
				request.setAttribute("escalatedLeaves", escalatedLeaves);
			}
			ArrayList<UserLeaveRequestVO> userLeaveValueList = new ArrayList<UserLeaveRequestVO>();// add
			// here
			if (pendingLeaveList != null)
				for (LeaveRequest item : pendingLeaveList) {
					UserLeaveRequestVO userLeaveVO = new UserLeaveRequestVO();
					User userObj = userDao.findById(item.getUser().getId());
					userLeaveVO.setFirstName(userObj.getFirstName());
					userLeaveVO.setLastName(userObj.getLastName());
					userLeaveVO.setDays(item.getDays());
					userLeaveVO.setEndDate(item.getEndDate());
					userLeaveVO.setStartDate(item.getStartDate());
					userLeaveVO.setRequestDate(item.getRequestDate());
					userLeaveVO.setRequestId(item.getId());
					userLeaveVO.setStatus(item.getStatus());
					userLeaveVO.setHdFd(item.getHdFd());
					userLeaveValueList.add(userLeaveVO);
				}

			request.setAttribute("userLeaveValueList", userLeaveValueList);
		} catch (Exception e) {
			Notification statusNotification = new Notification(
					"Error occured, Please login once again", "999",
					STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}
		return mapping.findForward("success");

	}

	// Function to list pending WorkFromHome Request
	public ActionForward listPendingWorkFromHomeRequest(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			com.thoughtservice.portal.user.User lmsUser = new com.thoughtservice.portal.user.User();
			lmsUser = userServices
					.findUserByUserName(sessionUser.getUsername());
			List<WorkFromHome> pendingWorkFromHomeList = managerServices
					.listPendingWorkFromHomeRequest(lmsUser.getId());

			ArrayList<UserWorkFromHomeVO> userWorkFromHomeValueList = new ArrayList<UserWorkFromHomeVO>();
			if (pendingWorkFromHomeList != null)
				for (WorkFromHome item : pendingWorkFromHomeList) {
					UserWorkFromHomeVO userWorkFromHomeVO = new UserWorkFromHomeVO();
					User userObj = userDao.findById(item.getUser().getId());
					userWorkFromHomeVO.setFirstName(userObj.getFirstName());
					userWorkFromHomeVO.setLastName(userObj.getLastName());
					userWorkFromHomeVO.setDays(item.getDays());
					userWorkFromHomeVO.setEndDate(item.getEndDate());
					userWorkFromHomeVO.setStartDate(item.getStartDate());
					// userLeaveVO.setRequestDate(item.getRequestDate());
					userWorkFromHomeVO.setRequestId(item.getId());
					userWorkFromHomeVO.setStatus(item.getStatus());
					// userLeaveVO.setHdFd(item.getHdFd());
					userWorkFromHomeValueList.add(userWorkFromHomeVO);
				}
			request.setAttribute("userWorkFromHomeValueList",
					userWorkFromHomeValueList);
		} catch (Exception e) {
			Notification statusNotification = new Notification(
					"Error occured, Please login once again", "999",
					STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}
		return mapping.findForward("success");

	}

	// Function to list pending bank holiday requests to approver
	public ActionForward listPendingBankHoidayWorkingRequest(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			com.thoughtservice.portal.user.User lmsUser;
			lmsUser = userServices
					.findUserByUserName(sessionUser.getUsername());
			List<BankHolidayWorking> pendingBankHolidayList = managerServices

			.listPendingBankHoidayWorkingRequest(lmsUser.getId());

			ArrayList<UserBankHolidayVO> userBankHolidayValueList = new ArrayList<UserBankHolidayVO>();
			if (pendingBankHolidayList != null)
				for (BankHolidayWorking item : pendingBankHolidayList) {
					UserBankHolidayVO userBankHolidayVO = new UserBankHolidayVO();
					User userObj = userDao.findById(item.getUser().getId());
					userBankHolidayVO.setFirstName(userObj.getFirstName());
					userBankHolidayVO.setLastName(userObj.getLastName());
					userBankHolidayVO.setDays(item.getDays());
					userBankHolidayVO.setEndDate(item.getEndDate());
					userBankHolidayVO.setStartDate(item.getStartDate());
					userBankHolidayVO.setRequestDate(item.getRequestDate());
					userBankHolidayVO.setRequestId(item.getId());
					userBankHolidayVO.setStatus(item.getStatus());
					userBankHolidayVO.setType(item.getType());
					userBankHolidayValueList.add(userBankHolidayVO);

				}
			request.setAttribute("userBankHolidayValueList",
					userBankHolidayValueList);
		} catch (Exception e) {
			Notification statusNotification = new Notification(
					"Error occured, Please login once again", "999",
					STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}
		// Set properties and add pending leave request and work from home
		// request
		return mapping.findForward("success");
	}

	// Function to list pending WEEKEND WORKING Request
	public ActionForward listPendingWeekendWorkingRequest(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			com.thoughtservice.portal.user.User lmsUser = new com.thoughtservice.portal.user.User();
			lmsUser = userServices
					.findUserByUserName(sessionUser.getUsername());
			List<WeekendWorking> pendingWeekendWorkingList = managerServices
					.listPendingWeekendWorkingRequest(lmsUser.getId());
			ArrayList<UserWeekendWorkingVO> userWeekendWorkingValueList = new ArrayList<UserWeekendWorkingVO>();
			if (pendingWeekendWorkingList != null)
				for (WeekendWorking item : pendingWeekendWorkingList) {
					UserWeekendWorkingVO userWeekendWorkingVO = new UserWeekendWorkingVO();
					User userObj = userDao.findById(item.getUser().getId());
					userWeekendWorkingVO.setFirstName(userObj.getFirstName());
					userWeekendWorkingVO.setLastName(userObj.getLastName());
					userWeekendWorkingVO.setDays(item.getDays());
					userWeekendWorkingVO.setEndDate(item.getEndDate());
					userWeekendWorkingVO.setStartDate(item.getStartDate());
					// userLeaveVO.setRequestDate(item.getRequestDate());
					userWeekendWorkingVO.setRequestId(item.getId());
					userWeekendWorkingVO.setStatus(item.getStatus());
					// userLeaveVO.setHdFd(item.getHdFd());
					userWeekendWorkingValueList.add(userWeekendWorkingVO);
				}
			request.setAttribute("userWeekendWorkingValueList",
					userWeekendWorkingValueList);
		} catch (Exception e) {
			Notification statusNotification = new Notification(
					"Error occured, Please login once again", "999",
					STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}
		return mapping.findForward("success");

	}

	// Function to list pending requests to approver
	public ActionForward listPendingRequest(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		listPendingLeaveRequest(mapping, form, request, response);
		listPendingWorkFromHomeRequest(mapping, form, request, response);
		listPendingBankHoidayWorkingRequest(mapping, form, request, response);
		listPendingWeekendWorkingRequest(mapping, form, request, response);
		return mapping.findForward("success");

	}

	// Function to list all approved requests of user
	public ActionForward listAllApprovedRequestOfUser(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			List<LeaveRequest> leaves = leaveRequestDao
					.findAllApprovedLeaveRequestByUserId(Long
							.parseLong(sessionUser.getUserId()));
			List<WorkFromHome> workFromHomeRequests = workFromHomeDao
					.findAllApprovedWorkFromRequestByUserId(Long
							.parseLong(sessionUser.getUserId()));
			List<BankHolidayWorking> bankHolidayWorkingRequests = bankHolidayWorkingDao
					.findAllApprovedBankHolidayWorkingRequestByUserId(Long
							.parseLong(sessionUser.getUserId()));

			List<WeekendWorking> weekendWorkingRequests = weekendWorkingDao
					.findAllApprovedWeekendWorkingRequestByUserId(Long
							.parseLong(sessionUser.getUserId()));

			listApprovers(mapping, form, request, response);
			listHolidays(mapping, form, request, response);
			request.setAttribute("bankHolidayWorkingRequests",
					bankHolidayWorkingRequests);
			request.setAttribute("leaves", leaves);
			request.setAttribute("workFromHomeRequests", workFromHomeRequests);

			request.setAttribute("weekendWorkingRequests",
					weekendWorkingRequests);

		} catch (Exception e) {
			return mapping.findForward("success");

		}
		return mapping.findForward("success");

	}

	// to list all requests by user id

	public ActionForward listAllLeaveRequestByUserId(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		List<LeaveRequest> leaves = leaveRequestDao
				.findAllLeaveRequestByUserId(Long.parseLong(sessionUser
						.getUserId()));
		request.setAttribute("leaves", leaves);
		return mapping.findForward("leave");

	}

	public ActionForward listAllWeekendWorkingRequestByUserId(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		List<WeekendWorking> weekendWorkingRequests = weekendWorkingDao
				.findAllWeekendWorkingRequestByUserId(Long
						.parseLong(sessionUser.getUserId()));
		request.setAttribute("weekendWorkingRequests", weekendWorkingRequests);
		return mapping.findForward("weekendworking");
	}

	public ActionForward listAllWorkFromHomeRequestByUserId(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		List<WorkFromHome> workFromHomeRequests = workFromHomeDao
				.findAllWorkFromHomeRequestByUserId(Long.parseLong(sessionUser
						.getUserId()));
		request.setAttribute("workFromHomeRequests", workFromHomeRequests);
		return mapping.findForward("workfromhome");
	}

	public ActionForward listAllBankHolidayWorkingRequestByUserId(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		List<BankHolidayWorking> bankHolidayWorkingRequests = bankHolidayWorkingDao
				.findAllBankHolidayWorkingRequestByUserId(Long
						.parseLong(sessionUser.getUserId()));
		request.setAttribute("bankHolidayWorkingRequests",
				bankHolidayWorkingRequests);
		return mapping.findForward("bankholidayworking");
	}

	// List all requests by year to view detailed log
	public ActionForward listAllRequestByYear(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request.getParameter("year") == null) {
			int year = 2014;
			List<LeaveRequest> leaverequests = adminServices
					.findAllLeaveRequestByYear(year);
			List<WorkFromHome> workFromHomeRequests = adminServices
					.findAllWorkFromHomeRequestByYear(year);
			List<WeekendWorking> weekendWorkingRequests = adminServices
					.findAllWeekendWorkingRequestByYear(year);
			List<BankHolidayWorking> bankHolidayWorkingRequests = adminServices
					.findAllBankHolidayWorkingRequestByYear(year);
			request.setAttribute("leaves", leaverequests);
			request.setAttribute("workFromHomeRequests", workFromHomeRequests);
			request.setAttribute("bankHolidayWorkingRequests",
					bankHolidayWorkingRequests);
			request.setAttribute("weekendWorkingRequests",
					weekendWorkingRequests);

		} else {
			int year = Integer.parseInt(request.getParameter("year"));
			List<LeaveRequest> leaverequests = adminServices
					.findAllLeaveRequestByYear(year);
			List<WeekendWorking> weekendWorkingRequests = adminServices
					.findAllWeekendWorkingRequestByYear(year);
			List<WorkFromHome> workFromHomeRequests = adminServices
					.findAllWorkFromHomeRequestByYear(year);
			List<BankHolidayWorking> bankHolidayWorkingRequests = adminServices
					.findAllBankHolidayWorkingRequestByYear(year);
			request.setAttribute("leaves", leaverequests);
			request.setAttribute("weekendWorkingRequests",
					weekendWorkingRequests);
			request.setAttribute("workFromHomeRequests", workFromHomeRequests);
			request.setAttribute("bankHolidayWorkingRequests",
					bankHolidayWorkingRequests);
		}
		return mapping.findForward("success");

	}

	// To list pending requests of user
	public ActionForward listAllPendingRequestOfUser(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			com.thoughtservice.portal.user.User lmsUser;
			lmsUser = userServices
					.findUserByUserName(sessionUser.getUsername());
			List<LeaveRequest> leaves = leaveRequestDao
					.findAllPendingLeaveRequestByUserId(lmsUser.getId());
			List<WorkFromHome> workFromHomeRequests = workFromHomeDao
					.findAllPendingWorkFromRequestByUserId(lmsUser.getId());
			List<BankHolidayWorking> bankHolidayWorkingRequests = bankHolidayWorkingDao
					.findAllPendingBankHolidayWorkingRequestByUserId(lmsUser
							.getId());

			List<WeekendWorking> weekendWorkingRequests = weekendWorkingDao
					.findAllPendingWeekendWorkingRequestByUserId(lmsUser
							.getId());

			listApprovers(mapping, form, request, response);
			listHolidays(mapping, form, request, response);
			request.setAttribute("bankHolidayWorkingRequests",
					bankHolidayWorkingRequests);
			request.setAttribute("leaves", leaves);

			request.setAttribute("weekendWorkingRequests",
					weekendWorkingRequests);

			request.setAttribute("workFromHomeRequests", workFromHomeRequests);

		} catch (Exception e) {
			Notification statusNotification = new Notification(
					"Error occured, Please login once again", "999",
					STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}
		return mapping.findForward("success");
	}

	// Function to show profile details to user
	public ActionForward listUserDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();

			User user = userServices.findUserByUserName(sessionUser
					.getUsername());
			List<Experience> experience = experienceDao
					.listExperienceOfUser(user.getId());
			Iterator<Experience> iter = experience.iterator();
			while (iter.hasNext()) {
				System.out
						.println("jjjjjjjjjjj" + iter.next().getCompanyName());
			}
			request.setAttribute("experience", experience);
			request.setAttribute("user", user);

		} catch (Exception e) {
			Notification statusNotification = new Notification(
					"Error updating profile", "999", STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
			return mapping.findForward("success");
		}
		return mapping.findForward("success");
	}

	// Function to list all users and holidays to admin to mark leave page
	public ActionForward listDetailsToMarkLeave(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<User> users = null;
		users = userDao.findAllUsers();
		request.setAttribute("users", users);
		listHolidays(mapping, form, request, response);
		return mapping.findForward("success");

	}

	public ActionForward loadUserSkills(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		// Load calendar
		List<CalendarHolidays> events = calendarHolidaysDao.findAllHolidays();
		request.setAttribute("events", events);

		// Load Leaves
		List<LeaveRequest> leaves = leaveRequestDao
				.findAllApprovedLeaveRequestByUserId(Long
						.parseLong(sessionUser.getUserId()));
		if(leaves != null) {
			request.setAttribute("approvedLeavesCount", leaves.size());
		} else {
			request.setAttribute("approvedLeavesCount", "0");
		}
		
		//Load pending leave size
		List<LeaveRequest> pendingLeavesRequest = leaveRequestDao
				.findAllPendingLeaveRequestByUserId(Long.parseLong(sessionUser.getUserId()));
		if(pendingLeavesRequest.size() > 0) {
			request.setAttribute("pendingLeavesRequestCount", leaves.size());
		} else {
			request.setAttribute("pendingLeavesRequestCount", "0");
		}

		// Load Skill matrix
		List<UserSkills> userSkills = skillsDao.getRecentUserSkills(Long
				.parseLong(sessionUser.getUserId()));
		if(userSkills != null) {
			request.setAttribute("skillsCount", userSkills.size());
		} else {
			request.setAttribute("skillsCount", "0");
		}
		
		request.setAttribute("skills", userSkills);

		return mapping.findForward("success");

	}

	public ActionForward listUserHolidays(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<UserHolidays> userHolidays = userHolidaysDao.listAllUserHolidays();
		request.setAttribute("userHolidays", userHolidays);

		return mapping.findForward("success");
	}

	public ActionForward listAllRequestsOfLastTwoYearsByUserId(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			int year = Calendar.getInstance().get(Calendar.YEAR);
			PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			com.thoughtservice.portal.user.User lmsUser;
			lmsUser = userServices
					.findUserByUserName(sessionUser.getUsername());
			List<LeaveRequest> leaves = leaveRequestDao
					.findAllLeaveRequestsOfLastTwoYearsByUserId(
							lmsUser.getId(), year);
			List<WorkFromHome> workFromHomeRequests = workFromHomeDao
					.findAllWorkFormHomeRequestsOfLastTwoYearsByUserId(
							lmsUser.getId(), year);
			List<BankHolidayWorking> bankHolidayWorkingRequests = bankHolidayWorkingDao
					.findAllBankHolidayWorkingRequestsOfLastTwoYearsByUserId(
							lmsUser.getId(), year);

			List<WeekendWorking> weekendWorkingRequests = weekendWorkingDao
					.findAllWeekendWorkingRequestByUserId(lmsUser.getId());

			listApprovers(mapping, form, request, response);
			listHolidays(mapping, form, request, response);
			request.setAttribute("bankHolidayWorkingRequests",
					bankHolidayWorkingRequests);
			request.setAttribute("leaves", leaves);
			request.setAttribute("weekendWorkingRequests",
					weekendWorkingRequests);
			request.setAttribute("workFromHomeRequests", workFromHomeRequests);
		} catch (Exception e) {
			System.out.println(e);
		}
		return mapping.findForward("success");
	}

	public ActionForward listHolidaysToCalendar(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<CalendarHolidays> events = calendarHolidaysDao.findAllHolidays();
		// To set leave taken and remaining leave to user
		request.setAttribute("events", events);
		request.setAttribute("currentYear", LocalDate.now().getYear());
		return mapping.findForward("success");

	}

	public ActionForward listAllRequestOfUserBymonthAndYear(
			ActionMapping mapping, ActionForm form,// list requests by employee
													// code and month
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String employeecode = request.getParameter("employeecode");
			if (request.getParameter("employeecode") != null) {
				User user = userDao.findByEmployeeCode(Long.parseLong(request
						.getParameter("employeecode")));
				int year = Integer.parseInt(request.getParameter("year"));
				int month = Integer.parseInt(request.getParameter("month"));
				int lopLeaveRequests = 0;
				int bankHolidayWorkingPaid=0;
				int bankHolidayWorkingCompoff=0;
				int weekendWorkingPaid=0;
				int weekendWorkingCompoff=0;
				List<LeaveRequest> leaves = leaveRequestDao
						.findAllLeaveRequestsOfUserByMonthAndYear(user.getId(),
								month, year);
				List<WorkFromHome> workFromHomeRequests = workFromHomeDao
						.findAllWorkFormHomeRequestsOfUserByMonthAndYear(
								user.getId(), month, year);
				List<BankHolidayWorking> bankHolidayWorkingRequests = bankHolidayWorkingDao
						.findAllBankHolidayWorkingRequestsOfUserByMonthAndYear(
								user.getId(), month, year);

				List<WeekendWorking> weekendWorkingRequests = weekendWorkingDao
						.findAllWeekendWorkingRequestOfUserByMonthAndYear(
								user.getId(), month, year);

				
				// calculate compoff and paid bankholidayWorking
				Iterator<BankHolidayWorking> iterator = bankHolidayWorkingRequests
						.iterator();
				while (iterator.hasNext()) {
					if (iterator.next().getType().equalsIgnoreCase("paid"))
					{
						bankHolidayWorkingPaid++;
					}
					else
					{
						bankHolidayWorkingCompoff++;
					}
				}
				Iterator<WeekendWorking> iteratorWeekendWorking=weekendWorkingRequests.iterator();
				while (iteratorWeekendWorking.hasNext()) {
					if (iteratorWeekendWorking.next().getType().equalsIgnoreCase("paid"))
					{
						weekendWorkingPaid++;
					}
					else
					{
						weekendWorkingCompoff++;
					}
				}
				// calculate monthly leave of user
				/*
				 * for( int i=0;i<leaves.size();i++) {
				 * if(leaves.get(i).getLop==1) lopLeaveRequests++; }
				 */
				request.setAttribute("leaveRequests", leaves.size());
				request.setAttribute("workFromHome", workFromHomeRequests.size());
				//request.setAttribute("bankHolidayWorking", bankHolidayWorkingRequests.size());
			//	request.setAttribute("weekendWorking", weekendWorkingRequests.size());
				request.setAttribute("weekendWorkingCompoff", weekendWorkingCompoff);
				request.setAttribute("bankHolidayWorkingCompoff", bankHolidayWorkingCompoff);
				request.setAttribute("bankHolidayWorkingPaid",bankHolidayWorkingPaid);
				request.setAttribute("weekendWorkingPaid",weekendWorkingPaid);
				request.setAttribute("user", user);
				request.setAttribute("bankHolidayWorkingRequests",
						bankHolidayWorkingRequests);
				request.setAttribute("leaves", leaves);
				request.setAttribute("weekendWorkingRequests",
						weekendWorkingRequests);
				request.setAttribute("workFromHomeRequests",
						workFromHomeRequests);
			}
			List<User> users = userDao.findAllUsers();
			request.setAttribute("users", users);
		} catch (Exception e) {
			System.out.println(e);
		}
		return mapping.findForward("success");
	}

	public void setCalendarHolidaysDao(CalendarHolidaysDao calendarHolidaysDao) {
		this.calendarHolidaysDao = calendarHolidaysDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setManagerServices(ManagerServices managerServices) {
		this.managerServices = managerServices;
	}

	public void setUserServices(UserServices userServices) {
		this.userServices = userServices;
	}

	public LeaveRequestDao getLeaveRequestDao() {
		return leaveRequestDao;
	}

	public void setLeaveRequestDao(LeaveRequestDao leaveRequestDao) {
		this.leaveRequestDao = leaveRequestDao;
	}

	public void setWorkFromHomeDao(WorkFromHomeDao workFromHomeDao) {
		this.workFromHomeDao = workFromHomeDao;
	}

	public void setBankHolidayWorkingDao(
			BankHolidayWorkingDao bankHolidayWorkingDao) {
		this.bankHolidayWorkingDao = bankHolidayWorkingDao;
	}

	public void setAdminServices(AdminServices adminServices) {
		this.adminServices = adminServices;
	}

	public void setSkillsDao(SkillsDao skillsDao) {
		this.skillsDao = skillsDao;
	}

	public void setCalculateLeaves(CalculateLeaves calculateLeaves) {
		this.calculateLeaves = calculateLeaves;
	}

	public void setUserHolidaysDao(UserHolidaysDao userHolidaysDao) {
		this.userHolidaysDao = userHolidaysDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public void setWeekendWorkingDao(WeekendWorkingDao weekendWorkingDao) {
		this.weekendWorkingDao = weekendWorkingDao;
	}

	public void setAttendanceDao(AttendanceDao attendanceDao) {
		this.attendanceDao = attendanceDao;
	}

	public void setExperienceDao(ExperienceDao experienceDao) {
		this.experienceDao = experienceDao;
	}

}
