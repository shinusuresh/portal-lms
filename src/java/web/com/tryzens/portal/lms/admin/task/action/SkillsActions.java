package com.tryzens.portal.lms.admin.task.action;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.security.core.context.SecurityContextHolder;

import com.tryzens.portal.lms.admin.task.AdminServices;
import com.tryzens.portal.lms.admin.task.exceptions.SkillAlreadyExistsException;
import com.tryzens.portal.lms.common.notifications.Notification;
import com.tryzens.portal.lms.common.notifications.Notification.STATUS;
import com.tryzens.portal.login.service.PortalUserDetails;
import com.tryzens.portal.user.dao.UserDao;
import com.tryzens.portal.user.skills.Skill;
import com.tryzens.portal.user.skills.UserSkills;
import com.tryzens.portal.user.skills.dao.SkillsDao;

/**
 * Action class for Skill matrix operations
 * 
 * @author Shinu
 *
 */
public class SkillsActions extends DispatchAction {

	private static final Log LOGGER = LogFactory.getLog(SkillsActions.class);

	private UserDao userDao;
	private SkillsDao skillsDao;	
	private AdminServices adminServices;

	public ActionForward loadSkills(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// Get all distinct categories and set to request for category select
		// drop down to show
		List<Skill> categorySkills = skillsDao.getCategories();
		request.setAttribute("categories", categorySkills);
		return mapping.findForward("newskills");
	}

	/**
	 * Method will returns all skills and return the saved data for the current
	 * user context
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadUserSkills(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		//User Skills
		List<UserSkills> userSkills = skillsDao.getRecentUserSkills(Long.parseLong(sessionUser.getUserId()));		
		
		// Get all skills and categorize based on categories
		List<Skill> allSkills = skillsDao.getAllSkillsByCategory();
		
		
		MultiMap categoryMap = new MultiValueMap();		
		for (Skill skill : allSkills) {
			String category = skill.getCategory();			
			//Set rating to 0 initially and let it overwrite
			Map<String, String> additionalData = new HashMap<String, String>();
			additionalData.put("rating", "0");
			
			//iterate over userSkills and add rating string to multivalue map
			if(userSkills != null && userSkills.size() > 0){
				for(UserSkills us : userSkills){
					if(skill.getId().equals(us.getSkill().getId())){
						//set rating inside additional fields
						additionalData.put("rating", Long.toString(us.getRating()));
						skill.setAdditionalFields(additionalData);
						break;
					}
				}
			}	
			categoryMap.put(category, skill);
		}		
	

		request.setAttribute("categories", categoryMap);
		return mapping.findForward("userskills");
	}

	public ActionForward saveSkills(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String name = request.getParameter("name");
		String category = request.getParameter("category");
		String description = request.getParameter("description");

		// Create a new skill
		Skill s = new Skill();
		s.setName(name);
		s.setCategory(category);
		s.setDescription(description);
		s.setLastUpdate(new Date());

		try {
			String id = adminServices.createSkill(s);
			LOGGER.debug("New skill saved with id " + id);

			Notification statusNotification = new Notification("Skill "
					+ s.getName() + " saved successfully.", "000",
					STATUS.SUCCESS);
			request.setAttribute("notification", statusNotification);
		} catch (SkillAlreadyExistsException e) {
			LOGGER.error("Error Occured ", e);
			Notification statusNotification = new Notification("Skill "
					+ s.getName() + " is existing.", "999", STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		} catch (Exception e1) {
			LOGGER.error("Error Occured ", e1);
			Notification statusNotification = new Notification(
					"An error occured while saving skill. Please try again later. If the issue persist, please contact administrator.",
					"999", STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}

		return listSkills(mapping, form, request, response);
	}

	/**
	 * Delete a skill
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteSkill(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		// Load skill object based on id
		try {
			Skill skill = skillsDao.getSkillById(Long.parseLong(id));
			skillsDao.deleteSkill(skill);
			Notification statusNotification = new Notification("Skill "
					+ skill.getName() + " deleted successfully.", "000",
					STATUS.SUCCESS);
			request.setAttribute("notification", statusNotification);
		} catch (Exception e1) {
			LOGGER.error("Error Occured ", e1);
			Notification statusNotification = new Notification(
					"An error occured while deleting skill. Please try again later. If the issue persist, please contact administrator.",
					"999", STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}

		return listSkills(mapping, form, request, response);
	}

	/**
	 * List all skills
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listSkills(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Skill> skills = skillsDao.getAllSkills();
		// Sort the skills by lastupdate
		Collections.sort(skills, new Comparator<Skill>() {

			public int compare(Skill o1, Skill o2) {
				return o2.getLastUpdate().compareTo(o1.getLastUpdate());
			}

		});
		request.setAttribute("skills", skills);
		return mapping.findForward("listskills");
	}

	/**
	 * This method will be invoked up on clicking on skill link from list page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateSkillLoad(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String id = request.getParameter("id");
			// Load skill from DB based on id
			Skill skillToUpdate = skillsDao.getSkillById(Long.parseLong(id));

			request.setAttribute("skill", skillToUpdate);
			request.setAttribute("update", true);
		} catch (Exception e1) {
			LOGGER.error("Error Occured ", e1);
			Notification statusNotification = new Notification(
					"An error occured while loading skill. Please try again later. If the issue persist, please contact administrator.",
					"999", STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}

		return loadSkills(mapping, form, request, response);
	}

	/**
	 * Update skillset
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateSkill(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String category = request.getParameter("category");
			String description = request.getParameter("description");

			// Load skill from DB based on id
			Skill skillToUpdate = skillsDao.getSkillById(Long.parseLong(id));

			// Set updated skills to the object
			skillToUpdate.setName(name);
			skillToUpdate.setCategory(category);
			skillToUpdate.setDescription(description);

			skillsDao.updateSkill(skillToUpdate);

			Notification statusNotification = new Notification("Skill "
					+ skillToUpdate.getName() + " updated successfully.",
					"000", STATUS.SUCCESS);
			request.setAttribute("notification", statusNotification);
		} catch (Exception e1) {
			LOGGER.error("Error Occured ", e1);
			Notification statusNotification = new Notification(
					"An error occured while updating skill. Please try again later. If the issue persist, please contact administrator.",
					"999", STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}

		return listSkills(mapping, form, request, response);
	}
	
	/**
	 * This will be invoked when user try to add a skill
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveUserSkill(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			Long skillId = Long.parseLong(request.getParameter("skillId"));
			PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			
			UserSkills userSkills = new UserSkills();
			userSkills.setUser(userDao.findById(Long.parseLong(sessionUser.getUserId())));
			userSkills.setSkill(skillsDao.getSkillById(skillId));
			
			skillsDao.addUserSkill(userSkills);
			LOGGER.debug("New skill added to "+sessionUser.getUsername()+" with id ");
		} catch (Exception e1) {
			LOGGER.error("Error Occured ", e1);
			Notification statusNotification = new Notification(
					"An error occured while saving the skill. Please try again later. If the issue persist, please contact administrator.",
					"999", STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}
		return listSkills(mapping, form, request, response);
	}
	
	public ActionForward searchSkills(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/*try {
			Long skillId = Long.parseLong(request.getParameter("skillId"));
			PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			
			UserSkills userSkills = new UserSkills();
			userSkills.setUser(userDao.findById(Long.parseLong(sessionUser.getUserId())));
			userSkills.setSkill(skillsDao.getSkillById(skillId));
			
			skillsDao.addUserSkill(userSkills);
			LOGGER.debug("New skill added to "+sessionUser.getUsername()+" with id ");
		} catch (Exception e1) {
			LOGGER.error("Error Occured ", e1);
			Notification statusNotification = new Notification(
					"An error occured while saving the skill. Please try again later. If the issue persist, please contact administrator.",
					"999", STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}
		return listSkills(mapping, form, request, response);*/
		return mapping.findForward("searchskills");
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setAdminServices(AdminServices adminServices) {
		this.adminServices = adminServices;
	}

	public void setSkillsDao(SkillsDao skillsDao) {
		this.skillsDao = skillsDao;
	}
}
