package com.eot.domain.dao;

import com.eot.domain.model.Login;

public interface LoginDao {
	
public void saveLogin(Login login);

public Login findLoginByUserId(String userId);

public void deleteLogin(String userId);

}
