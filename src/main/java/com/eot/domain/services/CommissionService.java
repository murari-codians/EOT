package com.eot.domain.services;

import java.util.List;

import com.eot.domain.model.Commission;
import com.eot.util.EotException;

public interface CommissionService {

	public void saveOrUpadte(Commission commission) throws EotException;

	public void deleteCommission(String id) throws EotException;

	public Commission findCommissionByUserId(String userId);

	public List<Commission> getListCommission();

}
