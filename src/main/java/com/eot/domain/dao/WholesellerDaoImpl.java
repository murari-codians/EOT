package com.eot.domain.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eot.domain.model.Wholeseller;

@Repository
public class WholesellerDaoImpl implements WholesellerDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void saveOrUpdate(Wholeseller wholeseller) {
		getSession().save(wholeseller);

	}

	@Override
	public Wholeseller findWholesellerByUserId(String userId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Wholeseller.class);
		List<Wholeseller> wholesellerList = (List<Wholeseller>) criteria.list();
		for (Wholeseller wholeseller : wholesellerList) {
			if (wholeseller.getUserId().equals(userId))
				return wholeseller;
		}
		return null;
	}

}
