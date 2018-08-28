package com.eot.domain.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eot.domain.model.SuperAdmin;

@Repository
public class SuperAdminDaoImpl implements SuperAdminDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
    protected Session getSession() {
    	return sessionFactory.getCurrentSession();
    }
    

	@Override
	public void saveOrUpadte(SuperAdmin admin) {
		getSession().saveOrUpdate(admin);
	}

	@Override
	public void deleteAdmin(String userId) {
		Criteria criteria = getSession().createCriteria(SuperAdmin.class);
		List<SuperAdmin> admins = (List<SuperAdmin>)criteria.list();
		for(SuperAdmin admin:admins)
		{
			if(admin.getUserId().equals(userId))
				getSession().delete(admin);
				
		}
		
	}

	@Override
	public SuperAdmin findAdminByUserId(String userId) {
		Criteria criteria = getSession().createCriteria(SuperAdmin.class);
		List<SuperAdmin> admins = (List<SuperAdmin>)criteria.list();
		for(SuperAdmin admin:admins)
		{
			if(admin.getUserId().equals(userId))
			return admin;
		}
		return null;
		
	}

	@Override
	public SuperAdmin getAdmin() {
	
		Criteria criteria = getSession().createCriteria(SuperAdmin.class);
		List<SuperAdmin> admin = (List<SuperAdmin>)criteria.list();
		return (admin.size() == 0 )? null:admin.get(0);
		
	}

}
