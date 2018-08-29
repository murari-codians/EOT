package com.eot.domain.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eot.domain.dao.LoginDao;
import com.eot.domain.dao.EntitiDao;
import com.eot.domain.model.Entiti;
import com.eot.domain.model.Login;
import com.eot.util.EotException;

@Service
@Transactional
public class EntitiServiceImpl  implements EntitiService{

	@Autowired
	 EntitiDao mgurushDao;
	
	@Autowired
	LoginDao loginDao;
	
	
	
	@Override
	public void saveOrUpadte(Entiti entiti) throws EotException {
		Entiti entitiDetails = mgurushDao.findMgurushByUserId(entiti.getUserId());
		if(entitiDetails!=null) {
			throw new EotException("Mgurush already exits");
		}else {
		Login login = new Login();
		login.setUserId(entiti.getUserId());
		login.setPassword(entiti.getPassword());
		login.setUserType(entiti.getUserType());
		loginDao.saveLogin(login);
		
		entiti.setCreatedDate(new Date());
		entiti.setUpdateDate(new Date());
		mgurushDao.saveOrUpdate(entiti);
		}
	}

	@Override
	public void deleteMgurush(String userId) throws EotException {
		
		Entiti entiti = mgurushDao.findMgurushByUserId(userId);
		if(entiti != null) {
		mgurushDao.deleteMgurush(userId);
		loginDao.deleteLogin(userId);
		}else {
			throw new EotException("Mgurush Does not exits");
		}
	}

	@Override
	public void updateMgurush(Entiti entiti) throws EotException {
		
		Entiti entitiDetails = mgurushDao.findMgurushByUserId(entiti.getUserId());
		if(entitiDetails!=null) {
        Login login = loginDao.findLoginByUserId(entitiDetails.getUserId());
		if(entiti.getUserType()!=null)
		login.setUserType(entiti.getUserType());
		if(entiti.getPassword()!=null) 
		login.setPassword(entiti.getPassword());
		loginDao.saveLogin(login);
		
		entitiDetails.setId(entitiDetails.getId());
		entitiDetails.getTransactionLimit().setId(entitiDetails.getTransactionLimit().getId());
		entitiDetails.setUpdateDate(new Date());
		
		if(entiti.getTransactionLimit()!=null)
		entitiDetails.setTransactionLimit(entiti.getTransactionLimit());
		
		if(entiti.getPassword()!=null)
			entitiDetails.setPassword(entiti.getPassword());
		
		
		if(entiti.getUserName()!=null)
			entitiDetails.setUserName(entiti.getUserName());
		
		
		
		mgurushDao.update(entitiDetails);
		
	}else {
		throw new EotException("Mgurush Does not exits");
	}
		
	}

	@Override
	public Entiti findMgurushByUserId(String userId)
	{
		return mgurushDao.findMgurushByUserId(userId);
		
	}
	@Override
	public List<Entiti> findAll() {
		
		return mgurushDao.findAll();
	}

	

}
