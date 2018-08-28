package com.eot.domain.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eot.domain.model.MGurush;
import com.eot.domain.model.SuperAdmin;

@Repository
public class MgurushDaoImpl implements MgurushDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void saveOrUpdate(MGurush mGurush) {
		sessionFactory.getCurrentSession().saveOrUpdate(mGurush);
		
	}

	@Override
	public void update(MGurush mGurush) {
		sessionFactory.getCurrentSession().merge(mGurush);
		
	}
	@Override
	public void deleteMgurush(String userId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MGurush.class);
		List<MGurush> gurushList = (List<MGurush>)criteria.list();
		for(MGurush gurush:gurushList)
		{
			if(gurush.getUserId().equals(userId))
				sessionFactory.getCurrentSession().delete(gurush);
		}
		
	}

	@Override
	public MGurush findMgurushByUserId(String userId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MGurush.class);
		List<MGurush> gurushList = (List<MGurush>) criteria.list();
		for (MGurush gurush : gurushList) {
			if (gurush.getUserId().equals(userId))
				return gurush;
		}
		return null;
	}

	@Override
	public List<MGurush> findAll() {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(MGurush.class);
		return c.list();
	}

	
}
