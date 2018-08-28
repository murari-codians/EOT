package com.eot.domain.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eot.domain.dao.RetailerDao;
import com.eot.domain.model.Retailer;
import com.eot.util.EotException;

@Service
@Transactional
public class RetailerServiceImpl implements RetailerService {
	
	@Autowired
	RetailerDao retailerDao;

	@Override
	public void saveOrUpdate(Retailer retailer) throws EotException {
		retailer.setCreatedDate(new Date());
		retailer.setUpdateDate(new Date());
		retailerDao.saveOrUpdate(retailer);
		
	}

	@Override
	public Retailer findRetailerByUserId(String userId) throws EotException {
		return retailerDao.findRetailerByUserId(userId);
	}

}
