package com.thoughtservice.portal.lms.common.details;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.security.core.context.SecurityContextHolder;

import com.thoughtservice.portal.lms.admin.task.AdminServices;
import com.thoughtservice.portal.lms.manager.task.ManagerServices;
import com.thoughtservice.portal.login.service.PortalUserDetails;
import com.thoughtservice.portal.role.Role;
import com.thoughtservice.portal.user.User;
import com.thoughtservice.portal.user.dao.RoleDao;
import com.thoughtservice.portal.user.dao.UserDao;
import com.thoughtservice.portal.user.request.bankholidayworking.BankHolidayWorking;
import com.thoughtservice.portal.user.request.bankholidayworking.UserBankHolidayVO;
import com.thoughtservice.portal.user.request.leaverequest.LeaveRequest;
import com.thoughtservice.portal.user.request.leaverequest.UserLeaveRequestVO;
import com.thoughtservice.portal.user.request.weekendworking.UserWeekendWorkingVO;
import com.thoughtservice.portal.user.request.weekendworking.WeekendWorking;
import com.thoughtservice.portal.user.request.workfromhome.UserWorkFromHomeVO;
import com.thoughtservice.portal.user.request.workfromhome.WorkFromHome;

public class ListRequests extends DispatchAction {
	private UserDao userDao;
	private ManagerServices managerServices;
	private ListDetails listDetails;
	private RoleDao roleDao;
	private AdminServices adminServices;

	// Function to list all requests for cancellation of leave
	public ActionForward listLeaveRequestForCancellation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		Role role = roleDao.findUserRoleByUserId(Long.parseLong(sessionUser
				.getUserId()));
		List<LeaveRequest> pendingLeaveRequestForCancellation = new ArrayList<LeaveRequest>();
		if (role.getName().equalsIgnoreCase("ROLE_MANAGER")) {
			pendingLeaveRequestForCancellation.addAll(managerServices
					.listPendingLeaveRequestForCancellation(Long
							.parseLong(sessionUser.getUserId())));
		} else if (role.getName().equalsIgnoreCase("ROLE_ADMIN")) {
			pendingLeaveRequestForCancellation.addAll(adminServices
					.listPendingLeaveRequestForCancellation());
		}
		ArrayList<UserLeaveRequestVO> userLeaveCancellationValueList = new ArrayList<UserLeaveRequestVO>();// add
		if (userLeaveCancellationValueList != null)
			for (LeaveRequest item : pendingLeaveRequestForCancellation) {
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
				userLeaveCancellationValueList.add(userLeaveVO);
			}
		request.setAttribute("userLeaveCancellationValueList",
				userLeaveCancellationValueList);
		return mapping.findForward("success");

	}

	// Function to list all requests for cancellation of work from home
	public ActionForward listWorkFromHomeRequestForCancellation(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		Role role = roleDao.findUserRoleByUserId(Long.parseLong(sessionUser
				.getUserId()));
		List<WorkFromHome> pendingWorkFromHomeRequestForCancellation = new ArrayList<WorkFromHome>();
		if (role.getName().equalsIgnoreCase("ROLE_MANAGER")) {
			pendingWorkFromHomeRequestForCancellation.addAll(managerServices
					.listPendingWorkFromHomeRequestForCancellation(Long
							.parseLong(sessionUser.getUserId())));
		} else if (role.getName().equalsIgnoreCase("ROLE_ADMIN")) {
			pendingWorkFromHomeRequestForCancellation.addAll(adminServices
					.listPendingWorkFromHomeRequestForCancellation());
		}
		ArrayList<UserWorkFromHomeVO> userWorkFromHomeCancellationValueList = new ArrayList<UserWorkFromHomeVO>();
		if (pendingWorkFromHomeRequestForCancellation != null)
			for (WorkFromHome item : pendingWorkFromHomeRequestForCancellation) {
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
				userWorkFromHomeCancellationValueList.add(userWorkFromHomeVO);
			}

		request.setAttribute("userWorkFromHomeCancellationValueList",
				userWorkFromHomeCancellationValueList);
		return mapping.findForward("success");

	}

	// Function to list all requests for cancellation of work from home
	public ActionForward listWeekendWorkingRequestForCancellation(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		Role role = roleDao.findUserRoleByUserId(Long.parseLong(sessionUser
				.getUserId()));
		List<WeekendWorking> pendingWeekendWorkingRequestForCancellation = new ArrayList<WeekendWorking>();

		if (role.getName().equalsIgnoreCase("ROLE_MANAGER")) {
			pendingWeekendWorkingRequestForCancellation.addAll(managerServices
					.listPendingWeekendWorkingRequestForCancellation(Long
							.parseLong(sessionUser.getUserId())));
		} else if (role.getName().equalsIgnoreCase("ROLE_ADMIN")) {
			pendingWeekendWorkingRequestForCancellation.addAll(adminServices
					.listPendingWeekendWorkingRequestForCancellation());
		}
		ArrayList<UserWeekendWorkingVO> userWeekendWorkingCancellationValueList = new ArrayList<UserWeekendWorkingVO>();
		if (pendingWeekendWorkingRequestForCancellation != null)
			for (WeekendWorking item : pendingWeekendWorkingRequestForCancellation) {
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
				userWeekendWorkingCancellationValueList
						.add(userWeekendWorkingVO);
			}

		request.setAttribute("userWeekendWorkingCancellationValueList",
				userWeekendWorkingCancellationValueList);
		return mapping.findForward("success");

	}

	// Function to list all requests for cancellation of bank holiday working
	public ActionForward listBankHolidayWorkingRequestForCancellation(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		Role role = roleDao.findUserRoleByUserId(Long.parseLong(sessionUser
				.getUserId()));
		List<BankHolidayWorking> pendingBankHolidayWorkingRequestForCancellation = new ArrayList<BankHolidayWorking>();
		if (role.getName().equalsIgnoreCase("ROLE_MANAGER")) {
			pendingBankHolidayWorkingRequestForCancellation
					.addAll(managerServices
							.listPendingBankHolidayWorkingRequestForCancellation(Long
									.parseLong(sessionUser.getUserId())));
		} else if (role.getName().equalsIgnoreCase("ROLE_ADMIN")) {
			pendingBankHolidayWorkingRequestForCancellation
					.addAll(adminServices
							.listPendingBankHolidayWorkingRequestForCancellation());
		}
		ArrayList<UserBankHolidayVO> userBankHolidayCancellationValueList = new ArrayList<UserBankHolidayVO>();
		if (pendingBankHolidayWorkingRequestForCancellation != null)
			for (BankHolidayWorking item : pendingBankHolidayWorkingRequestForCancellation) {
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
				userBankHolidayCancellationValueList.add(userBankHolidayVO);

			}
		request.setAttribute("userBankHolidayCancellationValueList",
				userBankHolidayCancellationValueList);
		return mapping.findForward("success");

	}

	// Function to list all requests for cancellation of approved requests
	public ActionForward listRequestsForApproval(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		listLeaveRequestForCancellation(mapping, form, request, response);
		listWorkFromHomeRequestForCancellation(mapping, form, request, response);
		listWeekendWorkingRequestForCancellation(mapping, form, request,
				response);
		listBankHolidayWorkingRequestForCancellation(mapping, form, request,
				response);
		listDetails.listPendingRequest(mapping, form, request, response);

		return mapping.findForward("success");

	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setManagerServices(ManagerServices managerServices) {
		this.managerServices = managerServices;
	}

	public void setListDetails(ListDetails listDetails) {
		this.listDetails = listDetails;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public void setAdminServices(AdminServices adminServices) {
		this.adminServices = adminServices;
	}
}
