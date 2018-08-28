package com.eot.domain.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eot.core.EOTConstant;
import com.eot.domain.dao.LoginDao;
import com.eot.domain.dao.SuperAdminDao;
import com.eot.domain.model.Login;
import com.eot.domain.model.SuperAdmin;
import com.eot.util.EotException;

@Service
@Transactional
public class SuperAdminServiceImp implements SuperAdminService {

	@Autowired
	SuperAdminDao adminDao;

	@Autowired
	LoginDao loginDao;

	public void setAdminDao(SuperAdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public void saveOrUpadte(SuperAdmin admin) throws EotException {

		SuperAdmin superAdmin = adminDao.getAdmin();

		if (superAdmin == null) {
			Login login = new Login();
			login.setUserId(admin.getUserId());
			login.setPassword(admin.getPassword());
			login.setUserType(admin.getUserType());
			loginDao.saveLogin(login);
			admin.setCreatedDate(new Date());
			adminDao.saveOrUpadte(admin);
		} else {
			throw new EotException(EOTConstant.SUPERADMIN_ALREADY_EXISTS);
		}

	}

	@Override
	public void deleteAdmin(String userId) throws EotException {
		SuperAdmin superAdmin = adminDao.findAdminByUserId(userId);
		if (superAdmin != null) {
			adminDao.deleteAdmin(userId);
		} else {
			throw new EotException(EOTConstant.SUPERADMIN_DOESNOT_EXISTS);
		}

	}

	@Override
	public SuperAdmin findAdminByUserId(String userId) {
		SuperAdmin superAdmin = adminDao.findAdminByUserId(userId);
		return superAdmin;
	}

	@Override
	public void adminLogin(SuperAdmin admin) throws EotException {

		SuperAdmin superAdmin = adminDao.findAdminByUserId(admin.getUserId());

		if (superAdmin != null) {
			if ((superAdmin.getPassword().equals(admin.getPassword()))
					&& (superAdmin.getUserId().equals(admin.getUserId()))) {
				superAdmin.setActive(true);
				superAdmin.setAccountEnabled(true);
				adminDao.saveOrUpadte(superAdmin);
			} else {
				throw new EotException(EOTConstant.INVALID_USER);
			}
		} else {
			throw new EotException(EOTConstant.SUPERADMIN_DOESNOT_EXISTS);
		}

	}

}
