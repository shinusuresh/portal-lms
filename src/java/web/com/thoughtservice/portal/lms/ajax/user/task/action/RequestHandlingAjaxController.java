package com.thoughtservice.portal.lms.ajax.user.task.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thoughtservice.portal.lms.user.task.UserServices;
import com.thoughtservice.portal.login.service.PortalUserDetails;
import com.thoughtservice.portal.user.dao.UserDao;
import com.thoughtservice.portal.user.request.bankholidayworking.BankHolidayWorking;
import com.thoughtservice.portal.user.request.bankholidayworking.dao.BankHolidayWorkingDao;
import com.thoughtservice.portal.user.request.leaverequest.LeaveRequest;
import com.thoughtservice.portal.user.request.leaverequest.dao.LeaveRequestDao;
import com.thoughtservice.portal.user.request.weekendworking.WeekendWorking;
import com.thoughtservice.portal.user.request.weekendworking.dao.WeekendWorkingDao;
import com.thoughtservice.portal.user.request.workfromhome.WorkFromHome;
import com.thoughtservice.portal.workfromhome.dao.WorkFromHomeDao;

@Controller
@RequestMapping("/requests")
public class RequestHandlingAjaxController {

	
	private static final Log LOGGER = LogFactory
			.getLog(RequestHandlingAjaxController.class);


	@Autowired
	private UserDao userDao;

	@Autowired
	private LeaveRequestDao leaveRequestDao;
	
	@Autowired
	private UserServices userServices;

	@Autowired
	private BankHolidayWorkingDao bankHolidayWorkingDao;
	
	@Autowired
	private WeekendWorkingDao weekendWorkingDao;
	
	@Autowired
	private WorkFromHomeDao workFromHomeDao;
	
	@Transactional
	@RequestMapping(value = "getleavesforuser.ajax", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<LeaveRequest> leavesForUser(@RequestParam String month,int year) {
		LOGGER.debug("Getting all leave requests for user ");
		PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		com.thoughtservice.portal.user.User lmsUser = new com.thoughtservice.portal.user.User();
		lmsUser = userServices.findUserByUserName(sessionUser
				.getUsername());
		int mnth= Integer.parseInt(month);
		List<LeaveRequest> leaves =new ArrayList<LeaveRequest>();
		leaves =leaveRequestDao
				.findAllLeaveRequestsOfUserByMonthAndYear(lmsUser.getId(),mnth, year);
		return leaves;
	}
	
	@Transactional
	@RequestMapping(value = "getbankholidayworkingrequestsforuser.ajax", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<BankHolidayWorking> bankHolidayWorkingReqestsForUser(@RequestParam String month,int year) {
		LOGGER.debug("Getting all bank holiday working  requests for user ");
		PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		com.thoughtservice.portal.user.User lmsUser = new com.thoughtservice.portal.user.User();
		lmsUser = userServices.findUserByUserName(sessionUser
				.getUsername());
		int mnth= Integer.parseInt(month);
		List<BankHolidayWorking> bankHolidayWorkingRequests =new ArrayList<BankHolidayWorking>();
		bankHolidayWorkingRequests =bankHolidayWorkingDao.findAllBankHolidayWorkingRequestsOfUserByMonthAndYear(lmsUser.getId(),mnth, year);
		return bankHolidayWorkingRequests;
	}
	
	@Transactional
	@RequestMapping(value = "getweekendworkingrequestsforuser.ajax", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<WeekendWorking> weekendWorkingReqestsForUser(@RequestParam String month,int year) {
		LOGGER.debug("Getting all bank holiday working  requests for user ");
		PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		com.thoughtservice.portal.user.User lmsUser = new com.thoughtservice.portal.user.User();
		lmsUser = userServices.findUserByUserName(sessionUser
				.getUsername());
		int mnth= Integer.parseInt(month);
		List<WeekendWorking> weekendWorkingReqestsForUser =new ArrayList<WeekendWorking>();
		weekendWorkingReqestsForUser =weekendWorkingDao.findAllWeekendWorkingRequestOfUserByMonthAndYear(lmsUser.getId(),mnth, year);
		return weekendWorkingReqestsForUser;
	}
	
	@Transactional
	@RequestMapping(value = "getworkfromhomerequestsforuser.ajax", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<WorkFromHome> workFromHomeReqestsForUser(@RequestParam String month,int year) {
		LOGGER.debug("Getting all bank holiday working  requests for user ");
		PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		com.thoughtservice.portal.user.User lmsUser = new com.thoughtservice.portal.user.User();
		lmsUser = userServices.findUserByUserName(sessionUser
				.getUsername());
		int mnth= Integer.parseInt(month);
		List<WorkFromHome> workFromHomeReqestsForUser =new ArrayList<WorkFromHome>();
		workFromHomeReqestsForUser =workFromHomeDao.findAllWorkFormHomeRequestsOfUserByMonthAndYear(lmsUser.getId(),mnth, year);
		return workFromHomeReqestsForUser;
	}
}
