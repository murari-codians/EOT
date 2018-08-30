package com.eot.domain.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eot.domain.dao.DistributerDao;

import com.eot.domain.dao.LoginDao;
import com.eot.domain.dao.WholesellerDao;
import com.eot.domain.model.Distributer;
import com.eot.domain.model.Login;
import com.eot.domain.model.Wholeseller;
import com.eot.util.EotException;

@Service
@Transactional
public class WholesellerServiceImpl implements WholesellerService {

	@Autowired
	WholesellerDao wholesellerDao;

	@Autowired
	LoginDao loginDao;
	
	@Autowired
	DistributerDao distribDao;

	@Override
	public void saveOrUpdate(String userId,Wholeseller wholeseller) throws EotException {
		
		Distributer distributer = distribDao.findDistributerByUserId(userId);
		if (distributer != null) {
			if (distributer.isActive() && distributer.isAccountEnabled()) {
				
				Wholeseller wholesellerDetails = wholesellerDao.findWholesellerByUserId(wholeseller.getUserId());
				if (wholesellerDetails != null) {
					throw new EotException("Wholeseller already exits");
				} else {
					Login login = new Login();
					login.setUserId(wholeseller.getUserId());
					login.setPassword(wholeseller.getPassword());
					login.setUserType(wholeseller.getUserType());
					loginDao.saveLogin(login);


					wholeseller.setDistributerId(distributer.getUserId());
					wholeseller.setCreatedDate(new Date());
					wholeseller.setUpdateDate(new Date());
					wholesellerDao.saveOrUpdate(wholeseller);
				}
			}else {
				throw new EotException("Distributer not  logined");
			}
		}else {
			throw new EotException("Distributer not  exits");
		}
		
		
	}

	@Override
	public Wholeseller findWholesellerByUserId(String userId) throws EotException {
		return wholesellerDao.findWholesellerByUserId(userId);
	}
}
