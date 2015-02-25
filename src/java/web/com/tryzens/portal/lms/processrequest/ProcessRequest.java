package com.tryzens.portal.lms.processrequest;

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

import com.tryzens.portal.jobs.calculateleaves.CalculateLeaves;
import com.tryzens.portal.lms.common.details.ListDetails;
import com.tryzens.portal.lms.common.notifications.Notification;
import com.tryzens.portal.lms.common.notifications.Notification.STATUS;
import com.tryzens.portal.login.service.PortalUserDetails;
import com.tryzens.portal.user.request.bankholidayworking.BankHolidayWorking;
import com.tryzens.portal.user.request.bankholidayworking.dao.BankHolidayWorkingDao;
import com.tryzens.portal.user.request.leaverequest.LeaveRequest;
import com.tryzens.portal.user.request.leaverequest.dao.LeaveRequestDao;
import com.tryzens.portal.user.request.weekendworking.WeekendWorking;
import com.tryzens.portal.user.request.weekendworking.dao.WeekendWorkingDao;
import com.tryzens.portal.user.request.workfromhome.WorkFromHome;
import com.tryzens.portal.workfromhome.dao.WorkFromHomeDao;

@Transactional
public class ProcessRequest extends DispatchAction {
	private BankHolidayWorkingDao bankHolidayWorkingDao;
	private ListDetails listDetails;
	private LeaveRequestDao leaveRequestDao;
	private WorkFromHomeDao workFromHomeDao;
	private WeekendWorkingDao weekendWorkingDao;
	private CalculateLeaves calculateLeaves;
	private static final Log LOGGER = LogFactory.getLog(ProcessRequest.class);

	// To process accept/reject of bank holiday working request from manager
	public ActionForward bankHolidayWorkingRequest(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			BankHolidayWorking bankHolidayWorking = new BankHolidayWorking();
			bankHolidayWorking.setId(Long.parseLong(request
					.getParameter("requestId")));
			bankHolidayWorking = bankHolidayWorkingDao
					.findRequestByRequestId(bankHolidayWorking.getId());
			if (request.getParameter("process").equalsIgnoreCase("accept"))
				bankHolidayWorking.setStatus("Approved");
			else if (request.getParameter("process").equalsIgnoreCase("reject"))
				bankHolidayWorking.setStatus("Rejected");
			bankHolidayWorking.setApproversComment(request
					.getParameter("Comments"));
			try {

				bankHolidayWorking.setLastUpdate(new Date());
				bankHolidayWorking.setLmsInternal("processed_by_"
						+ sessionUser.getUsername());
				bankHolidayWorkingDao.updateStatus(bankHolidayWorking);
				if (request.getParameter("process").equalsIgnoreCase("accept"))
					calculateLeaves.addLeave(bankHolidayWorking);
			} catch (Exception e) {
				LOGGER.error(e);
				Notification statusNotification = new Notification(
						"Error updating request, please try again" + e, "000",
						STATUS.WARNING);
				request.setAttribute("notification", statusNotification);
				listDetails
						.listPendingRequest(mapping, form, request, response);
				return mapping.findForward("success");
			}

			Notification statusNotification = new Notification(
					"Updated successfully", "000", STATUS.SUCCESS);
			request.setAttribute("notification", statusNotification);
		} catch (Exception e) {
			LOGGER.error(e);
			Notification statusNotification = new Notification(
					"Request Not Found!" + e, "000", STATUS.WARNING);
			request.setAttribute("notification", statusNotification);
		}
		listDetails.listPendingRequest(mapping, form, request, response);
		return mapping.findForward("success");
	}

	// To process accept/reject of leave request from manager
	public ActionForward leaveRequest(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			LeaveRequest leaveRequest = new LeaveRequest();
			leaveRequest
					.setId(Long.parseLong(request.getParameter("requestId")));
			leaveRequest = leaveRequestDao.findRequestByRequestId(leaveRequest
					.getId());
			// try{
			if (request.getParameter("process").equalsIgnoreCase("accept")) {
				leaveRequest.setStatus("Approved");
				calculateLeaves.addLeave(leaveRequest);

			} else if (request.getParameter("process").equalsIgnoreCase(
					"reject")) {
				leaveRequest.setStatus("Rejected");
				leaveRequest.setApproversComment(request
						.getParameter("Comments"));

			}
			leaveRequest.setLmsInternal("Processed_by_"
					+ sessionUser.getUsername());
			leaveRequest.setLastUpdate(new Date());
			leaveRequestDao.updateStatus(leaveRequest);
			Notification statusNotification = new Notification(
					"Updated successfully", "000", STATUS.SUCCESS);
			request.setAttribute("notification", statusNotification);
		} catch (Exception e) {

			LOGGER.error("Error Occured ", e);
			Notification statusNotification = new Notification(
					"Request Not Found!" + e, "000", STATUS.WARNING);
			request.setAttribute("notification", statusNotification);
		}
		listDetails.listPendingRequest(mapping, form, request, response);
		return mapping.findForward("success");
	}

	// To process accept/reject of work from home request from manager
	public ActionForward workFromHomeRequest(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			WorkFromHome workFromHome = new WorkFromHome();
			workFromHome
					.setId(Long.parseLong(request.getParameter("requestId")));
			workFromHome = workFromHomeDao.findRequestByRequestId(workFromHome
					.getId());
			if (request.getParameter("process").equalsIgnoreCase("accept")) {
				workFromHome.setStatus("Approved");
				calculateLeaves.addLeave(workFromHome);
			} else if (request.getParameter("process").equalsIgnoreCase(
					"reject"))
				workFromHome.setStatus("Rejected");
			workFromHome.setApproversComment(request.getParameter("Comments"));
			workFromHome.setLmsInternal("processed_by_"
					+ sessionUser.getUsername());
			workFromHome.setLastUpdate(new Date());
			workFromHomeDao.updateStatus(workFromHome);

			Notification statusNotification = new Notification(
					"Updated successfully", "000", STATUS.SUCCESS);
			request.setAttribute("notification", statusNotification);
		} catch (Exception e) {
			LOGGER.error(e);

			Notification statusNotification = new Notification(
					"Request Not Found!" + e, "000", STATUS.WARNING);
			request.setAttribute("notification", statusNotification);
		}
		listDetails.listPendingRequest(mapping, form, request, response);
		return mapping.findForward("success");

	}

	// To process accept/reject of weekend working request from manager

	public ActionForward weekendWorkingRequest(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			WeekendWorking weekendWorking = new WeekendWorking();
			weekendWorking.setId(Long.parseLong(request
					.getParameter("requestId")));
			weekendWorking = weekendWorkingDao
					.findRequestByRequestId(weekendWorking.getId());
			if (request.getParameter("process").equalsIgnoreCase("accept")) {
				weekendWorking.setStatus("Approved");
				calculateLeaves.addLeave(weekendWorking);
			} else if (request.getParameter("process").equalsIgnoreCase(
					"reject"))
				weekendWorking.setStatus("Rejected");
			weekendWorking
					.setApproversComment(request.getParameter("Comments"));
			weekendWorking.setLmsInternal("processed_by_"
					+ sessionUser.getUsername());
			weekendWorking.setLastUpdate(new Date());
			weekendWorkingDao.updateStatus(weekendWorking);

			Notification statusNotification = new Notification(
					"Updated successfully", "000", STATUS.SUCCESS);
			request.setAttribute("notification", statusNotification);
		} catch (Exception e) {
			LOGGER.error(e);

			Notification statusNotification = new Notification(
					"Request Not Found!" + e, "000", STATUS.WARNING);
			request.setAttribute("notification", statusNotification);
		}
		listDetails.listPendingRequest(mapping, form, request, response);
		return mapping.findForward("success");

	}

	// To process cancel pending leave request by user
	public ActionForward cancelPendingLeaveRequest(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			LeaveRequest leaveRequest = new LeaveRequest();
			leaveRequest
					.setId(Long.parseLong(request.getParameter("requestId")));
			leaveRequest = leaveRequestDao.findRequestByRequestId(leaveRequest
					.getId());
			if (request.getParameter("process").equalsIgnoreCase("cancel"))
				leaveRequest.setStatus("Cancelled");
			leaveRequest.setLmsInternal("processed_by_"
					+ sessionUser.getUsername());
			leaveRequestDao.updateStatus(leaveRequest);
		} catch (Exception e) {
			LOGGER.error(e);
			Notification statusNotification = new Notification(
					"Request Not Found!", "000", STATUS.WARNING);
			request.setAttribute("notification", statusNotification);
		}
		Notification statusNotification = new Notification(
				"Updated successfully", "000", STATUS.SUCCESS);
		request.setAttribute("notification", statusNotification);
		listDetails.listAllPendingRequestOfUser(mapping, form, request,
				response);
		return mapping.findForward("cancelledByUser");
	}

	// To process cancel pending work from home request by user
	public ActionForward cancelPendingWorkFromHomeRequest(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			WorkFromHome workFromHome = new WorkFromHome();
			workFromHome
					.setId(Long.parseLong(request.getParameter("requestId")));
			workFromHome = workFromHomeDao.findRequestByRequestId(workFromHome
					.getId());
			if (request.getParameter("process").equalsIgnoreCase("cancel"))
				workFromHome.setStatus("Cancelled");
			else if (request.getParameter("process").equalsIgnoreCase("reject"))
				workFromHome.setStatus("Rejected");
			workFromHome.setLmsInternal("processed_by_"
					+ sessionUser.getUsername());
			workFromHome.setLastUpdate(new Date());
			workFromHomeDao.updateStatus(workFromHome);
		} catch (Exception e) {
			LOGGER.error(e);
			Notification statusNotification = new Notification(
					"Request Not Found!", "000", STATUS.WARNING);
			request.setAttribute("notification", statusNotification);
		}
		Notification statusNotification = new Notification(
				"Updated successfully", "000", STATUS.SUCCESS);
		request.setAttribute("notification", statusNotification);
		listDetails.listAllPendingRequestOfUser(mapping, form, request,
				response);
		return mapping.findForward("cancelledByUser");

	}

	// To process cancel pending weekend working request by user
	public ActionForward cancelPendingWeekendWorkingRequest(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			WeekendWorking weekendWorking = new WeekendWorking();
			weekendWorking.setId(Long.parseLong(request
					.getParameter("requestId")));

			weekendWorking = weekendWorkingDao
					.findRequestByRequestId(weekendWorking.getId());

			if (request.getParameter("process").equalsIgnoreCase("cancel"))
				weekendWorking.setStatus("Cancelled");
			else if (request.getParameter("process").equalsIgnoreCase("reject"))
				weekendWorking.setStatus("Rejected");

			weekendWorking.setLmsInternal("processed_by_"
					+ sessionUser.getUsername());
			weekendWorking.setLastUpdate(new Date());
			weekendWorkingDao.updateStatus(weekendWorking);

		} catch (Exception e) {
			LOGGER.error(e);
			Notification statusNotification = new Notification(
					"Request Not Found!", "000", STATUS.WARNING);
			request.setAttribute("notification", statusNotification);
		}
		Notification statusNotification = new Notification(
				"Updated successfully", "000", STATUS.SUCCESS);
		request.setAttribute("notification", statusNotification);
		listDetails.listAllPendingRequestOfUser(mapping, form, request,
				response);
		return mapping.findForward("cancelledByUser");

	}

	// To process cancel pending bank holiday working request
	public ActionForward cancelPendingBankHolidayWorkingRequest(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			BankHolidayWorking bankHolidayWorking = new BankHolidayWorking();
			bankHolidayWorking.setId(Long.parseLong(request
					.getParameter("requestId")));
			bankHolidayWorking = bankHolidayWorkingDao
					.findRequestByRequestId(bankHolidayWorking.getId());
			if (request.getParameter("process").equalsIgnoreCase("cancel"))
				bankHolidayWorking.setStatus("Cancelled");
			bankHolidayWorking.setLastUpdate(new Date());
			bankHolidayWorking.setLmsInternal("processed_by_"
					+ sessionUser.getUsername());
			bankHolidayWorkingDao.updateStatus(bankHolidayWorking);
		} catch (Exception e) {
			LOGGER.error(e);
			Notification statusNotification = new Notification(
					"Request Not Found!", "000", STATUS.WARNING);
			request.setAttribute("notification", statusNotification);
		}
		Notification statusNotification = new Notification(
				"Updated successfully", "000", STATUS.SUCCESS);
		request.setAttribute("notification", statusNotification);
		listDetails.listAllPendingRequestOfUser(mapping, form, request,
				response);
		return mapping.findForward("cancelledByUser");
	}

	public void setBankHolidayWorkingDao(
			BankHolidayWorkingDao bankHolidayWorkingDao) {
		this.bankHolidayWorkingDao = bankHolidayWorkingDao;
	}

	public void setListDetails(ListDetails listDetails) {
		this.listDetails = listDetails;
	}

	public void setLeaveRequestDao(LeaveRequestDao leaveRequestDao) {
		this.leaveRequestDao = leaveRequestDao;
	}

	public void setWorkFromHomeDao(WorkFromHomeDao workFromHomeDao) {
		this.workFromHomeDao = workFromHomeDao;
	}

	public void setCalculateLeaves(CalculateLeaves calculateLeaves) {
		this.calculateLeaves = calculateLeaves;
	}

	public void setWeekendWorkingDao(WeekendWorkingDao weekendWorkingDao) {
		this.weekendWorkingDao = weekendWorkingDao;
	}

}
