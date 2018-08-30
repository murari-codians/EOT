package com.eot.domain.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eot.domain.dao.DistributerDao;
import com.eot.domain.dao.EntitiDao;
import com.eot.domain.dao.LoginDao;
import com.eot.domain.model.Distributer;
import com.eot.domain.model.Entiti;
import com.eot.domain.model.Login;
import com.eot.util.EotException;

@Service
@Transactional
public class DistributerServiceImpl implements DistributerService {

	@Autowired
	DistributerDao distributerDao;

	@Autowired
	LoginDao loginDao;
	
	@Autowired
	EntitiDao entitiDao;

	@Override
	public void saveOrUpdate(String userId, Distributer distributer) throws EotException {
		
		Entiti entiti = entitiDao.findEntitiByUserId(userId);
		
		if (entiti != null) {
			if (entiti.isActive() && entiti.isAccountEnabled()) {
				
				Distributer distributerDetails = distributerDao.findDistributerByUserId(distributer.getUserId());
				if (distributerDetails != null) {
					throw new EotException("Distributer already exits");
				} else {
					
					Login login = new Login();
					login.setUserId(distributer.getUserId());
					login.setPassword(distributer.getPassword());
					login.setUserType(distributer.getUserType());
					loginDao.saveLogin(login);
					
					distributer.setEntitiId(userId);
					distributer.setCreatedDate(new Date());
					distributer.setUpdateDate(new Date());
					distributerDao.saveOrUpdate(distributer);
				}
				
			}else {
				throw new EotException("Entity  is not yet login");
			}
		}else {
			throw new EotException("Entity Does not exits");

		}
		
	

	}

	@Override
	public Distributer findDistributerByUserId(String userId) throws EotException {
		return distributerDao.findDistributerByUserId(userId);
	}

}
