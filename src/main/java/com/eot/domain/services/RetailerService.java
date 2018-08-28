package com.eot.domain.services;

import com.eot.domain.model.Retailer;
import com.eot.util.EotException;

public interface RetailerService {

	public void saveOrUpdate(Retailer retailer) throws EotException;

	Retailer findRetailerByUserId(String userId) throws EotException;
}
