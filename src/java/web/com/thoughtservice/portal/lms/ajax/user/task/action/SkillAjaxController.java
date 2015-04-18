package com.thoughtservice.portal.lms.ajax.user.task.action;

import java.util.ArrayList;
import java.util.Date;
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
import com.thoughtservice.portal.user.User;
import com.thoughtservice.portal.user.dao.UserDao;
import com.thoughtservice.portal.user.request.bankholidayworking.dao.BankHolidayWorkingDao;
import com.thoughtservice.portal.user.request.leaverequest.dao.LeaveRequestDao;
import com.thoughtservice.portal.user.request.weekendworking.dao.WeekendWorkingDao;
import com.thoughtservice.portal.user.skills.Skill;
import com.thoughtservice.portal.user.skills.UserSkills;
import com.thoughtservice.portal.user.skills.dao.SkillsDao;
import com.thoughtservice.portal.workfromhome.dao.WorkFromHomeDao;

/**
 * This is specifically for handling ajax calls of skills update
 * 
 * @author Shinu
 *
 */
@Controller
@RequestMapping("/skills")
public class SkillAjaxController {

	private static final Log LOGGER = LogFactory
			.getLog(SkillAjaxController.class);

	@Autowired
	private SkillsDao skillsDao;

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
	
	@RequestMapping(value = "addskill.ajax", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserSkills addSkill(@RequestParam String skillId,
			@RequestParam String rating) {
		// Get user id
		PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		LOGGER.debug("Updating skill for user with id "
				+ sessionUser.getUserId());

		UserSkills userSkills = new UserSkills();

		User u = new User();
		u.setId(Long.parseLong(sessionUser.getUserId()));
		userSkills.setUser(u);

		Skill skill = new Skill();
		skill.setId(Long.parseLong(skillId));

		userSkills.setSkill(skill);

		userSkills.setRating(Long.parseLong(rating));

		userSkills.setLastUpdate(new Date());

		skillsDao.addUserSkill(userSkills);

		return userSkills;
	}

	@RequestMapping(value = "searchskill.ajax", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Skill> addSkill(@RequestParam String search) {
		List<Skill> skills = new ArrayList<Skill>();
		skills = skillsDao.getSkillByName(search);
		return skills;
	}

	@RequestMapping(value = "getallskills.ajax", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Skill> getAllSkills() {
		List<Skill> skills = new ArrayList<Skill>();
		skills = skillsDao.getAllSkills();
		return skills;
	}

	@Transactional
	@RequestMapping(value = "searchuserbyskills.ajax", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<UserSkills> searchBySkills(
			@RequestParam String search) {
		LOGGER.debug("Searching for skills " + search);
		List<UserSkills> skills = new ArrayList<UserSkills>();
		skills = skillsDao.searchRecentSkills(search);
		return skills;
	}
	
	@Transactional
	@RequestMapping(value = "getskillsforuser.ajax", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<UserSkills> skillsForUser(
			@RequestParam Long id) {
		LOGGER.debug("Getting all skills for user " + id);
		List<UserSkills> skills = new ArrayList<UserSkills>();
		skills = skillsDao.getRecentUserSkills(id);
		return skills;
	}
	
}
