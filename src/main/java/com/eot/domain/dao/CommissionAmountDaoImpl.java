package com.eot.domain.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eot.domain.model.CommissionAmount;

@Repository
public class CommissionAmountDaoImpl implements CommissionAmountDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addCommissionAmount(CommissionAmount commissionAmount) {

		getSession().save(commissionAmount);
	}

}
