package com.eot.domain.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eot.domain.model.Commission;

@Repository
public class CommissionDaoImpl implements CommissionDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void saveOrUpadte(Commission commission) {
		getSession().saveOrUpdate(commission);
	}

	@Override
	public void deleteCommission(String userId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Commission.class);
		@SuppressWarnings("unchecked")
		List<Commission> commissions = (List<Commission>) criteria.list();
		for (Commission comm : commissions) {
			if (comm.getUserId().equals(userId))
				sessionFactory.getCurrentSession().delete(comm);
		}

	}

	@Override
	public Commission findCommissionByUserId(String userId) {
		Criteria criteria = getSession().createCriteria(Commission.class);
		@SuppressWarnings("unchecked")
		List<Commission> commissions = (List<Commission>) criteria.list();
		for (Commission comm : commissions) {
			if (comm.getUserId().equals(userId))
				return comm;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Commission> getListCommission() {
		Criteria criteria = getSession().createCriteria(Commission.class);
		
			return (List<Commission>) criteria.list();
	}

}
