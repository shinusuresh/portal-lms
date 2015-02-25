package com.tryzens.portal.lms.common.resetpassword;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;

import com.tryzens.portal.lms.common.notifications.Notification;
import com.tryzens.portal.lms.common.notifications.Notification.STATUS;
import com.tryzens.portal.login.service.PortalUserDetails;
import com.tryzens.portal.user.User;
import com.tryzens.portal.user.dao.UserDao;

public class ResetPassword extends DispatchAction {
	private UserDao userDao;
	private PasswordEncoder passwordEncoder;

	public ActionForward reset(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = new User();// (User)request.getAttribute("user");
		PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		user = (User) userDao.findById(Long.parseLong(sessionUser
				.getUserId()));
		// Encode the password
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmpassword");
		if(passwordEncoder.isPasswordValid(user.getPassword(), request.getParameter("old_password"), null))
		if (password.equals(confirmPassword)) {
			
			user.setPassword(passwordEncoder.encodePassword(password, null));
			userDao.resetPassword(user);

			Notification statusNotification = new Notification(
					"Password changed successfully", "000", STATUS.SUCCESS);
			request.setAttribute("notification", statusNotification);
		} else {
			// Create the notification object to JSP status display
			Notification statusNotification = new Notification(
					"Passwords does not match. Please re-enter again", "998",
					STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}
		else{
			Notification statusNotification = new Notification(
					"Please provide correct password", "998",
					STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}

		return mapping.findForward("success");

	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

}
