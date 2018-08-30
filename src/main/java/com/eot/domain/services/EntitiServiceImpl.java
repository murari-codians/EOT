package com.eot.domain.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eot.domain.dao.LoginDao;
import com.eot.core.EOTConstant;
import com.eot.domain.dao.EntitiDao;
import com.eot.domain.model.Entiti;
import com.eot.domain.model.Login;
import com.eot.util.EotException;

@Service
@Transactional
public class EntitiServiceImpl  implements EntitiService{

	@Autowired
	 EntitiDao entitiDao;
	
	 @Autowired
	 LoginDao loginDao;
	
	
	
	@Override
	public void saveOrUpadte(Entiti entiti) throws EotException {
		Entiti entitiDetails = entitiDao.findEntitiByUserId(entiti.getUserId());
		if(entitiDetails!=null) {

			throw new EotException(EOTConstant.ENTITY_ALREADY_EXISTS);
		}else {
		Login login = new Login();
		login.setUserId(entiti.getUserId());
		login.setPassword(entiti.getPassword());
		login.setUserType(entiti.getUserType());
		loginDao.saveLogin(login);
		
		entiti.setCreatedDate(new Date());
		entiti.setUpdateDate(new Date());
		entitiDao.saveOrUpdate(entiti);
		}
	}

	@Override
	public void deleteEntiti(String userId) throws EotException {
		
		Entiti entiti = entitiDao.findEntitiByUserId(userId);
		if(entiti != null) {
		entitiDao.deleteEntiti(userId);
		loginDao.deleteLogin(userId);
		}else {

			throw new EotException(EOTConstant.ENTITY_DOESNOT_EXISTS);
		}
	}

	@Override
	public void updateEntiti(Entiti entiti) throws EotException {
		
		Entiti entitiDetails = entitiDao.findEntitiByUserId(entiti.getUserId());
		
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
		
		if(entiti.getServiceChargeSplit()!=null || entiti.getServiceChargeSplit().size()>0)
			
		entitiDetails.setServiceChargeSplit(entiti.getServiceChargeSplit());
		
		entitiDao.update(entitiDetails);
		
	}else {
		throw new EotException(EOTConstant.ENTITY_DOESNT_EXISTS);
	}
		
	}

	@Override
	public Entiti findEntitiByUserId(String userId)
	{
		return entitiDao.findEntitiByUserId(userId);
		
	}
	@Override
	public List<Entiti> findAll() {
		
		return entitiDao.findAll();
	}

	

}
