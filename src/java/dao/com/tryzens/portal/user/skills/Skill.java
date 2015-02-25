package com.tryzens.portal.user.skills;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.tryzens.portal.PersistentObject;

public class Skill extends PersistentObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4800857689597902730L;
	
	private String name;
	private String category;
	private String description;
	private transient Map<String, String> additionalFields;
	
	private Set<UserSkills> userSkills = new HashSet<UserSkills>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Set<UserSkills> getUserSkills() {
		return userSkills;
	}
	public void setUserSkills(Set<UserSkills> userSkills) {
		this.userSkills = userSkills;
	}
	public Map<String, String> getAdditionalFields() {
		return additionalFields;
	}
	public void setAdditionalFields(Map<String, String> additionalFields) {
		this.additionalFields = additionalFields;
	}
	
	

}
