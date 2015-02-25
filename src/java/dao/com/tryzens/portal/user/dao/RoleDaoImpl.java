package com.tryzens.portal.user.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.tryzens.portal.role.Role;

@Transactional
public class RoleDaoImpl extends HibernateDaoSupport implements RoleDao {

	@SuppressWarnings("unchecked")
	public List<Role> findAllRoles() {
		return getHibernateTemplate().find("from Role");
	}

	@SuppressWarnings("unchecked")
	public Role findRoleByName(String name) {

		List<Role> roles = (List<Role>) getHibernateTemplate().find(
				"from Role where name=?", name);
		if (roles.size() > 0)
			return roles.get(0);
		else
			return null;
	}

	public Role findRoleById(long id) {
		return getHibernateTemplate().get(Role.class, id);
	}

	public Role findUserRoleByUserId(Long userId) {
		String sqlQuery = "SELECT * FROM role r WHERE r.ROLE_ID IN (SELECT role_id FROM user_roles WHERE user_id = :userId) ";
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(Role.class).setParameter("userId", userId)
				.setCacheable(true);
		@SuppressWarnings("unchecked")
		List<Role> role = query.list();
		if (role.size() > 0)
		{
			return role.get(0);
		}
		else
			return role.get(0);

	}
}
