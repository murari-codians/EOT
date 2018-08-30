package com.eot.domain.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eot.domain.model.Entiti;

@Repository
public class EntitiDaoImpl implements EntitiDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveOrUpdate(Entiti entiti) {
		sessionFactory.getCurrentSession().saveOrUpdate(entiti);

	}

	@Override
	public void update(Entiti entiti) {
		sessionFactory.getCurrentSession().merge(entiti);

	}

	@Override
	public void deleteEntiti(String userId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Entiti.class);
		List<Entiti> entitiDetails = (List<Entiti>) criteria.list();
		for (Entiti entiti : entitiDetails) {
			if (entiti.getUserId().equals(userId))
				sessionFactory.getCurrentSession().delete(entiti);
		}

	}

	@Override
	public Entiti findEntitiByUserId(String userId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Entiti.class);
		List<Entiti> entitiDetails = (List<Entiti>) criteria.list();
		for (Entiti entiti : entitiDetails) {
			if (entiti.getUserId().equals(userId))
				return entiti;
		}
		return null;
	}

	@Override
	public List<Entiti> findAll() {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Entiti.class);
		return c.list();
	}

}
