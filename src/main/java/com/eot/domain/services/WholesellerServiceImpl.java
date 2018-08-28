package com.eot.domain.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eot.domain.dao.WholesellerDao;
import com.eot.domain.model.Wholeseller;
import com.eot.util.EotException;

@Service
@Transactional
public class WholesellerServiceImpl implements WholesellerService {

	@Autowired
	WholesellerDao wholesellerDao;
	
	

	@Override
	public void saveOrUpdate(Wholeseller wholeseller) throws EotException {
		wholeseller.setCreatedDate(new Date());
		wholeseller.setUpdateDate(new Date());
		wholesellerDao.saveOrUpdate(wholeseller);

	}

	@Override
	public Wholeseller findWholesellerByUserId(String userId) throws EotException {
		return wholesellerDao.findWholesellerByUserId(userId);
	}
}
