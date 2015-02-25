package com.tryzens.portal.lms.admin.task.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.tryzens.portal.holidays.CalendarHolidays;
import com.tryzens.portal.holidays.dao.CalendarHolidaysDao;
import com.tryzens.portal.lms.common.details.ListDetails;
import com.tryzens.portal.lms.common.notifications.Notification;
import com.tryzens.portal.lms.common.notifications.Notification.STATUS;

public class DeleteCalendarHolidays extends DispatchAction {
	private ListDetails listDetails;
	private CalendarHolidaysDao calendarHolidaysDao;

	public ActionForward listHolidays(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		listDetails.listHolidays(mapping, form, request, response);
		return mapping.findForward("success");

	}
	public ActionForward deleteHoliday(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CalendarHolidays calendarHolidays= new CalendarHolidays();
		try{
			if(request.getParameter("from").equalsIgnoreCase("calendar")){
			calendarHolidays.setId(Long.parseLong(request.getParameter("id")));
			calendarHolidaysDao.deleteHoliday(calendarHolidays);
			Notification statusNotification = new Notification(
					"Deleted Successfully!", "000", STATUS.SUCCESS);
			request.setAttribute("notification", statusNotification);
			listHolidays(mapping, form, request, response);
			return mapping.findForward("calendar");
			}
		}catch(Exception e)
		{
			Notification statusNotification = new Notification(
					e.toString(), "999", STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}
		listHolidays(mapping, form, request, response);
		return mapping.findForward("success");

	}


	public void setListDetails(ListDetails listDetails) {
		this.listDetails = listDetails;
	}

	public void setCalendarHolidaysDao(CalendarHolidaysDao calendarHolidaysDao) {
		this.calendarHolidaysDao = calendarHolidaysDao;
	}

}
