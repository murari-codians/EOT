package com.eot.domain.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eot.core.EOTConstant;
import com.eot.core.LoginTypes;
import com.eot.domain.dao.LoginDao;
import com.eot.domain.dao.MgurushDao;
import com.eot.domain.dao.SuperAdminDao;
import com.eot.domain.model.Login;
import com.eot.domain.model.MGurush;
import com.eot.domain.model.SuperAdmin;
import com.eot.util.EotException;

@Service
@Transactional
public class LoginServiceImp implements LoginService {

	@Autowired
	MgurushDao mgurushDao;

	@Autowired
	SuperAdminDao adminDao;

	@Autowired
	LoginDao loginDao;

	@Override
	public void loginUser(Login loginUser) throws EotException {

		Login login = loginDao.findLoginByUserId(loginUser.getUserId());

		if (login != null) {
			if (login.getUserType() == LoginTypes.SUPERADMIN.getValue()) {
				if (login.getUserId().equals(loginUser.getUserId())
						&& login.getPassword().equals(loginUser.getPassword())) {
					SuperAdmin superadmin = adminDao.findAdminByUserId(login.getUserId());
					superadmin.setActive(true);
					superadmin.setAccountEnabled(true);
					adminDao.saveOrUpadte(superadmin);

				} else {
					throw new EotException("invalid User");
				}
			}
			
			else if (login.getUserType() == LoginTypes.MGURUSH.getValue()) {
				if (login.getUserId().equals(loginUser.getUserId())
						&& login.getPassword().equals(loginUser.getPassword())) {
					MGurush gurush = mgurushDao.findMgurushByUserId(login.getUserId());
					gurush.setActive(true);
					gurush.setAccountEnabled(true);
					mgurushDao.saveOrUpdate(gurush);
				} else {
					throw new EotException("invalid User");
				}

			}
		} else {
			throw new EotException("Login User Doen not Exits");
		}
	}

	@Override
	public Login findLoginByUserId(String userId) throws EotException {
		// TODO Auto-generated method stub
		return null;
	}

}
