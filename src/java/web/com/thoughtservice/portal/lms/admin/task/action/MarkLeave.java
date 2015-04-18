package com.thoughtservice.portal.lms.admin.task.action;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import com.thoughtservice.portal.jobs.calculateleaves.CalculateLeaves;
import com.thoughtservice.portal.lms.admin.task.AdminServices;
import com.thoughtservice.portal.lms.common.date.FormatDate;
import com.thoughtservice.portal.lms.common.details.ListDetails;
import com.thoughtservice.portal.lms.common.notifications.Notification;
import com.thoughtservice.portal.lms.common.notifications.Notification.STATUS;
import com.thoughtservice.portal.login.service.PortalUserDetails;
import com.thoughtservice.portal.user.User;
import com.thoughtservice.portal.user.dao.UserDao;
import com.thoughtservice.portal.user.request.leaverequest.LeaveRequest;
import com.thoughtservice.portal.user.request.leaverequest.dao.LeaveRequestDao;
import com.thoughtservice.portal.user.request.workfromhome.WorkFromHome;
import com.thoughtservice.portal.workfromhome.dao.WorkFromHomeDao;
@Transactional
public class MarkLeave extends DispatchAction {
	private UserDao userDao;
	private LeaveRequestDao leaveRequestDao;
	private WorkFromHomeDao workFromHomeDao;
	private ListDetails listDetails;
	private CalculateLeaves calculateLeaves;
	private AdminServices adminServices;
	private Date date= new Date();
	
	public ActionForward markUserLeave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
		User user= userDao.findByEmployeeCode(Long.parseLong(request.getParameter("employeecode")));
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		String leaveDate = request.getParameter("leaveDate");
		FormatDate formatDate = new FormatDate();
		List<Date> dateList = formatDate.dates(leaveDate);
		PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		try{
		if(request.getParameter("HolidayType").equalsIgnoreCase("leave"))
				{
			
			LeaveRequest leaveRequest= new LeaveRequest();
			leaveRequest.setUser(user);
			try{
			if(request.getParameter("RadioGroup1").equalsIgnoreCase("fullday"))
			{
				leaveRequest.setHdFd("full Day");
				
			}
			else if(request.getParameter("RadioGroup1").equalsIgnoreCase("halfday"))
			{
				try{
					if(request.getParameter("RadioGroup2").equalsIgnoreCase("forenoon"))
						leaveRequest.setHdFd("forenoon");
					else
						leaveRequest.setHdFd("afternoon");
				}catch(Exception e)
				{
					Notification statusNotification = new Notification("please select type", "999", STATUS.ERROR);
					request.setAttribute("notification", statusNotification);
					listDetails.listDetailsToMarkLeave(mapping, form, request, response);
					return mapping.findForward("success");
				}
			}
			}catch(Exception e)
			{
				Notification statusNotification = new Notification("please select type", "999", STATUS.ERROR);
				request.setAttribute("notification", statusNotification);
				listDetails.listDetailsToMarkLeave(mapping, form, request, response);
				return mapping.findForward("success");
			}
			
			leaveRequest.setRequestDate(date);
			leaveRequest.getUser().setId(user.getId());
			leaveRequest.setYear(year);
			leaveRequest.setDescription(request.getParameter("description"));
			User approver = userDao.findById(Long.parseLong(sessionUser.getUserId()));
			leaveRequest.setApprover(approver);
			leaveRequest.setStatus("approved");
			leaveRequest.setMarkedBy(sessionUser.getUsername());
			Date increment = DateUtils.addDays(dateList.get(1), -1);
			dateList.set(1, increment);
			leaveRequest.setDays(formatDate.calculateDays(dateList, "leaveRequest"));
			leaveRequest.setStartDate(dateList.get(0));
			
			leaveRequest.setEndDate(increment);
			leaveRequest.setLastUpdate(new Date());
			leaveRequest.setLmsInternal("Marked by admin");
			leaveRequestDao.addLeaveRequest(leaveRequest);
			adminServices.updateUserHolidays(leaveRequest);
			Notification statusNotification = new Notification("Leave request saved successfully", "000", STATUS.SUCCESS);
			request.setAttribute("notification", statusNotification);
				}
		else if(request.getParameter("HolidayType").equalsIgnoreCase("workfromhome"))
		{
			WorkFromHome workFromHome= new WorkFromHome();
			try{
			if(request.getParameter("RadioGroup1").equalsIgnoreCase("fullday"))
			{
				workFromHome.setHdFd("full Day");
				
			}
			else if(request.getParameter("RadioGroup1").equalsIgnoreCase("halfday"))
			{
				try{
					if(request.getParameter("RadioGroup2").equalsIgnoreCase("forenoon"))
						workFromHome.setHdFd("forenoon");
					else
						workFromHome.setHdFd("afternoon");
				}catch(Exception e)
				{
					Notification statusNotification = new Notification("please select type", "999", STATUS.ERROR);
					request.setAttribute("notification", statusNotification);
					listDetails.listDetailsToMarkLeave(mapping, form, request, response);
					return mapping.findForward("success");
				}
			}
			}catch(Exception e)
			{
				Notification statusNotification = new Notification("please select type", "999", STATUS.ERROR);
				request.setAttribute("notification", statusNotification);	
				listDetails.listDetailsToMarkLeave(mapping, form, request, response);
				return mapping.findForward("success");
			}
			workFromHome.setRequestDate(date);
			workFromHome.setUser(user);
			workFromHome.setYear(Calendar.getInstance().get(Calendar.YEAR));
			workFromHome.setDescription(request.getParameter("description"));
			User approver = userDao.findById(Long.parseLong(sessionUser.getUserId()));
			workFromHome.setApprover(approver);
			workFromHome.setStatus("approved");
			workFromHome.setMarkedBy(sessionUser.getUsername());
			workFromHome.setDays(formatDate.calculateDays(dateList, "workFromHome"));
			workFromHome.setStartDate(dateList.get(0));
			Date increment = DateUtils.addDays(dateList.get(1), -1);
			workFromHome.setEndDate(increment);
			workFromHome.setIsVerified("verified");
				workFromHomeDao.addRequest(workFromHome);
				adminServices.updateUserHolidays(workFromHome);
			Notification statusNotification = new Notification("Workfromhome request saved successfully", "000", STATUS.SUCCESS);
			request.setAttribute("notification", statusNotification);
		}

		}catch(Exception e)
		{
			Notification statusNotification = new Notification("Please select type", "999", STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}
		
		}catch(Exception e)
		{
			Notification statusNotification = new Notification("User not found", "999", STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}
			listDetails.listDetailsToMarkLeave(mapping, form, request, response);
			return mapping.findForward("success");
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setLeaveRequestDao(LeaveRequestDao leaveRequestDao) {
		this.leaveRequestDao = leaveRequestDao;
	}

	

	public void setListDetails(ListDetails listDetails) {
		this.listDetails = listDetails;
	}

	public void setWorkFromHomeDao(WorkFromHomeDao workFromHomeDao) {
		this.workFromHomeDao = workFromHomeDao;
	}


	public void setAdminServices(AdminServices adminServices) {
		this.adminServices = adminServices;
	}



}
