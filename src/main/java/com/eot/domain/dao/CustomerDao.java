package com.eot.domain.dao;

import java.util.List;

import com.eot.domain.model.Customer;

public interface CustomerDao {

	public void saveOrUpdate(Customer customer);

	public void deleteCustomer(String userId);

	public Customer findCustomerByUserId(String userId);
	
	public Customer findCustomerByAccountNo(String userId);

	public List<Customer> findAll();

	void update(Customer customer);
}
