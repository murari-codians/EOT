package com.eot.domain.dao;

import com.eot.domain.model.Wholeseller;

public interface WholesellerDao {

	public void saveOrUpdate(Wholeseller wholeseller);

	public Wholeseller findWholesellerByUserId(String userId);
}
