package com.eot.domain.services;

import com.eot.domain.model.SuperAdmin;
import com.eot.util.EotException;

public interface SuperAdminService {

	public void saveOrUpadte(SuperAdmin admin) throws EotException;

	public void deleteAdmin(String userName) throws EotException ;

	public SuperAdmin findAdminByUserId(String userId) throws EotException;
	
	public void adminLogin(SuperAdmin admin) throws EotException;

	
	

}
