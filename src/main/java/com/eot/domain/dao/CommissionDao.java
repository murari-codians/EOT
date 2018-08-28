package com.eot.domain.dao;

import java.util.List;

import com.eot.domain.model.Commission;

public interface CommissionDao {

	public void saveOrUpadte(Commission commission);

	public void deleteCommission(String id);

	public Commission findCommissionByUserId(String userId);

	public List<Commission> getListCommission();
}
