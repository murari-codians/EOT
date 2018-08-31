package com.eot.domain.services;

import com.eot.domain.model.Transaction;
import com.eot.util.EotException;

public interface TransactionService {
	
	public void deposite(String agentId,Transaction transaction) throws EotException;
	
	public void miniStatement(String agentId,Transaction transaction) throws EotException;

}
