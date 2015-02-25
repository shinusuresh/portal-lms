package com.tryzens.portal.user.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import com.tryzens.portal.login.service.PortalUserDetails;
import com.tryzens.portal.user.User;

@Transactional
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	public Long addCustomer(User user) {
		return (Long) getHibernateTemplate().save(user);
	}

	public void updateStatus(User user) {
		// String hql="update User set status=:status where userId=:Id";

		Query updateQuery = getSession().createQuery(
				"update User set status=:status where id=:Id");
		updateQuery.setLong("status", 1);
		updateQuery.setLong("Id", user.getId());
		updateQuery.executeUpdate();

	}

	public void resetPassword(User user) {
		// String hql="update User set status=:status where userId=:Id";

		Query updateQuery = getSession().createQuery(
				"update User set password=:password where id=:id");
		updateQuery.setString("password", user.getPassword());
		updateQuery.setLong("id", user.getId());
		updateQuery.executeUpdate();

	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		return getHibernateTemplate().find("from User");
	}

	@SuppressWarnings("unchecked")
	public User findUserByUserid(long userid) {
		List<User> userList = getHibernateTemplate().find(
				"from User where userId=?", userid);
		if (userList != null) {
			return userList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Finds for users having email matches with argument passed and returns the
	 * corresponding {@link User} object
	 */
	@SuppressWarnings("unchecked")
	public User findByEmail(String email) {
		List<User> userList = getHibernateTemplate().find(
				"from User where email=?", email);
		if (userList != null && userList.size() > 0) {
			return userList.get(0);
		} else {
			return null;
		}
	}

	public User findById(Long id) {
		return getHibernateTemplate().load(User.class, id);
	}

	public void updateUser(User user) {
		getHibernateTemplate().update(user);
	}

	/**
	 * Method will return all {@link User} object based on a role.
	 */
	@SuppressWarnings({"unchecked" })
	public List<User> findAllUserByRoleAndDepartment(String role) {
		PortalUserDetails sessionUser= (PortalUserDetails) SecurityContextHolder
			     .getContext().getAuthentication().getPrincipal();
		
		return (List<User>) getHibernateTemplate().find(
				"select u from User u join u.roles r where r.name=? and u.department=?", role, sessionUser.getDepartment());
	
	}

	@SuppressWarnings({"unchecked" })
	public List<User> findAllUserByJoiningDate(Date joiningDate) {		
		return (List<User>) getHibernateTemplate().find(
				"select u from User u where u.dateOfJoining=?", joiningDate);
	}
	
	@SuppressWarnings({"unchecked" })
	public List<User> findUsersByJoiningDayAndMonth(int day, int month) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.sqlRestriction("DAY(DATE_OF_JOINING) = ?", day, StandardBasicTypes.INTEGER));
		criteria.add(Restrictions.sqlRestriction("MONTH(DATE_OF_JOINING) = ?", month, StandardBasicTypes.INTEGER));
		
		/*ProjectionList projectionList = Projections.projectionList();
		projectionList
				.add(Projections
						.sqlGroupProjection(
								"MONTH(DATE_OF_JOINING) as month, YEAR(DATE_OF_JOINING) as year",
								"MONTH(DATE_OF_JOINING), YEAR(DATE_OF_JOINING)",
								new String[] { "month", "year" },
								new Type[] { StandardBasicTypes.INTEGER }));
		
		criteria.setProjection(projectionList);*/
		return criteria.list();
	}
	
	public User findByEmployeeCode(Long employeeCode) {
		@SuppressWarnings("unchecked")
		List<User> userList = getHibernateTemplate().find(
				"from User where employeeCode=?", employeeCode);
		if (userList != null && userList.size() > 0) {
			return userList.get(0);
		} else {
			return null;
		}
	}
	@SuppressWarnings({"unchecked" })
	public List<User> findAllUserByRole(String role) {
		return (List<User>) getHibernateTemplate().find(
				"select u from User u join u.roles r where r.name=?", role);
	
	}

	


}
