package com.eot.domain.dao;

import java.util.List;

import com.eot.domain.model.Entiti;

public interface EntitiDao {
	
	
	public void saveOrUpdate(Entiti entiti);
	
	public void deleteMgurush(String userId);
		
	public Entiti findMgurushByUserId(String userId);
	
	public List<Entiti> findAll();

	void update(Entiti entiti);
	
	

}
