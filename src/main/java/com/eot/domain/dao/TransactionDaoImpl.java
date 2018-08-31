package com.eot.domain.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eot.domain.model.Transaction;

@Repository
public class TransactionDaoImpl implements TransactionDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void deposite(Transaction transaction) {
		getSession().save(transaction);
		
	}

	@Override
	public List<Transaction> miniStatements(Transaction transaction) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Transaction.class);
		List<Transaction> transactionDetails = (List<Transaction>) criteria.list();
		List<Transaction> list = new ArrayList<>();
		for (Transaction transact : transactionDetails) {
			if (transact.getAccountNumber().equals(transaction.getAccountNumber()))
				list.add(transact);
		}
		return list;	
	}

}
