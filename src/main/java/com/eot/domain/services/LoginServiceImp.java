package com.eot.domain.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eot.core.LoginTypes;
import com.eot.domain.dao.DistributerDao;
import com.eot.domain.dao.LoginDao;
import com.eot.domain.dao.EntitiDao;
import com.eot.domain.dao.SuperAdminDao;
import com.eot.domain.model.Distributer;
import com.eot.domain.model.Entiti;
import com.eot.domain.model.Login;
import com.eot.domain.model.SuperAdmin;
import com.eot.util.EotException;

@Service
@Transactional
public class LoginServiceImp implements LoginService {

	@Autowired
	EntitiDao mgurushDao;

	@Autowired
	SuperAdminDao adminDao;

	@Autowired
	LoginDao loginDao;
	
	@Autowired
	DistributerService distributerService;
	
	@Autowired
	DistributerDao distributerDao;

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
					Entiti entiti = mgurushDao.findMgurushByUserId(login.getUserId());
					entiti.setActive(true);
					entiti.setAccountEnabled(true);
					mgurushDao.saveOrUpdate(entiti);
					
					
				} else {
					throw new EotException("invalid User");
				}

			}else if(login.getUserType() == LoginTypes.DISTRIBUTER.getValue()) {
				if (login.getUserId().equals(loginUser.getUserId())
						&& login.getPassword().equals(loginUser.getPassword())) {
					Distributer distributer = distributerService.findDistributerByUserId(login.getUserId());
					distributer.setActive(true);
					distributer.setAccountEnabled(true);
					distributerDao.saveOrUpdate(distributer);
					
					
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
		return  loginDao.findLoginByUserId(userId);
	}

	@Override
	public void saveLogin(Login login) throws EotException {
		 loginDao.saveLogin(login);
		
	}

}
