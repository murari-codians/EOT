package com.eot.domain.dao;

import java.util.List;

import com.eot.domain.model.SuperAdmin;

public interface SuperAdminDao {

	public List<SuperAdmin> getListSuperAdmin();
	
	public void saveOrUpdate(SuperAdmin superAdmin);
	
	public void deleteSuperAdmin(int id);
	
	public SuperAdmin findSuperAdminById(int id);

	public SuperAdmin findAdminByUserId(Long userId);
}
