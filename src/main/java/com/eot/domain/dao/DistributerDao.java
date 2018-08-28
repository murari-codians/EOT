package com.eot.domain.dao;

import com.eot.domain.model.Distributer;

public interface DistributerDao {

	public void saveOrUpdate(Distributer distributer);

	public Distributer findDistributerByUserId(String userId);

}
