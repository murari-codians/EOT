package com.eot.domain.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eot.core.EOTConstant;
import com.eot.domain.dao.DistributerDao;
import com.eot.domain.dao.LoginDao;
import com.eot.domain.model.Distributer;
import com.eot.domain.model.Login;
import com.eot.util.EotException;

@Service
@Transactional
public class DistributerServiceImpl implements DistributerService {

	@Autowired
	DistributerDao distributerDao;

	@Autowired
	LoginDao loginDao;

	@Override
	public void saveOrUpdate(Distributer distributer) throws EotException {

		Distributer distributerDetails = distributerDao.findDistributerByUserId(distributer.getUserId());
		if (distributerDetails != null) {
			throw new EotException(EOTConstant.DISTRIBUTER_ALREADY_EXISTS);
		} else {
			Login login = new Login();
			login.setUserId(distributer.getUserId());
			login.setPassword(distributer.getPassword());
			login.setUserType(distributer.getUserType());
			loginDao.saveLogin(login);

			distributer.setCreatedDate(new Date());
			distributer.setUpdateDate(new Date());
			distributerDao.saveOrUpdate(distributer);
		}

	}

	@Override
	public Distributer findDistributerByUserId(String userId) throws EotException {
		return distributerDao.findDistributerByUserId(userId);
	}

}
