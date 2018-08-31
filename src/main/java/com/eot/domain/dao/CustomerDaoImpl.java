package com.eot.domain.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eot.domain.model.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveOrUpdate(Customer customer) {
		sessionFactory.getCurrentSession().saveOrUpdate(customer);

	}

	@Override
	public void deleteCustomer(String userId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		List<Customer> customerDetails = (List<Customer>) criteria.list();
		for (Customer customer : customerDetails) {
			if (customer.getCustomerId().equals(userId))
				sessionFactory.getCurrentSession().delete(customer);
		}

	}

	@Override
	public Customer findCustomerByUserId(String userId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		List<Customer> customerDetails = (List<Customer>) criteria.list();
		for (Customer customer : customerDetails) {
			if (customer.getCustomerId().equals(userId))
				return customer;
		}
		return null;
	}

	@Override
	public List<Customer> findAll() {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		return c.list();
	}

	@Override
	public void update(Customer customer) {
		sessionFactory.getCurrentSession().merge(customer);

	}

	@Override
	public Customer findCustomerByAccountNo(String accNo) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		List<Customer> customerDetails = (List<Customer>) criteria.list();
		for (Customer customer : customerDetails) {
			if (customer.getAccountNumber().equals(accNo))
				return customer;
		}
		return null;
	}

}
