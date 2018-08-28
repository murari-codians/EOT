package com.eot.domain.services;

import com.eot.domain.model.Distributer;
import com.eot.util.EotException;

public interface DistributerService {

	public void saveOrUpdate(Distributer distributer) throws EotException;

	Distributer findDistributerByUserId(String userId) throws EotException;

}
