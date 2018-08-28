package com.eot.domain.services;

import com.eot.domain.model.Agent;
import com.eot.util.EotException;

public interface AgentService {
	
	public void saveOrUpdate(Agent agent) throws EotException;

}
