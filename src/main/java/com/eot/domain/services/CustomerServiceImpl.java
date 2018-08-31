package com.eot.domain.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eot.core.EOTConstant;
import com.eot.domain.dao.CustomerDao;
import com.eot.domain.model.Customer;
import com.eot.util.EotException;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao customerDao;

	@Override
	public void saveOrUpadte(Customer customer) throws EotException {
		Customer customerDetails = customerDao.findCustomerByAccountNo(customer.getAccountNumber());
		if (customerDetails != null) {

			throw new EotException(EOTConstant.CUSTOMER_ALREADY_EXISTS);
		} else {
			customer.setCreatedDate(new Date());
			customerDao.saveOrUpdate(customer);
		}

	}

	@Override
	public void deleteCustomer(String userId) throws EotException {
		Customer customer = customerDao.findCustomerByUserId(userId);
		if (customer != null) {
			customerDao.deleteCustomer(userId);
		} else {

			throw new EotException(EOTConstant.CUSTOMER_DOESNOT_EXISTS);
		}

	}

	@Override
	public void updateCustomer(Customer customer) throws EotException {
		Customer customerDetails = customerDao.findCustomerByUserId(customer.getCustomerId());

		if (customerDetails != null) {

			customerDetails.setId(customerDetails.getId());

			customerDao.update(customerDetails);

		} else {
			throw new EotException(EOTConstant.CUSTOMER_DOESNOT_EXISTS);
		}

	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	@Override
	public Customer findCustomerByUserId(String userId) throws EotException {
		return customerDao.findCustomerByUserId(userId);
	}

}
