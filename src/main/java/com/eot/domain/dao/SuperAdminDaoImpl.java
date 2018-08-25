package com.eot.domain.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eot.domain.model.SuperAdmin;

/**
 * @author murari
 *
 */
@Repository
public class SuperAdminDaoImpl implements SuperAdminDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<SuperAdmin> getListSuperAdmin() {
		Criteria criteria = getSession().createCriteria(SuperAdmin.class);
		if (criteria.list().size() == 0) {
			return null;
		} else {
			return (List<SuperAdmin>) criteria.list();
		}
	}

	public void saveOrUpdate(SuperAdmin superAdmin) {

		getSession().saveOrUpdate(superAdmin);
	}

	public void deleteSuperAdmin(int id) {

		SuperAdmin superAdmin = (SuperAdmin) getSession().get(SuperAdmin.class, id);
		getSession().delete(superAdmin);
	}

	public SuperAdmin findSuperAdminById(int id) {
		return (SuperAdmin) getSession().get(SuperAdmin.class, id);
	}
	
	@Override
	public SuperAdmin findAdminByUserId(Long userId) {
		Criteria criteria = getSession().createCriteria(SuperAdmin.class);
		@SuppressWarnings("unchecked")
		List<SuperAdmin> admins = (List<SuperAdmin>)criteria.list();
		for(SuperAdmin admin:admins)
		{
			if(admin.getUserId().equals(userId))
			return admin;
		}
		return null;
		
	}

}
