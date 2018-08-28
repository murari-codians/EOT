package com.eot.domain.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eot.domain.dao.LoginDao;
import com.eot.domain.dao.MgurushDao;
import com.eot.domain.model.Login;
import com.eot.domain.model.MGurush;
import com.eot.util.EotException;

@Service
@Transactional
public class MgurushServiceImpl  implements MgurushService{

	@Autowired
	 MgurushDao mgurushDao;
	
	@Autowired
	LoginDao loginDao;
	
	
	
	@Override
	public void saveOrUpadte(MGurush mgurush) throws EotException {
		Login login = new Login();
		login.setUserId(mgurush.getUserId());
		login.setPassword(mgurush.getPassword());
		login.setUserType(mgurush.getUserType());
		loginDao.saveLogin(login);
		
		mgurush.setCreatedDate(new Date());
		mgurushDao.saveOrUpdate(mgurush);
	}

	@Override
	public void deleteMgurush(String userId) throws EotException {
		
		MGurush gurush = mgurushDao.findMgurushByUserId(userId);
		if(gurush != null) {
		mgurushDao.deleteMgurush(userId);
		loginDao.deleteLogin(userId);
		}else {
			throw new EotException("Mgurush Does not exits");
		}
	}

	@Override
	public void updateMgurush(MGurush mgurush) throws EotException {
		
		MGurush gurush = mgurushDao.findMgurushByUserId(mgurush.getUserId());
        Login login = loginDao.findLoginByUserId(mgurush.getUserId());
		if(gurush!=null) {
		login.setUserId(mgurush.getUserId());
		login.setPassword(mgurush.getPassword());
		login.setUserType(mgurush.getUserType());
		loginDao.saveLogin(login);
		
		mgurush.setId(gurush.getId());
		mgurush.getTransactionLimit().setId(gurush.getTransactionLimit().getId());
		mgurush.setUpdateDate(new Date());
		mgurushDao.update(mgurush);
	}else {
		throw new EotException("Mgurush Does not exits");
	}
		
	}

	@Override
	public MGurush findMgurushByUserId(String userId)
	{
		return mgurushDao.findMgurushByUserId(userId);
		
	}
	@Override
	public List<MGurush> findAll() {
		
		return mgurushDao.findAll();
	}

	

}
