package com.thoughtservice.portal.lms.admin.task.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.thoughtservice.portal.lms.admin.task.AdminServices;
import com.thoughtservice.portal.lms.admin.task.exceptions.UserAlreadyExists;
import com.thoughtservice.portal.lms.common.notifications.Notification;
import com.thoughtservice.portal.lms.common.notifications.Notification.STATUS;
import com.thoughtservice.portal.lms.user.task.UserServices;
import com.thoughtservice.portal.role.Role;
import com.thoughtservice.portal.user.User;
import com.thoughtservice.portal.user.dao.RoleDao;
import com.thoughtservice.portal.user.dao.UserDao;
import com.thoughtservice.portal.user.experience.Experience;

public class UserActions extends DispatchAction {

	private static final Log LOGGER = LogFactory.getLog(UserActions.class);

	private UserDao userDao;
	private RoleDao roleDao;
	private AdminServices adminServices;
	private UserServices userServices;

	/**
	 * Method will be called at the time of page load
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward load(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// Load roles adnd set in request object
		request.setAttribute("roles", roleDao.findAllRoles());

		return mapping.findForward("success");
	}

	
	/**
	 * Method will be called while saving a user
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{

		User user = new User();
		user.setEmployeeCode(Long.parseLong(request
				.getParameter("employeeCode")));

		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setPan(request.getParameter("pan"));
		user.setEmail(request.getParameter("email"));
		user.setAddress(request.getParameter("address"));
		user.setCreatedDate(Calendar.getInstance().getTime());
		user.setGender(request.getParameter("gender"));
		user.setMobile(request.getParameter("mobile"));
		user.setDepartment(request.getParameter("department"));
		user.setAnnualLeaves(Integer.parseInt(request.getParameter("anualLeaves")));
		
			try {
				LOGGER.debug("Date of birth is "
						+ request.getParameter("dateOfBirth"));
				Date date = DateUtils.parseDate(
						request.getParameter("dateOfBirth"),
						new String[] { "dd-MM-yyyy" });
				user.setDateOfBirth(date);
				
				LOGGER.debug("Date of joining is "
						+ request.getParameter("dateOfJoining"));
				
				Date parsedJoiningDate = DateUtils.parseDate(
						request.getParameter("dateOfJoining"),
						new String[] { "dd-MM-yyyy" });

				LOGGER.debug("Parsed Joining date is " + parsedJoiningDate);
				user.setDateOfJoining(parsedJoiningDate);

			} catch (ParseException ex) {
				LOGGER.error("Exception " + ex);
			}
		// Create a Role object
		Role role = new Role();
		role.setId(Long.parseLong(request.getParameter("roleId")));

		try {
			
			//Save experience
			Set<Experience> experiences = new HashSet<Experience>();				
			
			//See if fresher or lateral
			String experienceStatus = request.getParameter("experienceStatus");
			if("lateral".equals(experienceStatus)) {
				LOGGER.debug("Lateral input. Loop through experiences");
				//populating experience table
				
				String[] companyNames = request.getParameterValues("companyName[]");
				String[] designations = request.getParameterValues("designation[]");
				String[] startDate = request.getParameterValues("startDate[]");
				String[] endDate = request.getParameterValues("endDate[]");
				//Take the length, thats the # of companies employee worked for
				int i = 0;
				for(String designation : designations){					
					LOGGER.debug("Dsesignation is "+designation);
					String strStartDate = startDate[i];
					String endEndDate=endDate[i];
						Experience consExperience = createUserExperience(
								companyNames[i], designation,
								DateUtils.parseDate(strStartDate, new String[] { "dd-MM-yyyy" }),
								DateUtils.parseDate(endEndDate, new String[] { "dd-MM-yyyy" }));
					experiences.add(consExperience);
					i++;
				}
								
			}
			
			Long id = adminServices.createUser(user, role, experiences);		

			// Create the notification object to JSP status displaye
			Notification statusNotification = new Notification("User " + id
					+ " create successfully.", "000", STATUS.SUCCESS);
			request.setAttribute("notification", statusNotification);

		} catch (UserAlreadyExists ue) {
			LOGGER.error("Exception " + ue);
			// User is already registered. Return the Notification object with
			// error.
			Notification statusNotification = new Notification("User "
					+ user.getEmail() + " is existing.", "999", STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}
		}
		catch(Exception e)
		{
			LOGGER.error("Exception Occured ", e);
			Notification statusNotification = new Notification("Error creating user!"+e, "999", STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}
		finally {
			// Invoke load for loading roles
			load(mapping, form, request, response);

		}

		return mapping.findForward("success");
	}

	

	/**
	 * Method will be called for Employee List page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	// Method will be called for list out employees
	public ActionForward listUsers(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<User> users = userDao.findAllUsers();
		request.setAttribute("users", users);

		// Get users by a role
		List<User> usersByRole = userDao.findAllUserByRoleAndDepartment("ROLE_MANAGER");
		if (usersByRole != null) {
			for (User user : usersByRole) {
				LOGGER.info("User is " + user.getFirstName() + " and id is "
						+ user.getId());
			}
		}

		return mapping.findForward("success");
	}

	private Experience createUserExperience(String companyName, String designation, Date startDate, Date endDate) {
		LOGGER.debug("Entering createUserExperience");
		// Create experience object
		Experience experience = new Experience();
		experience.setCompanyName(companyName);		
		experience.setDesignation(designation);
		experience.setStartDate(startDate);
		experience.setEndDate(endDate);
		
		return experience;
	}
	//Populate user details to edit
	public ActionForward listUsersDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user= userDao.findById(Long.parseLong(request.getParameter("userId")));
		request.setAttribute("user", user);
		//try {
			
			LOGGER.debug("Date of birth is "
					+ request.getParameter("dateOfBirth"));
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dateOfBirth= dateFormat.format(user.getDateOfBirth());
			request.setAttribute("dob", dateOfBirth);
			//Date date = DateUtils.parseDate("dateOfBirth", new String[] { "yyyy-MM-dd" });
			//user.setDateOfBirth(date);
		/*} catch (ParseException ex) {
			LOGGER.error("Exception " + ex);
		}*/
		return mapping.findForward("success");
	}
	
	//Populate user details to edit
	public ActionForward editUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			Long userId = Long.parseLong(request.getParameter("userId"));
			User user = userDao.findById(userId);
			user.setFirstName(request.getParameter("firstName"));
			user.setLastName(request.getParameter("lastName"));
			try {
				LOGGER.debug("Date of birth is "
						+ request.getParameter("dateOfBirth"));
				Date date = DateUtils.parseDate(
						request.getParameter("dateOfBirth"),
						new String[] { "yyyy-MM-dd" });
				user.setDateOfBirth(date);
			} catch (ParseException ex) {
				LOGGER.error("Exception " + ex);
			}
			user.setAddress(request.getParameter("address"));
			user.setMobile(request.getParameter("mobile"));
			user.setEmployeeCode(Long.parseLong(request.getParameter("empId")));
			user.setEmail(request.getParameter("email"));
			user.setDepartment(request.getParameter("department"));
			userDao.updateUser(user);
			Notification statusNotification = new Notification(
					"Updated successfully!", "000", STATUS.SUCCESS);
			request.setAttribute("notification", statusNotification);
		} catch (Exception e) {
			LOGGER.error("Error Occured ", e);
			Notification statusNotification = new Notification(
					"Error! please try again", "999", STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}
		return listUsersDetails(mapping, form, request, response);
	}
	
	public ActionForward deactivate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try
		{
		Long userId = Long.parseLong(request.getParameter("userId"));
		User user = userDao.findById(userId);
		if(user.getStatus()==0)
		{
			Notification statusNotification = new Notification(
					"User already deactivated", "999", STATUS.WARNING);
			request.setAttribute("notification", statusNotification);
			return listUsersDetails(mapping, form, request, response);
		}
		user.setStatus(0);
		userServices.updateUser(user);
		}catch(Exception e)
		{
			LOGGER.error("Error Occured ", e);
			Notification statusNotification = new Notification(
					"Error! please try again", "999", STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
			return listUsersDetails(mapping, form, request, response);
		}
		Notification statusNotification = new Notification("User deactivated!", "000", STATUS.SUCCESS);
		request.setAttribute("notification", statusNotification);
		return listUsersDetails(mapping, form, request, response);
	}


	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	public AdminServices getAdminServices() {
		return adminServices;
	}

	public void setAdminServices(AdminServices adminServices) {
		this.adminServices = adminServices;
	}


	public void setUserServices(UserServices userServices) {
		this.userServices = userServices;
	}

}
