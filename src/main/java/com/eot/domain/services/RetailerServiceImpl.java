package com.eot.domain.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eot.core.EOTConstant;
import com.eot.domain.dao.LoginDao;
import com.eot.domain.dao.RetailerDao;
import com.eot.domain.model.Login;
import com.eot.domain.model.Retailer;
import com.eot.domain.model.Wholeseller;
import com.eot.util.EotException;

@Service
@Transactional
public class RetailerServiceImpl implements RetailerService {

	@Autowired
	RetailerDao retailerDao;

	@Autowired
	LoginDao loginDao;

	@Override
	public void saveOrUpdate(Retailer retailer) throws EotException {

		Retailer retailerDetails = retailerDao.findRetailerByUserId(retailer.getUserId());
		if (retailerDetails != null) {
			throw new EotException(EOTConstant.RETAILER_ALREADY_EXISTS);
		} else {
			Login login = new Login();
			login.setUserId(retailer.getUserId());
			login.setPassword(retailer.getPassword());
			login.setUserType(retailer.getUserType());
			loginDao.saveLogin(login);
			
			retailer.setCreatedDate(new Date());
			retailer.setUpdateDate(new Date());
			retailerDao.saveOrUpdate(retailer);
		}
	}

	@Override
	public Retailer findRetailerByUserId(String userId) throws EotException {
		return retailerDao.findRetailerByUserId(userId);
	}

}
