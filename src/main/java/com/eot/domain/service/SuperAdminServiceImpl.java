package com.eot.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eot.api.EOTException;
import com.eot.domain.dao.SuperAdminDao;
import com.eot.domain.model.SuperAdmin;

/**
 * @author murari
 *
 */
@Service
@Transactional
public class SuperAdminServiceImpl implements SuperAdminService {

	@Autowired
	SuperAdminDao superAdminDao;

	public void setSuperAdminDao(SuperAdminDao superAdminDao) {
		this.superAdminDao = superAdminDao;
	}

	public List<SuperAdmin> getListSuperAdmin() {
		return superAdminDao.getListSuperAdmin();
	}

	public void saveOrUpdate(SuperAdmin superAdmin) throws EOTException {
		List<SuperAdmin> admins = superAdminDao.getListSuperAdmin();
		if (admins == null) {
			superAdminDao.saveOrUpdate(superAdmin);
		} else {
			throw new EOTException("Superadmin already exists");
		}

	}

	public void deleteSuperAdmin(int id) {
		superAdminDao.deleteSuperAdmin(id);
	}

	public SuperAdmin findSuperAdminById(int id) {
		return superAdminDao.findSuperAdminById(id);
	}

	@Override
	public void superAdminLogin(SuperAdmin admin) throws EOTException {

		SuperAdmin superAdmin = superAdminDao.findAdminByUserId(admin.getUserId());

		if (superAdmin != null) {
			if ((superAdmin.getPassword().equals(admin.getPassword()))
					&& (superAdmin.getUserId().equals(admin.getUserId()))) {
				superAdmin.setActive(true);
				superAdminDao.saveOrUpdate(superAdmin);
			} else {
				throw new EOTException("Invalid login");
			}
		} else {
			throw new EOTException("admin does not exists");
		}
	}
}
