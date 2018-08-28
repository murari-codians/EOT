 package com.eot.domain.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eot.domain.dao.AgentDao;
import com.eot.domain.model.Agent;
import com.eot.util.EotException;

@Service
@Transactional
public class AgentServiceImpl implements AgentService {

	@Autowired
	AgentDao agentDao;
	
	@Override
	public void saveOrUpdate(Agent agent) throws EotException {
		
		agent.setCreatedDate(new Date());
		agent.setUpdateDate(new Date());
		agentDao.saveOrUpdate(agent);
		
		
		
	}

}
