package com.eot.domain.dao;

import java.util.List;

import com.eot.domain.model.MGurush;
import com.eot.util.EotException;

public interface MgurushDao {
	
	
	public void saveOrUpdate(MGurush mGurush);
	
	public void deleteMgurush(String userId);
		
	public MGurush findMgurushByUserId(String userId);
	
	public List<MGurush> findAll();

	void update(MGurush mGurush);
	
	

}
