package com.tryzens.portal.lms.ajax.admin.task.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tryzens.portal.user.User;
import com.tryzens.portal.user.dao.UserDao;

@Controller
@RequestMapping("/users")
public class UsersAjaxController {

	private static final Log LOGGER = LogFactory
			.getLog(UsersAjaxController.class);
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "getallusers.ajax", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<User> getAllUsers() {
		LOGGER.debug("Get all users");
		List<User> users = new ArrayList<User>();
		users = userDao.findAllUsers();
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Total of "+users.size()+" users returned.");
		}
		return users;
	}
}
