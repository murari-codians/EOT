package com.eot.domain.dao;

import java.util.List;

import com.eot.domain.model.Transaction;

public interface TransactionDao {

	public void deposite(Transaction transaction);
	
	public List<Transaction> miniStatements(Transaction transaction);
	
}
