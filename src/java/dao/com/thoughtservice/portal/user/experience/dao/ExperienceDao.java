package com.thoughtservice.portal.user.experience.dao;

import java.util.List;
import java.util.Set;

import com.thoughtservice.portal.user.User;
import com.thoughtservice.portal.user.experience.Experience;

public interface ExperienceDao {
	
 void saveRequest(Set<Experience> experience);
 List<Experience> listExperienceOfUser(Long userId);
 
	
}
