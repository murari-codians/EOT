package com.eot.domain.services;

import com.eot.domain.model.Wholeseller;
import com.eot.util.EotException;

public interface WholesellerService {

	public void saveOrUpdate(Wholeseller wholeseller) throws EotException;
}
