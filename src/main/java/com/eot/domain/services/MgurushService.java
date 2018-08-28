package com.eot.domain.services;

import java.util.List;

import com.eot.domain.model.MGurush;
import com.eot.domain.model.SuperAdmin;
import com.eot.util.EotException;

public interface MgurushService {
	
	public void saveOrUpadte(MGurush mgurush) throws EotException;

	public void deleteMgurush(String userId) throws EotException ;
	
	public void updateMgurush(MGurush mgurush)throws EotException;
	
	public List<MGurush> findAll();

	MGurush findMgurushByUserId(String userId)throws EotException;
	
	
	

}
