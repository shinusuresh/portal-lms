package com.thoughtservice.portal.user.experience.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.thoughtservice.portal.user.User;
import com.thoughtservice.portal.user.experience.Experience;
import com.thoughtservice.portal.user.request.leaverequest.LeaveRequest;
@Transactional
public class ExperienceDaoImpl extends HibernateDaoSupport implements ExperienceDao{
	
//save experience to table
	public void saveRequest(Set<Experience> experiences) {
		
	//	getHibernateTemplate().save(experiences);
		getHibernateTemplate().saveOrUpdateAll(experiences);
	}

	public List<Experience> listExperienceOfUser(Long userId) {
		String sqlQuery = "select * from Experience where user_id=:user_id";
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(Experience.class).setParameter("user_id", userId);
		List<Experience> experience = query.list();
		return experience;
	}

}
