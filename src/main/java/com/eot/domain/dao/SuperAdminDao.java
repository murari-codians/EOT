package com.eot.domain.dao;

import java.util.List;

import com.eot.domain.model.SuperAdmin;

public interface SuperAdminDao {
	
	public void saveOrUpadte(SuperAdmin admin);
	
	public void deleteAdmin(String userId);
	
	
	public SuperAdmin getAdmin();

	public SuperAdmin findAdminByUserId(String string);

}
