package com.eot.domain.dao;

import com.eot.domain.model.Agent;

public interface AgentDao {
	
	public void saveOrUpdate(Agent agent);

	public Agent findAgentByUserId(String userId); 
	
}
