package com.tryzens.portal.user.dao;

import java.util.Date;
import java.util.List;
import com.tryzens.portal.user.User;

public interface UserDao {

	Long addCustomer(User user);

	List<User> findAllUsers();

	User findByEmail(String email);

	User findById(Long id);

	void updateUser(User user);

	void resetPassword(User user);

	User findUserByUserid(long userid);

	List<User> findAllUserByRoleAndDepartment(String role);

	/**
	 * Returns a list of users based on the dates joined
	 * 
	 * @param joiningDate
	 * @return
	 */
	List<User> findAllUserByJoiningDate(Date joiningDate);

	List<User> findUsersByJoiningDayAndMonth(int day, int month);

	User findByEmployeeCode(Long employeeCode);

	List<User> findAllUserByRole(String role);

}
