package com.eot.domain.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eot.domain.model.Login;


@Repository
public class LoginDaoImpl implements LoginDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveLogin(Login login) {

		sessionFactory.getCurrentSession().save(login);

	}

	@Override
	public Login findLoginByUserId(String userId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Login.class);
		List<Login> loginList = (List<Login>) criteria.list();
		for (Login login : loginList) {
			if (login.getUserId().equals(userId))
				return login;
		}
		return null;
	}

	@Override
	public void deleteLogin(String userId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Login.class);
		List<Login> loginList = (List<Login>) criteria.list();
		for (Login login : loginList) {
			if (login.getUserId().equals(userId))
				sessionFactory.getCurrentSession().delete(login);
		}
		
		
	}

}
