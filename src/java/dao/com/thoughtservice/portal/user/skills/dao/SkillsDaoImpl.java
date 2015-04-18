package com.thoughtservice.portal.user.skills.dao;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.thoughtservice.portal.user.skills.Skill;
import com.thoughtservice.portal.user.skills.UserSkills;

@Transactional
public class SkillsDaoImpl extends HibernateDaoSupport implements SkillsDao {

	public Long addSkills(Skill skills) {
		return (Long) getHibernateTemplate().save(skills);
	}

	@SuppressWarnings("unchecked")
	public List<Skill> getAllSkills() {
		return getHibernateTemplate().find("from Skill");
	}

	public Skill getSkillById(Long id) {
		return getHibernateTemplate().get(Skill.class, id);
	}

	public void deleteSkill(Skill skill) {
		getHibernateTemplate().delete(skill);
	}

	public void updateSkill(Skill skill) {
		getHibernateTemplate().update(skill);
	}

	@SuppressWarnings("unchecked")
	public List<Skill> getSkillByName(String name) {
		String sqlQuery = "from Skill where lower(name) like :searchName";
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(UserSkills.class).setParameter("searchName", name).setCacheable(true);		
		
		return (List<Skill>) query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Skill> getCategories() {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createCriteria(Skill.class);
		criteria.setProjection(Projections.distinct(Projections
				.property("category")));

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Skill> getAllSkillsByCategory() {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createCriteria(Skill.class);
		criteria.addOrder(Order.asc("category"));

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<UserSkills> getAllUserSkills() {
		return getHibernateTemplate().find("from UserSkills");
	}

	public void addUserSkill(UserSkills userSkills) {
		getHibernateTemplate().save(userSkills);
	}

	public void deleteUserSkill(Long id) {
		UserSkills userSkill = getUserSkillById(id);
		getHibernateTemplate().delete(userSkill);

	}

	public void deleteUserSkill(UserSkills userSkill) {
		getHibernateTemplate().delete(userSkill);
	}

	public void updateUserSkill(UserSkills userSkill) {
		getHibernateTemplate().update(userSkill);

	}

	public UserSkills getUserSkillById(Long id) {
		return getHibernateTemplate().get(UserSkills.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<UserSkills> getRecentUserSkills(Long userId) {

		String sqlQuery = "SELECT * FROM skills_user skills WHERE skills.lastUpdate_date IN (SELECT MAX(lastUpdate_date) FROM skills_user WHERE user_id = :userId GROUP BY skill_id) ";
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(UserSkills.class).setParameter("userId", userId).setCacheable(true);
		List<UserSkills> userSkills = query.list();

		if (userSkills != null && userSkills.size() > 0) {
			return userSkills;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<UserSkills> searchRecentSkills(String skills) {
		String sqlQuery = "select s1.* from skills_user s1 inner join(select max(lastupdate_date) LASTUPDATE, USER_ID, SKILL_ID from SKILLS_USER group by user_id, skill_id) s2 on s1.user_id = s2.user_id and s1.skill_id=s2.skill_id and s1.lastupdate_date = s2.lastupdate and s1.skill_id in (:skills) order by s1.lastupdate_date desc";
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(UserSkills.class).setParameterList("skills", Arrays.asList(skills.split(",")));
		List<UserSkills> userSkills = query.list();
		
		return userSkills;
	}
}
