package com.eot.domain.services;

import java.util.List;

import com.eot.domain.model.Entiti;
import com.eot.util.EotException;

public interface EntitiService {
	
	public void saveOrUpadte(Entiti entiti) throws EotException;

	public void deleteEntiti(String userId) throws EotException ;
	
	public void updateEntiti(Entiti entiti)throws EotException;
	
	public List<Entiti> findAll();

	Entiti findEntitiByUserId(String userId)throws EotException;
	
	
	

}
