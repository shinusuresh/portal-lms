package com.thoughtservice.portal.user.dao.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtservice.portal.test.base.BaseTests;
import com.thoughtservice.portal.user.User;
import com.thoughtservice.portal.user.dao.UserDao;


public class TestsUserDao extends BaseTests{

	private UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Before
	public void setUp(){
		
	}
	
	@Test
	public void testfindUsersByJoiningMonthAndYear(){
		List<User> users = userDao.findUsersByJoiningDayAndMonth(1, 1);
		Assert.assertNotNull(users);
		Assert.assertArrayEquals(new int[]{3}, new int[]{users.size()});
	}
}
