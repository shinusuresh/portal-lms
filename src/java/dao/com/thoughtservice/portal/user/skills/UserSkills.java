package com.thoughtservice.portal.user.skills;

import com.thoughtservice.portal.PersistentObject;
import com.thoughtservice.portal.user.User;

public class UserSkills extends PersistentObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6479389946091084114L;
	
	private User user;
	private Skill skill;
	private Long rating;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	public Long getRating() {
		return rating;
	}
	public void setRating(Long rating) {
		this.rating = rating;
	}
	
	

}
