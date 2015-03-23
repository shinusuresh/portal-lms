package com.thoughtservice.portal.jobs.email.test;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.thoughtservice.portal.jobs.email.AnniversaryEmailScheduler;
import com.thoughtservice.portal.user.User;
import com.thoughtservice.portal.user.dao.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"/spring/xml/applicationContext.xml"	
})
@Transactional
public class TestsAnniversaryEmailScheduler {

	@Autowired AnniversaryEmailScheduler anniversaryEmailScheduler;
	@Autowired UserDao userDao;
	
	@Before
	public void createUser(){
		User user = new User();
		user.setEmployeeCode(new Long(121));
		user.setEmail("a@atest.com");
		user.setFirstName("ABC");
		user.setPan("BGHFHJFJ");
		user.setPassword("test");
		user.setDateOfBirth(LocalDate.now().minusYears(30).toDate());
		user.setDateOfJoining(LocalDate.now().minusYears(5).toDate());
		user.setCreatedDate(LocalDate.now().toDate());
		
		Long id = userDao.addCustomer(user);
		Assert.assertNotNull(id);
	}
	
	@Test
	public void testAnniversaryEmail(){
		anniversaryEmailScheduler.trigger();
	}
}
