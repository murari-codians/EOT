package com.eot.domain.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eot.core.LoginTypes;
import com.eot.domain.dao.AgentDao;
import com.eot.domain.model.Agent;
import com.eot.domain.model.Login;
import com.eot.domain.model.MGurush;
import com.eot.util.EotException;

@Service
@Transactional
public class AgentServiceImpl implements AgentService {

	@Autowired
	AgentDao agentDao;

	@Autowired
	MgurushService mgurushService;

	@Autowired
	LoginService loginSrvice;

	@Override
	public void saveOrUpdate(String userId, Agent agent) throws EotException {

		Login login = loginSrvice.findLoginByUserId(userId);
         if(login != null) {
		if (login.getUserType() == LoginTypes.MGURUSH.getValue()) {
			MGurush mgurush = mgurushService.findMgurushByUserId(userId);
			if (mgurush != null) {

				if (mgurush.isActive() && mgurush.isAccountEnabled()) {
					agent.setCreatedBy(userId);
					agent.setCreatedDate(new Date());
					agent.setUpdateDate(new Date());
					agentDao.saveOrUpdate(agent);
				} else {
					throw new EotException("Mgurush is not active");
				}
			} else {
				throw new EotException("Mgurush does not exits");
			}
		} else if (login.getUserType() == LoginTypes.DISTRIBUTER.getValue()) {
			agent.setCreatedBy(userId);
			agent.setCreatedDate(new Date());
			agent.setUpdateDate(new Date());
			agentDao.saveOrUpdate(agent);

		} else if (login.getUserType() == LoginTypes.WHOLSELLER.getValue()) {
			agent.setCreatedBy(userId);
			agent.setCreatedDate(new Date());
			agent.setUpdateDate(new Date());
			agentDao.saveOrUpdate(agent);
		}

	}else {
		throw new EotException("user not logined");
	}
	}

}
