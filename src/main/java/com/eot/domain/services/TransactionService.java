package com.eot.domain.services;

import java.util.List;

import com.eot.domain.model.Transaction;
import com.eot.util.EotException;

public interface TransactionService {

	public void deposite(String agentId, Transaction transaction) throws EotException;

	public List<Transaction> miniStatement(String agentId, Transaction transaction) throws EotException;

	public Double balanceEnquiry(String accountNumber) throws EotException;;

}
