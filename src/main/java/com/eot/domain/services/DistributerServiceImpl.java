package com.eot.domain.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eot.domain.dao.DistributerDao;
import com.eot.domain.model.Distributer;
import com.eot.util.EotException;

@Service
@Transactional
public class DistributerServiceImpl implements DistributerService {

	@Autowired
	DistributerDao distributerDao;

	@Override
	public void saveOrUpdate(Distributer distributer) throws EotException {

		distributer.setCreatedDate(new Date());
		distributer.setUpdateDate(new Date());
		distributerDao.saveOrUpdate(distributer);
	}

	@Override
	public Distributer findDistributerByUserId(String userId) throws EotException {
		return distributerDao.findDistributerByUserId(userId);
	}

}
