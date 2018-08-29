package com.eot.domain.services;

import java.util.List;

import com.eot.domain.model.Entiti;
import com.eot.util.EotException;

public interface EntitiService {
	
	public void saveOrUpadte(Entiti entiti) throws EotException;

	public void deleteMgurush(String userId) throws EotException ;
	
	public void updateMgurush(Entiti entiti)throws EotException;
	
	public List<Entiti> findAll();

	Entiti findMgurushByUserId(String userId)throws EotException;
	
	
	

}
