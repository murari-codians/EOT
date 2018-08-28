package com.eot.domain.services;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eot.domain.dao.CommissionDao;
import com.eot.domain.model.Commission;
import com.eot.util.EotException;

@Service
@Transactional
public class CommissionServiceImpl implements CommissionService {

	@Autowired
	CommissionDao commissionDao;

	@Override
	public void saveOrUpadte(Commission commission) throws EotException {

		commissionDao.saveOrUpadte(commission);
	}

	@Override
	public void deleteCommission(String userId) throws EotException {
		commissionDao.deleteCommission(userId);

	}

	@Override
	public Commission findCommissionByUserId(String userId) {
		Commission commission = commissionDao.findCommissionByUserId(userId);
		return commission;
	}

	@Override
	public List<Commission> getListCommission() {
		List<Commission> list = commissionDao.getListCommission();
		if(list.size() == 0) {
			return Collections.emptyList();
		}else {
			return list;
		}
	}

}
