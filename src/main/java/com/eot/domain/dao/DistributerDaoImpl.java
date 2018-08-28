package com.eot.domain.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eot.domain.model.Distributer;

@Repository
public class DistributerDaoImpl  implements DistributerDao{

	@Autowired
	private SessionFactory sessionFactory;


    protected Session getSession() {
    	return sessionFactory.getCurrentSession();
    }
    
	
	@Override
	public void saveOrUpdate(Distributer distributer) {
		getSession().save(distributer);
	}

}
