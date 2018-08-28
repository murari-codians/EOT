package com.eot.domain.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eot.domain.model.Distributer;

@Repository
public class DistributerDaoImpl implements DistributerDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void saveOrUpdate(Distributer distributer) {
		getSession().save(distributer);
	}

	@Override
	public Distributer findDistributerByUserId(String userId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Distributer.class);
		List<Distributer> distributerList = (List<Distributer>) criteria.list();
		for (Distributer distributer : distributerList) {
			if (distributer.getUserId().equals(userId))
				return distributer;
		}
		return null;
	}

}
