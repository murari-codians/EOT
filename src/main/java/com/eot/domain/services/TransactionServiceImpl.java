package com.eot.domain.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eot.core.TransactionIdGenerator;
import com.eot.domain.dao.TransactionDao;
import com.eot.domain.model.Transaction;

@Service
@Transactional
public class TransactionServiceImpl  implements TransactionService{

	@Autowired
	TransactionDao transactionDao;
	
	@Autowired
	TransactionIdGenerator transactionIdGenerator;
	
	@Override
	public void deposite(Transaction transaction) {
		
		Long commissionDeduction = transaction.getTransactionAmount()*(1/10);
		
		
		transaction.setStatus(1000);
		transaction.setTransactionDate(new Date());
		transaction.setTransactionID(transactionIdGenerator.transactionIdGenerator());
		transactionDao.deposite(transaction);
		
		
		
	}

}
