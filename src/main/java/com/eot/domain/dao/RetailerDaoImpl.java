package com.eot.domain.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eot.domain.model.Retailer;

@Repository
public class RetailerDaoImpl implements RetailerDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void saveOrUpdate(Retailer retailer) {
		getSession().save(retailer);
	}

	@Override
	public Retailer findRetailerByUserId(String userId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Retailer.class);
		List<Retailer> retailerList = (List<Retailer>) criteria.list();
		for (Retailer retailer : retailerList) {
			if (retailer.getUserId().equals(userId))
				return retailer;
		}
		return null;
	}

}
