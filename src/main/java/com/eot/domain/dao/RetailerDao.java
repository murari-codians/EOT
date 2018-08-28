package com.eot.domain.dao;

import com.eot.domain.model.Retailer;

public interface RetailerDao {

	public void saveOrUpdate(Retailer retailer);

	public Retailer findRetailerByUserId(String userId);
}
