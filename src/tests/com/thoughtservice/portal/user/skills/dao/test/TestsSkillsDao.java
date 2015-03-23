package com.thoughtservice.portal.user.skills.dao.test;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tryzens.portal.test.base.BaseTests;
import com.tryzens.portal.user.User;
import com.tryzens.portal.user.skills.Skill;
import com.tryzens.portal.user.skills.UserSkills;
import com.tryzens.portal.user.skills.dao.SkillsDao;

public class TestsSkillsDao extends BaseTests{

	
	@Autowired SkillsDao skillsDao;
	
	@Before
	public void setupData(){
		
		UserSkills user1 = createUserSkill("1", "3", "5", new DateTime().toDate());
		UserSkills user2 = createUserSkill("2", "3", "3", new DateTime().minusHours(1).toDate());
		UserSkills user3 = createUserSkill("3", "3", "5", new DateTime().plusHours(2).toDate());
		UserSkills user4 = createUserSkill("3", "3", "1", new DateTime().plusHours(3).toDate());
		UserSkills user5 = createUserSkill("1", "3", "1", new DateTime().plusHours(3).toDate());
		
		skillsDao.addUserSkill(user1);
		skillsDao.addUserSkill(user2);
		skillsDao.addUserSkill(user3);
		skillsDao.addUserSkill(user4);
		skillsDao.addUserSkill(user5);
	}
	
	@Test
	public void getRecentUserSkills(){
		List<UserSkills> list = skillsDao.getRecentUserSkills(new Long(3));
		printStats(super.statistics);
		Assert.assertNotNull(list);
		Assert.assertArrayEquals(new int[]{3}, new int[] {list.size()});
		
		List<UserSkills> list1 = skillsDao.getRecentUserSkills(new Long(3));
		printStats(super.statistics);
	}
	
	private UserSkills createUserSkill(String skillId, String userId,
			String rating, Date date) {
		
		UserSkills ur = new UserSkills();

		Skill s = new Skill();
		s.setId(new Long(skillId));
		ur.setSkill(s);

		User u = new User();
		u.setId(new Long(userId));

		ur.setLastUpdate(date);

		ur.setRating(new Long(rating));
		ur.setUser(u);

		return ur;
	}
	
}
