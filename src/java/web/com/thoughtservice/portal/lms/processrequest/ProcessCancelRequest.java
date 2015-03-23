package com.thoughtservice.portal.lms.processrequest;

import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import com.thoughtservice.portal.jobs.calculateleaves.CalculateLeaves;
import com.thoughtservice.portal.lms.common.details.ListDetails;
import com.thoughtservice.portal.lms.common.details.ListRequests;
import com.thoughtservice.portal.lms.common.notifications.Notification;
import com.thoughtservice.portal.lms.common.notifications.Notification.STATUS;
import com.thoughtservice.portal.login.service.PortalUserDetails;
import com.thoughtservice.portal.user.request.bankholidayworking.BankHolidayWorking;
import com.thoughtservice.portal.user.request.bankholidayworking.dao.BankHolidayWorkingDao;
import com.thoughtservice.portal.user.request.leaverequest.LeaveRequest;
import com.thoughtservice.portal.user.request.leaverequest.dao.LeaveRequestDao;
import com.thoughtservice.portal.user.request.weekendworking.WeekendWorking;
import com.thoughtservice.portal.user.request.weekendworking.dao.WeekendWorkingDao;
import com.thoughtservice.portal.user.request.workfromhome.WorkFromHome;
import com.thoughtservice.portal.workfromhome.dao.WorkFromHomeDao;

@Transactional
public class ProcessCancelRequest extends DispatchAction {
	private static final Log LOGGER = LogFactory
			.getLog(ProcessCancelRequest.class);
	private BankHolidayWorkingDao bankHolidayWorkingDao;
	private LeaveRequestDao leaveRequestDao;
	private WorkFromHomeDao workFromHomeDao;
	private WeekendWorkingDao weekendWorkingDao;
	private ListRequests listRequests;
	private CalculateLeaves calculateLeaves;
	private ListDetails listDetails;

	public ActionForward cancelBankHolidayWorkingRequest(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		BankHolidayWorking bankHolidayWorking = new BankHolidayWorking();
		try {
			bankHolidayWorking.setId(Long.parseLong(request
					.getParameter("requestId")));
			bankHolidayWorking = bankHolidayWorkingDao
					.findRequestByRequestId(bankHolidayWorking.getId());
			if (request.getParameter("process").equalsIgnoreCase(
					"CancelApprovedRequest"))
				bankHolidayWorking.setStatus("cancelled");
			else if (request.getParameter("process").equalsIgnoreCase("abort")) {
				bankHolidayWorking.setStatus("CancellationRejected");
				bankHolidayWorking.setApproversComment(request
						.getParameter("Comments"));
			}
			bankHolidayWorking.setLastUpdate(new Date());
			bankHolidayWorking.setLmsInternal("Processed by" + ""
					+ sessionUser.getUsername());
			bankHolidayWorkingDao.updateStatus(bankHolidayWorking);
			if (request.getParameter("process").equalsIgnoreCase(
					"CancelApprovedRequest"))
				calculateLeaves.substractLeave(bankHolidayWorking);
			listRequests.listRequestsForApproval(mapping, form, request,
					response);
			Notification statusNotification = new Notification(
					"Updated successfully", "000", STATUS.SUCCESS);
			request.setAttribute("notification", statusNotification);
			return mapping.findForward("success");
		} catch (Exception e) {
			LOGGER.error(e);
			listRequests.listRequestsForApproval(mapping, form, request,
					response);
			Notification statusNotification = new Notification(
					"error updating, please try again", "000", STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}
		return mapping.findForward("success");
	}

	// To process cancel leave request from manager
	public ActionForward cancelLeaveRequest(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		LeaveRequest leaveRequest = new LeaveRequest();
		try {
			leaveRequest
					.setId(Long.parseLong(request.getParameter("requestId")));
			leaveRequest = leaveRequestDao.findRequestByRequestId(leaveRequest
					.getId());
			if (request.getParameter("process").equalsIgnoreCase(
					"CancelApprovedRequest"))
				leaveRequest.setStatus("cancelled");
			else if (request.getParameter("process").equalsIgnoreCase("abort")) {
				leaveRequest.setStatus("CancellationRejected");
				leaveRequest.setApproversComment(request
						.getParameter("Comments"));
			}
			leaveRequest.setLastUpdate(new Date());
			leaveRequest.setLmsInternal("Processed by" + ""
					+ sessionUser.getUsername());
			leaveRequest.setLastUpdate(new Date());
			leaveRequestDao.updateStatus(leaveRequest);
			if (request.getParameter("process").equalsIgnoreCase(
					"CancelApprovedRequest"))// to calculate leaves in
												// USER_HOLIDAYS table
				calculateLeaves.substractLeave(leaveRequest);
			listRequests.listRequestsForApproval(mapping, form, request,
					response);
			Notification statusNotification = new Notification(
					"Updated successfully", "000", STATUS.SUCCESS);
			request.setAttribute("notification", statusNotification);
		} catch (Exception e) {
			listRequests.listRequestsForApproval(mapping, form, request,
					response);
			Notification statusNotification = new Notification(
					"error updating, please try again", "000", STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}

		return mapping.findForward("success");

	}

	// To process cancel workfromhome request from manager
	public ActionForward cancelWorkFromHomeRequest(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		WorkFromHome workFromHome = new WorkFromHome();
		try {
			workFromHome
					.setId(Long.parseLong(request.getParameter("requestId")));
			workFromHome = workFromHomeDao.findRequestByRequestId(workFromHome
					.getId());
			if (request.getParameter("process").equalsIgnoreCase(
					"CancelApprovedRequest"))
				workFromHome.setStatus("Cancelled");
			else if (request.getParameter("process").equalsIgnoreCase("abort")) {
				workFromHome.setStatus("CancellationRejected");
				workFromHome.setApproversComment(request
						.getParameter("Comments"));
			}
			workFromHome.setLastUpdate(new Date());
			workFromHome.setLmsInternal("Processed by" + ""
					+ sessionUser.getUsername());
			workFromHomeDao.updateStatus(workFromHome);
			if (workFromHome.getStatus().equalsIgnoreCase("Cancelled"))
				calculateLeaves.substractLeave(workFromHome);
			listRequests.listRequestsForApproval(mapping, form, request,
					response);
			Notification statusNotification = new Notification(
					"Updated successfully", "000", STATUS.SUCCESS);
			request.setAttribute("notification", statusNotification);
		} catch (Exception e) {
			LOGGER.error(e);
			listRequests.listRequestsForApproval(mapping, form, request,
					response);
			Notification statusNotification = new Notification(
					"error updating, please try again", "000", STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}
		return mapping.findForward("success");

	}

	// To process cancel weekendworking request from manager
	public ActionForward cancelWeekendWorkingRequest(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		WeekendWorking weekendWorking = new WeekendWorking();
		try {
			weekendWorking.setId(Long.parseLong(request
					.getParameter("requestId")));
			weekendWorking = weekendWorkingDao
					.findRequestByRequestId(weekendWorking.getId());
			if (request.getParameter("process").equalsIgnoreCase(
					"CancelApprovedRequest"))
				weekendWorking.setStatus("Cancelled");
			else if (request.getParameter("process").equalsIgnoreCase("abort")) {
				weekendWorking.setStatus("CancellationRejected");
				weekendWorking.setApproversComment(request
						.getParameter("Comments"));
			}
			weekendWorking.setLastUpdate(new Date());
			weekendWorking.setLmsInternal("Processed by" + ""
					+ sessionUser.getUsername());
			weekendWorkingDao.updateStatus(weekendWorking);
			if (weekendWorking.getStatus().equalsIgnoreCase("Cancelled"))
				calculateLeaves.substractLeave(weekendWorking);
			listRequests.listRequestsForApproval(mapping, form, request,
					response);
			Notification statusNotification = new Notification(
					"Updated successfully", "000", STATUS.SUCCESS);
			request.setAttribute("notification", statusNotification);
		} catch (Exception e) {

			LOGGER.error(e);
			listRequests.listRequestsForApproval(mapping, form, request,
					response);
			Notification statusNotification = new Notification(
					"error updating, please try again", "000", STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}
		return mapping.findForward("success");
	}

	public void setBankHolidayWorkingDao(
			BankHolidayWorkingDao bankHolidayWorkingDao) {
		this.bankHolidayWorkingDao = bankHolidayWorkingDao;
	}

	public void setLeaveRequestDao(LeaveRequestDao leaveRequestDao) {
		this.leaveRequestDao = leaveRequestDao;
	}

	public void setWorkFromHomeDao(WorkFromHomeDao workFromHomeDao) {
		this.workFromHomeDao = workFromHomeDao;
	}

	public void setListRequests(ListRequests listRequests) {
		this.listRequests = listRequests;
	}

	public void setCalculateLeaves(CalculateLeaves calculateLeaves) {
		this.calculateLeaves = calculateLeaves;
	}

	public void setWeekendWorkingDao(WeekendWorkingDao weekendWorkingDao) {
		this.weekendWorkingDao = weekendWorkingDao;
	}
}
