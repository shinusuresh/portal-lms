package com.tryzens.portal.investmentdeclaration.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.tryzens.portal.investmentdeclaration.form80.Form80;
import com.tryzens.portal.investmentdeclaration.form80c.Form80c;
import com.tryzens.portal.investmentdeclaration.hra.HomeRentAllowance;
@Transactional
public class InvestmentDeclarationDaoImpl extends HibernateDaoSupport implements
		InvestmentDeclarationDao {

	public void addForm80(Form80 form80) {
		getHibernateTemplate().save(form80);

	}

	public void addForm80c(Form80c form80c) {
		getHibernateTemplate().save(form80c);

	}

	public void addHra(HomeRentAllowance homeRentAllowance) {
		getHibernateTemplate().save(homeRentAllowance);

	}

	public void updateForm80(Form80 form80) {
		getHibernateTemplate().update(form80);

	}

	public void updateForm80c(Form80c form80c) {
		getHibernateTemplate().update(form80c);

	}

	public void updateHra(HomeRentAllowance homeRentAllowance) {
		getHibernateTemplate().update(homeRentAllowance);

	}

	public Form80 findForm80ByUserIdAndYear(Long userId, int year) {
		String sqlQuery = "select * from FORM80 where year=:year and user_id=:user_id";

		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(Form80.class).setParameter("user_id", userId)
				.setParameter("year", year);
		List<Form80> form80List = query.list();
		if(form80List.isEmpty())
			return null;
		return form80List.get(0);
	}

	public Form80c findForm80cByUserIdAndYear(Long userId, int year) {
		String sqlQuery = "select * from FORM_80C where year=:year and user_id=:user_id";

		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(Form80c.class).setParameter("user_id", userId)
				.setParameter("year", year);
		List<Form80c> form80cList = query.list();
		if(form80cList.isEmpty())
			return null;
		return form80cList.get(0);
	}

	public HomeRentAllowance findHraByUserIdAndYear(Long userId, int year) {
		String sqlQuery = "select * from HRA where year=:year and user_id=:user_id";
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sqlQuery)
				.addEntity(HomeRentAllowance.class).setParameter("user_id", userId)
				.setParameter("year", year);
		List<HomeRentAllowance> hraList = query.list();
		if(hraList.isEmpty())
			return null;
		return hraList.get(0);
	}

}
