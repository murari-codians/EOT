package com.eot.domain.services;

import com.eot.domain.model.Wholeseller;
import com.eot.util.EotException;

public interface WholesellerService {

	public void saveOrUpdate(String userId, Wholeseller wholeseller) throws EotException;

	Wholeseller findWholesellerByUserId(String userId) throws EotException;
}
