package com.tryzens.portal.lms.admin.task.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.tryzens.portal.holidays.CalendarHolidays;
import com.tryzens.portal.holidays.dao.CalendarHolidaysDao;
import com.tryzens.portal.user.request.leaverequest.dao.LeaveRequestDao;
import com.tryzens.portal.lms.common.details.ListDetails;
import com.tryzens.portal.lms.common.notifications.Notification;
import com.tryzens.portal.lms.common.notifications.Notification.STATUS;


public class CalendarHolidaysAction extends DispatchAction {

	private CalendarHolidaysDao calendarHolidaysDao;
	private ListDetails listDetails;
	
	/**
	 * Add a holiday
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addHoliday(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CalendarHolidays calendarHolidays = new CalendarHolidays();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal = Calendar.getInstance();
		Long uniqueId= null;
		try{
			
			uniqueId= Long.parseLong(request.getParameter("unique_id"));
		}catch(Exception e){
			uniqueId = null;
		}
		
		try {
			if(uniqueId != null && uniqueId>0)
			{
				try{
				CalendarHolidays currentRequest = calendarHolidaysDao.holidayById(Long.parseLong(request.getParameter("unique_id")));
				currentRequest.setHolidayType(request.getParameter("holiday_type"));
				currentRequest.setDescription(request.getParameter("description"));
				calendarHolidaysDao.updateHoliday(currentRequest);
				listDetails.listHolidays(mapping, form, request, response);
				Notification statusNotification= new Notification("Holiday Updated", "000", STATUS.SUCCESS);
				request.setAttribute("notification", statusNotification);
				}catch(Exception e){
					Notification statusNotification= new Notification("Error updating holiday", "000", STATUS.ERROR);
					request.setAttribute("notification", statusNotification);
				}
				return mapping.findForward("success");
			}
			calendarHolidays.setDate(simpleDateFormat.parse(request
					.getParameter("leaveDate")));
			cal.setTime(calendarHolidays.getDate());
			calendarHolidays.setHolidayType(request
					.getParameter("holiday_type"));
			calendarHolidays
					.setDescription(request.getParameter("description"));
			calendarHolidays.setYear(cal.get(Calendar.YEAR));
			calendarHolidaysDao.addHoliday(calendarHolidays);
			Notification statusNotification= new Notification("Holiday saved to database", "000", STATUS.SUCCESS);
			request.setAttribute("notification", statusNotification);
		} catch (Exception ex) {
			Notification statusNotification= new Notification("Error occured while creating holiday, select single date", "999", STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}

		listDetails.listHolidays(mapping, form, request, response);
		return mapping.findForward("success");
	}
	
	/**
	 * Delete a requested holiday
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteHoliday(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CalendarHolidays calendarHolidays= new CalendarHolidays();
		try{
			/*if(request.getParameter("from").equalsIgnoreCase("calendar")){*/
			//Load holiday object by id
			calendarHolidays = calendarHolidaysDao.holidayById(Long.parseLong(request.getParameter("id")));			
			calendarHolidaysDao.deleteHoliday(calendarHolidays);
			Notification statusNotification = new Notification(
					"Deleted Successfully!", "000", STATUS.SUCCESS);
			request.setAttribute("notification", statusNotification);
			listHolidays(mapping, form, request, response);			
			/*}*/
		}catch(Exception e)
		{
			Notification statusNotification = new Notification(
					e.toString(), "999", STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}		
		return mapping.findForward("success");

	}
	
	public ActionForward listHolidays(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		listDetails.listHolidays(mapping, form, request, response);
		return mapping.findForward("success");

	}


	public void setListDetails(ListDetails listDetails) {
		this.listDetails = listDetails;
	}
	public void setCalendarHolidaysDao(CalendarHolidaysDao calendarHolidaysDao) {
		this.calendarHolidaysDao = calendarHolidaysDao;
	}

	public CalendarHolidaysDao getCalendarHolidaysDao() {
		return calendarHolidaysDao;
	}

	
}

