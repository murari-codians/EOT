package com.eot.domain.services;

import java.util.List;

import com.eot.domain.model.Customer;
import com.eot.domain.model.Entiti;
import com.eot.util.EotException;

public interface CustomerService {

	public void saveOrUpadte(Customer customer) throws EotException;

	public void deleteCustomer(String userId) throws EotException;

	public void updateCustomer(Customer customer) throws EotException;

	public List<Customer> findAll();

	Customer findCustomerByUserId(String userId) throws EotException;
}
