package com.eot.domain.services;

import com.eot.domain.model.Login;
import com.eot.util.EotException;

public interface LoginService {
	
public void loginUser(Login login) throws EotException;	

public Login findLoginByUserId(String userId) throws EotException;

}
