package com.eot.domain.service;

import java.util.List;

import com.eot.api.EOTException;
import com.eot.domain.model.SuperAdmin;

public interface SuperAdminService {

	public List<SuperAdmin> getListSuperAdmin();
	
	public void saveOrUpdate(SuperAdmin superAdmin) throws EOTException;
	
	public void deleteSuperAdmin(int id);
	
	public SuperAdmin findSuperAdminById(int id);

	public void superAdminLogin(SuperAdmin admin) throws EOTException;
}
