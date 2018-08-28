package com.eot.domain.services;

import com.eot.domain.model.Agent;
import com.eot.util.EotException;

public interface AgentService {
	
	public void saveOrUpdate(String userId, Agent agent) throws EotException;

	public Agent findAgentByUserId(String userId) throws EotException;
	
}
