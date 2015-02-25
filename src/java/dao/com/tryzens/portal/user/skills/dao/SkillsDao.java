package com.tryzens.portal.user.skills.dao;

import java.util.List;

import com.tryzens.portal.user.skills.Skill;
import com.tryzens.portal.user.skills.UserSkills;

public interface SkillsDao {

	Long addSkills(Skill skills);

	List<Skill> getAllSkills();

	Skill getSkillById(Long id);

	void deleteSkill(Skill skill);

	void updateSkill(Skill skill);
	
	List<Skill> getSkillByName(String name);
	
	List<Skill> getCategories();
	
	List<Skill> getAllSkillsByCategory();
	
	//User skills
	
	UserSkills getUserSkillById(Long id);
	
	List<UserSkills> getAllUserSkills();
	
	void addUserSkill(UserSkills userSkills);
	
	void deleteUserSkill(Long id);
	
	void deleteUserSkill(UserSkills userSkills);
	
	void updateUserSkill(UserSkills userSkills);
	
	List<UserSkills> getRecentUserSkills(Long userId);
	
	List<UserSkills> searchRecentSkills(String skills);
	
}
