package com.eot.domain.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eot.core.LoginTypes;
import com.eot.domain.dao.AgentDao;
import com.eot.domain.dao.DistributerDao;
import com.eot.domain.dao.EntitiDao;
import com.eot.domain.dao.LoginDao;
import com.eot.domain.dao.RetailerDao;
import com.eot.domain.dao.WholesellerDao;
import com.eot.domain.model.Agent;
import com.eot.domain.model.Distributer;
import com.eot.domain.model.Entiti;
import com.eot.domain.model.Login;
import com.eot.domain.model.Retailer;
import com.eot.domain.model.Wholeseller;
import com.eot.util.EotException;

@Service
@Transactional
public class AgentServiceImpl implements AgentService {

	@Autowired
	AgentDao agentDao;

	@Autowired
	EntitiService mgurushService;

	@Autowired
	LoginService loginSrvice;

	@Autowired
	LoginDao loginDao;

	@Autowired
	EntitiDao mgurushDao;

	@Autowired
	DistributerDao distributerDao;

	@Autowired
	RetailerDao retailerDao;

	@Autowired
	WholesellerDao wholesellerDao;

	@Override
	public void saveOrUpdate(String userId, Agent agent) throws EotException {

		Login login = loginSrvice.findLoginByUserId(userId);

		if (login != null) {
			if (login.getUserType() == LoginTypes.MGURUSH.getValue()) {
				Entiti entiti = mgurushService.findMgurushByUserId(userId);
				if (entiti != null) {

					if (entiti.isActive() && entiti.isAccountEnabled()) {
						Agent agentDetails = agentDao.findAgentByUserId(agent.getUserId());
						if (agentDetails != null) {
							throw new EotException("Agent already exits");
						} else {
							Login agentLogin = new Login();
							agentLogin.setUserId(agent.getUserId());
							agentLogin.setPassword(agent.getPassword());
							agentLogin.setUserType(agent.getUserType());
							loginDao.saveLogin(agentLogin);
							agent.setCreatedBy(userId);
							agent.setCreatedDate(new Date());
							agent.setUpdateDate(new Date());
							agentDao.saveOrUpdate(agent);
						}

					} else {
						throw new EotException("Entity is not active");
					}
				} else {
					throw new EotException("Entity does not exits");
				}
			} else if (login.getUserType() == LoginTypes.DISTRIBUTER.getValue()) {
				Distributer distributer = distributerDao.findDistributerByUserId(userId);
				if (distributer != null) {
					if (distributer.isActive() && distributer.isAccountEnabled()) {
						Agent agentDetails = agentDao.findAgentByUserId(agent.getUserId());
						if (agentDetails != null) {
							throw new EotException("Agent already exits");
						} else {
							Login agentLogin = new Login();
							agentLogin.setUserId(agent.getUserId());
							agentLogin.setPassword(agent.getPassword());
							agentLogin.setUserType(agent.getUserType());
							loginDao.saveLogin(agentLogin);
							agent.setCreatedBy(userId);
							agent.setCreatedDate(new Date());
							agent.setUpdateDate(new Date());
							agentDao.saveOrUpdate(agent);
						}

					}
				} else {
					throw new EotException("Distributer does not exits");
				}

			} else if (login.getUserType() == LoginTypes.WHOLSELLER.getValue()) {
				Wholeseller wholeseller = wholesellerDao.findWholesellerByUserId(userId);
				if (wholeseller != null) {
					if (wholeseller.isActive() && wholeseller.isAccountEnabled()) {
						Agent agentDetails = agentDao.findAgentByUserId(agent.getUserId());
						if (agentDetails != null) {
							throw new EotException("Agent already exits");
						} else {
							Login agentLogin = new Login();
							agentLogin.setUserId(agent.getUserId());
							agentLogin.setPassword(agent.getPassword());
							agentLogin.setUserType(agent.getUserType());
							loginDao.saveLogin(agentLogin);
							agent.setCreatedBy(userId);
							agent.setCreatedDate(new Date());
							agent.setUpdateDate(new Date());
							agentDao.saveOrUpdate(agent);
						}

					}
				} else {
					throw new EotException("Wholeseller does not exits");
				}

			} else if (login.getUserType() == LoginTypes.RETAILER.getValue()) {
				Retailer retailer = retailerDao.findRetailerByUserId(userId);
				if (retailer != null) {
					if (retailer.isActive() && retailer.isAccountEnabled()) {
						Agent agentDetails = agentDao.findAgentByUserId(agent.getUserId());
						if (agentDetails != null) {
							throw new EotException("Agent already exits");
						} else {
							Login agentLogin = new Login();
							agentLogin.setUserId(agent.getUserId());
							agentLogin.setPassword(agent.getPassword());
							agentLogin.setUserType(agent.getUserType());
							loginDao.saveLogin(agentLogin);
							
							agent.setCreatedBy(userId);
							agent.setCreatedDate(new Date());
							agent.setUpdateDate(new Date());
							agentDao.saveOrUpdate(agent);
						}

					}
				} else {
					throw new EotException("Retailer does not exits");
				}

			}

		} else {
			throw new EotException("user not logined");
		}
	}

	@Override
	public Agent findAgentByUserId(String userId) throws EotException {
		return agentDao.findAgentByUserId(userId);
	}

}
